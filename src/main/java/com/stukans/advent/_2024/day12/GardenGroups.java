package com.stukans.advent._2024.day12;

import com.stukans.advent._2024.Coordinate;
import com.stukans.advent._2024.Direction;
import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GardenGroups extends Puzzle {

    @Override
    public long solution1(File file) {

        char[][] characters = asCharacters(file);

        List<Pair<Character, Set<Coordinate>>> gardens = getGarden(characters);

        long answer = 0;

        for (Pair<Character, Set<Coordinate>> entry : gardens) {
            Set<Coordinate> garden = entry.getRight();
            Collection<Coordinate> border = new ArrayList<>();
            //System.out.println(entry.getLeft() + ":");
            for (Coordinate coordinate : garden) {
                Collection<Coordinate> neighbours = getValidNeigbours(coordinate.neighbors(), garden);
                //System.out.println(coordinate + ": " + neighbours.size() + " | " + neighbours);
                border.addAll(neighbours);
            }
            long multiplication = (long) garden.size() * border.size();
            //System.out.println(entry.getLeft() + " plants:" + garden.size() + " borders: " + border.size() + " result: " + multiplication);
            answer += multiplication;
        }


        //System.out.println("Found: " + gardens.size() + " gardens");
        return answer;
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);

        List<Pair<Character, Set<Coordinate>>> gardens = getGarden(characters);

        long answer = 0;

        for (Pair<Character, Set<Coordinate>> gardenEntry : gardens) {
            Set<Coordinate> garden = gardenEntry.getRight();
            Set<Coordinate> border = new HashSet<>();
            //System.out.println(gardenEntry.getLeft() + ":");
            for (Coordinate coordinate : garden) {
                Collection<Coordinate> neigbours = getValidNeigbours(coordinate.neighbors(), garden);
                //  System.out.println(coordinate + ": " + neigbours.size() + " | " + neigbours);
                border.addAll(neigbours);
            }

            //print(border, characters[0].length, characters.length);

            Set<Coordinate> visited = new HashSet<>();
            Set<Set<Coordinate>> lines = new HashSet<>();
            for (Coordinate coordinate : border) {
                Set<Coordinate> hor = getHorizontalLines(coordinate, border, visited);
                lines.add(hor);
                Set<Coordinate> ver = getVerticalLines(coordinate, border, visited);
                lines.add(ver);

            }
            //System.out.println();
        }
        //System.out.println("Found: " + gardens.size() + " gardens");
        return answer;
    }

    private void print(Set<Coordinate> coordinates, int xMax, int yMax) {
        char[][] arr = new char[yMax + 2][xMax + 2];
        for (Coordinate coordinate : coordinates) {
            arr[coordinate.y() + 1][coordinate.x() + 1] = 'X';
        }

        printWithCharacterPredicates(arr, it -> it == 'X');
    }


    private static Set<Coordinate> getHorizontalLines(Coordinate coordinate, Collection<Coordinate> border, Set<Coordinate> visited) {
        boolean left = true;
        boolean right = true;
        Set<Coordinate> line = new HashSet<>();
        line.add(coordinate);

        Coordinate west = coordinate;
        Coordinate east = coordinate;
        do {
            if (right) {
                Coordinate moveEast = east.move(Direction.EAST);
                if (border.contains(moveEast)) {
                    line.add(moveEast);
                    visited.add(moveEast);
                    east = moveEast;
                } else {
                    right = false;
                }
            }

            if (left) {
                Coordinate moveWest = west.move(Direction.WEST);
                if (border.contains(moveWest)) {
                    line.add(moveWest);
                    visited.add(moveWest);
                    west = moveWest;
                } else {
                    left = false;
                }
            }

        } while (left || right);

        return line.size() > 1 ? line : Collections.<Coordinate>emptySet();
    }

    private static Set<Coordinate> getVerticalLines(Coordinate coordinate, Collection<Coordinate> border, Set<Coordinate> visited) {
        boolean top = true;
        boolean bottom = true;
        Set<Coordinate> line = new HashSet<>();
        line.add(coordinate);

        Coordinate north = coordinate;
        Coordinate south = coordinate;
        do {
            if (bottom) {
                Coordinate moveSouth = south.move(Direction.SOUTH);
                if (border.contains(moveSouth)) {
                    line.add(moveSouth);
                    visited.add(moveSouth);
                    south = moveSouth;
                } else {
                    bottom = false;
                }
            }

            if (top) {
                Coordinate moveNorth = north.move(Direction.NORTH);
                if (border.contains(moveNorth)) {
                    line.add(moveNorth);
                    visited.add(moveNorth);
                    north = moveNorth;
                } else {
                    top = false;
                }
            }

        } while (top || bottom);
        return line.size() > 1 ? line : Collections.<Coordinate>emptySet();
    }

    private List<List<Coordinate>> findLongConsecutiveBorders(List<Coordinate> list, Function<Coordinate, Integer> ordering, Function<Coordinate, Integer> incremental) {
        List<List<Coordinate>> result = new ArrayList<>();

        List<Coordinate> listToOperate = new ArrayList<>(list);

        do {
            Map<Integer, List<Coordinate>> collect = listToOperate.stream().collect(Collectors.groupingBy(
                    ordering::apply,
                    Collectors.toList()
            ));

            for (Map.Entry<Integer, List<Coordinate>> entry : collect.entrySet()) {
                List<Coordinate> consecutive = findConsecutiveLine(ordering, incremental, entry, result);
                if (!consecutive.isEmpty()) {
                    result.add(consecutive);
                    if (consecutive.size() == entry.getValue().size()) {
                        listToOperate.removeAll(consecutive);
                    } else {
                        for (Coordinate coordinate : consecutive) {
                            Iterator<Coordinate> iterator = listToOperate.iterator();
                            while (iterator.hasNext()) {
                                if (iterator.next().equals(coordinate)) {
                                    iterator.remove();
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    listToOperate.removeAll(entry.getValue());
                }
            }
        } while (!listToOperate.isEmpty());
        return result;
    }

    private static List<Coordinate> findConsecutiveLine(Function<Coordinate, Integer> ordering, Function<Coordinate, Integer> incremental, Map.Entry<Integer, List<Coordinate>> entry, List<List<Coordinate>> result) {
        Iterator<Coordinate> iterator = entry.getValue().iterator();
        Coordinate previous = iterator.next();
        List<Coordinate> consecutive = new ArrayList<>();
        consecutive.add(previous);

        while (iterator.hasNext()) {
            Coordinate current = iterator.next();
            if (current.equals(previous)) {
                continue;
            }
            if (ordering.apply(current).equals(ordering.apply(previous)) && incremental.apply(current) - incremental.apply(previous) == 1) {
                consecutive.add(current);
            } else {
                if (consecutive.size() > 1) {
                    result.add(consecutive);
                }
                consecutive = new ArrayList<>();
                consecutive.add(current);
            }
            previous = current;

        }
        if (consecutive.size() > 1) {
            return consecutive;
        }
        return Collections.emptyList();
    }

    private List<Pair<Character, Set<Coordinate>>> getGarden(char[][] characters) {
        List<Pair<Character, Set<Coordinate>>> gardens = new ArrayList<>();

        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                Coordinate point = Coordinate.of(x, y);
                if (checkIfCoordinateAlreadyBelongToAnotherGarden(gardens, point)) {
                    continue;
                }
                Set<Coordinate> visited = floodFill(point, characters, point.getValue(characters), Direction.EAST);

                if (!visited.isEmpty()) {
                    gardens.add(Pair.of(point.getValue(characters), visited));
                }
                //printWithCoordinatesPredicate(characters, visited::contains);
            }
        }
        //System.out.println("Counter: " + counter);
        return gardens;
    }

    private Collection<Coordinate> getValidNeigbours(Collection<Coordinate> neighbours, Set<Coordinate> garden) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate neighbour : neighbours) {
            if (!garden.contains(neighbour)) {
                coordinates.add(neighbour);
            }
        }
        return coordinates;
    }

    private boolean checkIfCoordinateAlreadyBelongToAnotherGarden(List<Pair<Character, Set<Coordinate>>> gardens, Coordinate point) {
        for (Pair<Character, Set<Coordinate>> garden : gardens) {
            if (garden.getRight().contains(point)) {
                return true;
            }
        }
        return false;
    }

}
