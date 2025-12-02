package com.stukans.advent._2024.day15;

import com.stukans.advent._2024.Coordinate;
import com.stukans.advent._2024.Direction;
import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WarehouseWoes extends Puzzle {


    public static final char WALL = '#';
    public static final char ROBOT = '@';
    public static final char BOX = 'O';
    public static final char EMPTY = '.';

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Line 1");
        Thread.sleep(1000);
        System.out.println("\r\rLine 2");


    }

    @Override
    public long solution1(File file) {
        Pair<List<String>, List<String>> data = parseInput(file);
        char[][] warehouse = getWarehouse(data.getLeft());
        List<Direction> directions = getDirections(data.getRight());

        Coordinate currentRobotPosition = robotStartingPoint(warehouse);

        printWithCharacterPredicates(warehouse, it -> it == WALL, it -> it == ROBOT, it -> it == BOX);

        try {
            for (int k = 0; k < directions.size(); k++) {
                Direction direction = directions.get(k);
                //System.out.printf("%4d|%d: Direction: %s", k, directions.size(), direction);
                Coordinate nextPotentialRobotPosition = currentRobotPosition.move(direction);
                char value = nextPotentialRobotPosition.getValue(warehouse);
                if (value == WALL) {
                    continue;
                } else if (value == EMPTY) {
                    currentRobotPosition.set(warehouse, EMPTY);
                    nextPotentialRobotPosition.set(warehouse, ROBOT);

                    currentRobotPosition = nextPotentialRobotPosition;
                    //printWithCharacterPredicates(warehouse, it -> it == WALL, it -> it == ROBOT, it -> it == BOX);
                } else if (value == BOX) {
                    boolean emptyPlacesFound = false;
                    Coordinate nextBoxCoordinates = nextPotentialRobotPosition;
                    do {
                        Coordinate move = nextBoxCoordinates.move(direction);
                        char nextValue = move.getValue(warehouse);
                        if (nextValue == EMPTY) {
                            emptyPlacesFound = true;
                            move.set(warehouse, BOX);
                            break;
                        } else if (nextValue == WALL) {
                            break;
                        }
                        nextBoxCoordinates = move;
                    } while (true);

                    if (emptyPlacesFound) {
                        currentRobotPosition.set(warehouse, EMPTY);
                        nextPotentialRobotPosition.set(warehouse, ROBOT);

                        currentRobotPosition = nextPotentialRobotPosition;
                    }

                    //printWithCharacterPredicates(warehouse, it -> it == WALL, it -> it == ROBOT, it -> it == BOX);

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Set<Coordinate> boxes = boxes(warehouse);
        long answer = 0;
        for (Coordinate box : boxes) {
            answer += box.y() * 100 + box.x();
        }

        return answer;
    }

    private Coordinate robotStartingPoint(char[][] warehouse) {
        for (int y = 0; y < warehouse.length; y++) {
            for (int x = 0; x < warehouse[y].length; x++) {
                if (warehouse[y][x] == ROBOT) {
                    return Coordinate.of(x, y);
                }
            }
        }
        throw new RuntimeException("Robot not found");

    }

    private Set<Coordinate> boxes(char[][] warehouse) {
        Set<Coordinate> boxes = new HashSet<>();
        for (int y = 0; y < warehouse.length; y++) {
            for (int x = 0; x < warehouse[y].length; x++) {
                if (warehouse[y][x] == BOX) {
                    boxes.add(Coordinate.of(x, y));
                }
            }
        }
        return boxes;

    }

    private Pair<List<String>, List<String>> parseInput(File file) {
        List<String> warehouseData = new ArrayList<>();
        List<String> directionsData = new ArrayList<>();

        boolean finishedWarehouse = false;
        for (String line : load(file)) {
            if (line.isBlank()) {
                finishedWarehouse = true;
                continue;
            }

            if (!finishedWarehouse) {
                warehouseData.add(line);
            } else {
                directionsData.add(line);
            }
        }
        return Pair.of(warehouseData, directionsData);
    }

    private static char[][] getWarehouse(List<String> warehouseData) {
        char[][] warehouse = new char[warehouseData.size()][];
        for (int i = 0; i < warehouseData.size(); i++) {
            warehouse[i] = warehouseData.get(i).toCharArray();
        }
        return warehouse;
    }

    private List<Direction> getDirections(List<String> directionsData) {
        return directionsData.stream()
                .map(String::toCharArray)
                .map(this::convert)
                .flatMap(Arrays::stream)
                .map(it -> {
                    return switch (it) {
                        case '<' -> Direction.WEST;
                        case '^' -> Direction.NORTH;
                        case '>' -> Direction.EAST;
                        case 'v' -> Direction.SOUTH;
                        default -> throw new IllegalStateException("Unexpected value: " + this);
                    };

                })
                .toList();
    }
}
