package com.stukans.advent.day11;

import com.stukans.advent.Puzzle;

import java.util.*;

public class CosmicExpansion extends Puzzle<Integer> {


    @Override
    public long solve(List<String> input) {
        return solve(input, 0);
    }

    @Override
    public long solve(List<String> input, Integer shift) {
        Character[][] universe = convertToMatrix(input);
        Set<Integer> horizontalExpensions = calcHorizontalExpensions(universe);
        Set<Integer> verticalExpensions = calculateVerticalExpensions(universe);

        List<Galaxy> galaxies = identifyGalaxies(universe);

        long totalDistance = calculateSumOfDistances(galaxies, horizontalExpensions, verticalExpensions, shift);

        return totalDistance;
    }

    private Character[][] convertToMatrix(List<String> input) {
        Character[][] universe = new Character[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            universe[i] = convert(input.get(i).strip().toCharArray());
        }
        return universe;
    }

    private static Set<Integer> calculateVerticalExpensions(Character[][] universe) {
        Set<Integer> expensions = new HashSet<>();
        for (int x = 0; x < universe[0].length; x++) {
            boolean expandable = true;
            for (int y = 0; y < universe.length; y++) {
                if (universe[y][x] == '#') {
                    expandable = false;
                    break;
                }
            }
            if (expandable) {
                expensions.add(x);
            }
        }
        return expensions;
    }

    private static Set<Integer> calcHorizontalExpensions(Character[][] universe) {
        Set<Integer> expensions = new HashSet<>();
        for (int y = 0; y < universe.length; y++) {
            boolean expandable = true;
            for (int x = 0; x < universe[y].length; x++) {
                if (universe[y][x] == '#') {
                    expandable = false;
                    break;
                }
            }
            if (expandable) {
                expensions.add(y);
            }
        }
        return expensions;

    }

    private static long calculateSumOfDistances(List<Galaxy> galaxies, Set<Integer> horizontalExpensions, Set<Integer> verticalExpensions, int shift) {
        int N = galaxies.size();
        int totalPairs = N * (N - 1) / 2;
        long totalDistance = 0;
        long step = 1;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            Galaxy source = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Galaxy destination = galaxies.get(j);
                long distance = source.coordinates.distance(destination.coordinates, horizontalExpensions, verticalExpensions, shift);
                System.out.printf("%3d: %d%s - %d%s | %d\n", step, source.index, source.coordinates, destination.index, destination.coordinates, distance);
                totalDistance += distance;
                step++;
            }

        }
        return totalDistance;
    }

    private List<Galaxy> identifyGalaxies(Character[][] universe) {
        for (int i = 0; i < universe.length; i++) {
            System.out.println(new String(convert(universe[i])));
        }


        List<Galaxy> galaxies = new ArrayList<>();
        int index = 1;
        for (int y = 0; y < universe.length; y++) {
            for (int x = 0; x < universe[y].length; x++) {
                if (universe[y][x] == '#') {
                    galaxies.add(new Galaxy(new Coordinates(x, y), index++));
                }
            }
        }
        return galaxies;
    }

    public static Character[][] pivotMatrix(Character[][] matrix) {
        Character[][] pivotedMatrix = new Character[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                pivotedMatrix[j][i] = matrix[i][j];
            }
        }
        return pivotedMatrix;
    }

    public record Galaxy(Coordinates coordinates, int index) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Galaxy galaxy = (Galaxy) o;
            return Objects.equals(coordinates, galaxy.coordinates);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinates);
        }
    }

    public record Coordinates(int x, int y) {

        public long sum() {
            return x + y;
        }

        public long distance(Coordinates dest, Set<Integer> horizontalExpensions, Set<Integer> verticalExpensions, int shift) {
            int difX = dest.x > x ? dest.x - x : x - dest.x;
            for (Integer expansion : verticalExpensions) {
                if ((expansion > this.x && expansion < dest.x) || (expansion > dest.x && expansion < this.x)) {
                    difX += shift == 1 ? shift : shift - 1;
                }
            }

            int difY = dest.y > y ? dest.y - y : y - dest.y;
            for (Integer expansion : horizontalExpensions) {
                if ((expansion > this.y && expansion < dest.y) || (expansion > dest.y && expansion < this.y)) {
                    difY += shift == 1 ? shift : shift - 1;
                }
            }
            return difX + difY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

}
