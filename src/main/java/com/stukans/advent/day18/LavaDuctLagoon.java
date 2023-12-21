package com.stukans.advent.day18;

import com.stukans.advent.Puzzle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class LavaDuctLagoon extends Puzzle<Void> {

    public enum Direction {
        U(0, -1),
        R(1, 0),
        D(0, 1),
        L(-1, 0);
        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Step {
        final Direction direction;
        final Integer length;

        final String rgb;

        public Step(String input) {
            String[] split = input.strip().split(" ");
            direction = Direction.valueOf(split[0].strip());
            length = Integer.parseInt(split[1].strip());
            rgb = split[2].strip();
        }

        public Step(Direction direction, Integer length, String rgb) {
            this.direction = direction;
            this.length = length;
            this.rgb = rgb;
        }
    }

    public record Coordinate(int x, int y) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public record Tile(Coordinate coordinates, String rgb) {
    }

    @Override
    public long solve(List<String> input) {
        int x = 0, xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        int y = 0, yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;
        List<Step> list = input.stream().map(Step::new).toList();
        Map<Coordinate, Tile> tiles = new HashMap<>();
        for (Step step : list) {
            for (int i = 0; i < step.length; i++) {
                x = x + step.direction.x;
                if (x < xMin) {
                    xMin = x;
                }
                if (x > xMax) {
                    xMax = x;
                }

                y = y + step.direction.y;
                if (y < yMin) {
                    yMin = y;
                }
                if (y > yMax) {
                    yMax = y;
                }

                Coordinate coordinates = new Coordinate(x, y);
                tiles.put(coordinates, new Tile(coordinates, step.rgb));
            }
        }


        int xCorrection = xMin == 0 ? 0 : (-1) * xMin - 1;
        int yCorrection = yMin == 0 ? 0 : (-1) * yMin - 1;
        char[][] array = new char[yMax + 1 + yCorrection][xMax + 1 + xCorrection];
        for (y = 0; y < array.length; y++) {
            for (x = 0; x < array[y].length; x++) {
                array[y + yCorrection][x + xCorrection] = tiles.containsKey(new Coordinate(x - xCorrection, y - yCorrection)) ? '#' : '.';
            }
        }

        printTheMatrix(array);
        System.out.println();

        for (y = 0; y < array.length; y++) {
            String line = new String(array[y]);
            if (line.indexOf('.') == -1) {
                continue;
            }

            long count = Stream.of(line.split("\\.+")).filter(it -> !it.isBlank()).count();
            if (count % 2 != 0) {
                continue;
            }

            boolean borderFound = false;
            boolean hasSpace = false;
            boolean endFound = false;
            for (x = 0; x < array[y].length - 1; x++) {

                if (array[y][x] == '#') {
                    if (!borderFound) {
                        borderFound = true;
                        endFound = false;
                        continue;
                    }

                    if (!endFound && hasSpace) {
                        borderFound = false;
                        endFound = true;
                        hasSpace = false;
                    }
                } else {
                    if (borderFound && !endFound) {
                        hasSpace = true;
                        array[y][x] = '#';
                    }

                }


            }
        }

        printTheMatrix(array);

        long result = 0;
        for (y = 0; y < array.length; y++) {
            for (x = 0; x < array[y].length; x++) {
                if (array[y][x] == '#') {
                    result++;
                }
            }

        }

        return result;
    }

    @Override
    public long solve(List<String> input, Void unused) {
        return 0;
    }
}
