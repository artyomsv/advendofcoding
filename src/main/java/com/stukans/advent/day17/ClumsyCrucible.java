package com.stukans.advent.day17;

import com.stukans.advent.Puzzle;

import java.util.*;

class ClumsyCrucible extends Puzzle<Void> {

    List<Location> finished = new ArrayList<>();

    @Override
    public long solve(List<String> input) {
        int[][] matrix = convertToIntArray(input);

        Set<Location> set = new HashSet<>();
        Location start = Location.of(0, 0, Direction.E, matrix[0][0]);
        set.add(start);
        Optional<Location> optional = start.further(matrix);
        Location further = optional.get();
        further.setWeight(further.calcWeight(matrix) + start.getWeight());
        go2(further, matrix, set);

        return 0;
    }

    private void go2(Location location, int[][] array, Set<Location> visited) {
        visited.add(location);
        System.out.printf("[%d,%d] visited:%d end:%s\n", location.x(), location.y(), visited.size(), location.endReached(array));
        if (location.endReached(array)) {
            finished.add(location);
            return;
        }

        List<Location> currentLocations = List.of(location);

        while (!currentLocations.isEmpty()) {
            List<Location> newCurrentLocations = new ArrayList<>();
            for (Location current : currentLocations) {
                visited.add(current);
                List<Location> possibleLocations = new ArrayList<>();
                current.toLeft(array).ifPresent(possibleLocations::add);
                current.toRight(array).ifPresent(possibleLocations::add);
                current.further(array).ifPresent(possibleLocations::add);

                for (Location possibleLocation : possibleLocations) {
                    if (visited.contains(possibleLocation)) {
                        continue;
                    }
                    int locationWeight = possibleLocation.calcWeight(array) + current.getWeight();
                    if (locationWeight < possibleLocation.getWeight()) {
                        possibleLocation.setWeight(locationWeight);
                    }

                    if (possibleLocation.endReached(array)) {
                        finished.add(possibleLocation);
                    }

                    newCurrentLocations.add(possibleLocation);
                }
            }
            currentLocations = newCurrentLocations;
        }
    }

    private void go(Location location, int[][] array, Set<Location> visited) {
        System.out.printf("[%d,%d] visited:%d end:%s\n", location.x(), location.y(), visited.size(), location.endReached(array));
        if (location.endReached(array)) {
            finished.add(location);
            return;
        }
        Location next = location;
        for (int i = 0; i < 3; i++) {
            next.toLeft(array).filter(it -> !visited.contains(it)).ifPresent(it -> {
                Set<Location> set = new HashSet<>(visited);
                set.add(it);
                go(it, array, set);
            });

            next.toRight(array).filter(it -> !visited.contains(it)).ifPresent(it -> {
                Set<Location> set = new HashSet<>(visited);
                set.add(it);
                go(it, array, set);
            });

            Optional<Location> optional = next.further(array).filter(it -> !visited.contains(it));
            if (optional.isPresent()) {
                visited.add(optional.get());
                next = optional.get();
            }
        }
    }

    @Override
    public long solve(List<String> input, Void unused) {
        return 0;
    }

}
