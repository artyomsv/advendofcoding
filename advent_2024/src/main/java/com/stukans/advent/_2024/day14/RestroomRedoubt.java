package com.stukans.advent._2024.day14;

import com.stukans.advent._2024.Coordinate;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestroomRedoubt extends Puzzle {

    public static void main(String[] args) {
        RestroomRedoubt puzzle = new RestroomRedoubt();
        Robot robot = puzzle.getRobot("p=2,4 v=2,-3");

        Coordinate coordinate = robot.getPosition(5, 11, 7);

    }

    public long solution1(File file, int steps, int xMax, int yMax) {
        String[] lines = load(file);

        List<Robot> robots = getRobots(lines);
        List<Coordinate> coordinates = new ArrayList<>();
        for (Robot robot : robots) {
            coordinates.add(robot.getPosition(steps, xMax, yMax));
        }

        int[][] array = new int[yMax][xMax];
        for (Coordinate coordinate : coordinates) {
            try {
                array[coordinate.y()][coordinate.x()] = array[coordinate.y()][coordinate.x()] + 1;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        printWithIntegerPredicates(array, integer -> integer > 0);

        long quadrant1 = 0;
        System.out.println();
        for (int y = 0; y < array.length / 2; y++) {
            for (int x = 0; x < array[y].length / 2; x++) {
                System.out.print(array[y][x]);
                quadrant1 += array[y][x];
            }
            System.out.println();
        }

        long quadrant2 = 0;
        System.out.println();
        for (int y = 0; y < array.length / 2; y++) {
            for (int x = array[y].length / 2 + 1; x < array[y].length; x++) {
                System.out.print(array[y][x]);
                quadrant2 += array[y][x];
            }
            System.out.println();
        }

        long quadrant3 = 0;
        System.out.println();
        for (int y = array.length / 2 + 1; y < array.length; y++) {
            for (int x = 0; x < array[y].length / 2; x++) {
                System.out.print(array[y][x]);
                quadrant3 += array[y][x];
            }
            System.out.println();
        }

        long quadrant4 = 0;
        System.out.println();
        for (int y = array.length / 2 + 1; y < array.length; y++) {
            for (int x = array[y].length / 2 + 1; x < array[y].length; x++) {
                System.out.print(array[y][x]);
                quadrant4 += array[y][x];
            }
            System.out.println();
        }

        System.out.printf("%d %d %d %d\n", quadrant1, quadrant2, quadrant3, quadrant4);

        return quadrant1 * quadrant2 * quadrant3 * quadrant4;
    }

    @Override
    public long solution2(File file) {
        String[] lines = load(file);

        int i = 1;

        int xMax = 101;
        int yMax = 103;
        List<Robot> robots = getRobots(lines);
        do {
            List<Robot> newRobots = new ArrayList<>();
            for (Robot robot : robots) {
                newRobots.add(new Robot(robot.getPosition(1, xMax, yMax), robot.velocity));
            }
            int[][] array = new int[yMax][xMax];
            List<Coordinate> list = newRobots.stream().map(Robot::initial).toList();
            Set<Coordinate> set = newRobots.stream().map(Robot::initial).collect(Collectors.toSet());

            //System.out.printf("%5d: %d %d\n", i, list.size(), set.size());

            for (Coordinate coordinate : list) {
                try {
                    array[coordinate.y()][coordinate.x()] = array[coordinate.y()][coordinate.x()] + 1;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (list.size() == set.size()) {
                System.out.println(i);
                printWithIntegerPredicates(array, integer -> integer > 0);
                break;
            }
            robots = newRobots;
            i++;
        } while (true);

        return i;

    }

    private List<Robot> getRobots(String[] lines) {
        List<Robot> robots = new ArrayList<>();
        for (String line : lines) {
            robots.add(getRobot(line));
        }
        return robots;
    }

    private Robot getRobot(String line) {
        String[] split = line.split(" ");
        String startData = split[0];
        String[] start = startData.split("=")[1].split(",");

        String velocityData = split[1];
        String[] velocity = velocityData.split("=")[1].split(",");

        return new Robot(
                Coordinate.of(Integer.parseInt(start[0].trim()), Integer.parseInt(start[1].trim())),
                Coordinate.of(Integer.parseInt(velocity[0].trim()), Integer.parseInt(velocity[1].trim()))
        );
    }

    private record Robot(Coordinate initial, Coordinate velocity) {
        public Coordinate getPosition(int steps, int xMax, int yMax) {
            int totalX = steps * velocity.x() + initial.x();
            int totalY = steps * velocity.y() + initial.y();
//            int correctionX = totalX / xMax;
//            int correctionY = totalY / yMax;
//
//            int x = totalX < 0 ? totalX + xMax : totalX > xMax ? totalX - xMax : totalX;
//            int y = totalY < 0 ? totalY + yMax : totalY > yMax ? totalY - yMax : totalY;
//
//            int x2 = calc(totalX, xMax);
//            int y2 = calc(totalY, yMax);


//            System.out.printf("TX:%d, TY:%d, CX:%d[%d], CY:%d[%d]  x:%d, y%d\n",
//                    totalX,
//                    totalY,
//                    correctionX,
//                    totalX % xMax,
//                    correctionY,
//                    totalY % yMax,
//                    x2, y2
//            );


            return Coordinate.of(calc(totalX, xMax), calc(totalY, yMax));
        }
    }

    private static int calc(int value, int max) {
        if (value >= 0 && value < max) {
            return value;
        }

        if (value < 0) {
            return calc(value + max, max);
        }

        return calc(value - max, max);

    }
}
