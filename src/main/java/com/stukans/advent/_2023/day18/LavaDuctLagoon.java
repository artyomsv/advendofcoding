package com.stukans.advent._2023.day18;

import com.stukans.advent._2023.Puzzle;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LavaDuctLagoon extends Puzzle<Void> {


    @Override
    public long solve(List<String> input) {
        Function<String, Step> function = s -> new Step(s);

        return solution(buildTilesMatrix(input, function));
    }

    @Override
    public long solve(List<String> input, Void unused) {
        Function<String, Step> function = s -> {
            String[] split = s.strip().split(" ");
            String rgb = split[2].strip();
            String substring = rgb.substring(2, 7);
            char c = rgb.charAt(rgb.length() - 2);
            Direction d = switch (c) {
                case '0' -> Direction.R;
                case '1' -> Direction.D;
                case '2' -> Direction.L;
                default -> Direction.U;
            };

            return new Step(d, Integer.parseInt(substring, 16), rgb);
        };

        int x = 0, xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        int y = 0, yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;

        List<Step> list = input.stream().map(function).toList();

        Map<Coordinate, Tile> tiles = new HashMap<>();
        Map<Integer, Set<Tile>> tilesOnY = new HashMap<>();
        List<VerticalRange> verticalRanges = new ArrayList<>();

        Tile firstTile = null;
        Tile prevTile = null;
        for (Step step : list) {

            char direction = switch (step.direction) {
                case R, L -> '─';
                case D, U -> '│';
            };

            if (prevTile != null) {
                char decide = prevTile.step.direction.decide(step.direction);
                Tile tileUpdate = prevTile.changeDirection(decide);
                tiles.put(prevTile.coordinates, tileUpdate);
                Set<Tile> listOfYTiles = tilesOnY.getOrDefault(prevTile.coordinates.y(), new HashSet<>());
                listOfYTiles.remove(tileUpdate);
                listOfYTiles.add(tileUpdate);
                tilesOnY.put(prevTile.coordinates.y(), listOfYTiles);
            }

            x = x + (step.direction.x * step.length);
            if (x < xMin) {
                xMin = x;
            }
            if (x > xMax) {
                xMax = x;
            }

            y = y + (step.direction.y * step.length);
            if (y < yMin) {
                yMin = y;
            }
            if (y > yMax) {
                yMax = y;
            }

            Coordinate coordinates = new Coordinate(x, y);
            Tile tile = new Tile(coordinates, step.rgb, '#', direction, step);
            tiles.put(coordinates, tile);
            Set<Tile> listOfYTiles = tilesOnY.getOrDefault(tile.coordinates.y(), new HashSet<>());
            listOfYTiles.remove(tile);
            listOfYTiles.add(tile);
            tilesOnY.put(coordinates.y(), listOfYTiles);

            prevTile = tile;
            if (firstTile == null) {
                firstTile = tile;
            }

            if (step.direction == Direction.U) {
                verticalRanges.add(new VerticalRange(coordinates.x() - step.length, coordinates.x()));
            }

            if (step.direction == Direction.D) {
                verticalRanges.add(new VerticalRange(coordinates.x(), coordinates.x() + step.length));
            }
        }

        Tile tileUpdate = prevTile.changeDirection(prevTile.step.direction.decide(firstTile.step().direction));
        tiles.put(prevTile.coordinates, tileUpdate);
        Set<Tile> listOfYTiles = tilesOnY.getOrDefault(prevTile.coordinates.y(), new HashSet<>());
        listOfYTiles.remove(tileUpdate);
        listOfYTiles.add(tileUpdate);
        tilesOnY.put(prevTile.coordinates.y(), listOfYTiles);

        for (Integer location : tilesOnY.keySet().stream().sorted().toList()) {
            Set<Tile> list1 = tilesOnY.get(location);
            System.out.println();

        }

        return 0;
    }

    public record VerticalRange(int from, int to) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VerticalRange that = (VerticalRange) o;
            return from == that.from && to == that.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "[" + from + "," + to + ']';
        }
    }

    public record Tile(Coordinate coordinates, String rgb, char character, char direction, Step step) {

        public Tile changeCharacter(char character) {
            return new Tile(coordinates, rgb, character, direction, step);
        }

        public Tile changeDirection(char direction) {
            return new Tile(coordinates, rgb, character, direction, step);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return Objects.equals(coordinates, tile.coordinates);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinates);
        }

        @Override
        public String toString() {
            return "Tile{" +
                    "coordinates=" + coordinates +
                    ", character=" + character +
                    ", direction=" + direction +
                    '}';
        }
    }

    private long solution(Tile[][] array) {
        //printTheMatrix(array);
        //System.out.println();

        for (int y = 0; y < array.length; y++) {
            Tile[] tiles = array[y];

            String line = new String(convert(Stream.of(tiles).map(Tile::character).toArray(Character[]::new)));
            if (line.indexOf('.') == -1) {
                continue;
            }

            List<String> blocks = Stream.of(line.split("\\.+")).filter(it -> !it.isBlank()).toList();
            long count = blocks.size();
            if (count == 1) {
                continue;
            }

            StringBuilder builder = new StringBuilder(tiles.length);
            int visitedVerticals = 0;

            for (int x = 0; x < tiles.length - 1; x++) {
                char c = tiles[x].character;
                if (c == '.') {
                    builder.append(c);
                }

                if (c == '#' && tiles[x].direction != '─') {
                    builder.append(tiles[x].direction);
                }

                if (tiles[x].direction == '│') {
                    visitedVerticals++;
                }

                if (c != '.') {
                    continue;
                }

                long intersects = 0;
                String text = builder.toString();
                intersects += visitedVerticals;
                intersects += (text.length() - text.replace("└┐", "").replace("┌┘", "").length()) / 2;

                if (intersects % 2 != 0) {
                    tiles[x] = tiles[x].changeCharacter('0');
                }
            }
        }

        //printTheMatrix(array);
        return countResult(array);
    }

    private Tile[][] buildTilesMatrix(List<String> input, Function<String, Step> stepFunction) {
        int x = 0, xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        int y = 0, yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;

        List<Step> list = input.stream().map(stepFunction).toList();

        Map<Coordinate, Tile> tiles = new HashMap<>();

        Tile firstTile = null;
        Tile prevTile = null;
        for (Step step : list) {
            PrimitiveIterator.OfInt iterator = IntStream.range(0, step.length).iterator();

            char direction = switch (step.direction) {
                case R, L -> '─';
                case D, U -> '│';
            };

            if (prevTile != null) {
                char decide = prevTile.step.direction.decide(step.direction);
                tiles.put(prevTile.coordinates, prevTile.changeDirection(decide));
            }

            while (iterator.hasNext()) {
                Integer i = iterator.next();
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
                Tile tile = new Tile(coordinates, step.rgb, '#', direction, step);

                tiles.put(coordinates, tile);
                prevTile = tile;
                if (firstTile == null) {
                    firstTile = tile;
                }
            }

        }

        tiles.put(prevTile.coordinates, prevTile.changeDirection(prevTile.step.direction.decide(firstTile.step().direction)));

        int xCorrection = xMin == 0 ? 0 : (-1) * xMin;
        int yCorrection = yMin == 0 ? 0 : (-1) * yMin;

        Tile[][] array = new Tile[yMax + 1 + yCorrection][xMax + 1 + xCorrection];

        for (y = yMin; y < yMax + 1; y++) {
            for (x = xMin; x < xMax + 1; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                int yCorrected = y + yCorrection;
                int xCorrected = x + xCorrection;
                if (tiles.containsKey(coordinate)) {
                    array[yCorrected][xCorrected] = tiles.get(coordinate);
                } else {
                    array[yCorrected][xCorrected] = new Tile(coordinate, null, '.', '+', null);
                }
            }
        }
        return array;
    }

    private static Function<String, Step> stepFunctionPart1() {
        return Step::new;
    }

    private long countResult(Tile[][] array) {
        int x;
        int y;
        long result = 0;
        for (y = 0; y < array.length; y++) {
            for (x = 0; x < array[y].length; x++) {
                if (array[y][x].character == '#' || array[y][x].character == '0') {
                    result++;
                }
            }

        }
        return result;
    }

    private void printTheMatrix(Tile[][] matrix) {
        System.out.println();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x].character == '#') {
                    System.out.print(matrix[y][x].direction);
                } else {
                    System.out.print(matrix[y][x].character);
                }
            }
            System.out.println();
        }
    }


}
