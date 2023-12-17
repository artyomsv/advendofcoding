package com.stukans.advent.day17;

import com.stukans.advent.Puzzle;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ClumsyCrucible extends Puzzle<Void> {

    @Override
    public long solve(List<String> input) {
        int[][] matrix = convertToIntArray(input);

        Set<Location> go = go(Location.of(1, 0, Direction.E), matrix, 0, new HashSet<>());

        return 0;
    }

    private Set<Location> go(Location location, int[][] array, int steps, Set<Location> visited) {
        steps++;
        System.out.printf("[%d,%d] steps:%d, visited:%d\n", location.x(), location.y(), steps, visited.size());
        if (location.endReached(array)) {
            return visited;
        }
        for (int i = 0; i < 3; i++) {
            Location left = location.toLeft();
            if (left.inBounds(array)) {
                HashSet<Location> set = new HashSet<>(visited);
                set.add(left);
                return go(left, array, steps, set);
            }

            Location right = location.toRight();
            if (right.inBounds(array)) {
                if (visited.contains(right)) {
                    return Collections.emptySet();
                }
                HashSet<Location> set = new HashSet<>(visited);
                set.add(right);
                return go(right, array, steps, set);
            }

            location = location.further();
            if (!location.inBounds(array)) {
                break;
            }

            if (visited.contains(location)) {
                return Collections.emptySet();
            }
            visited.add(location);


        }

        return Collections.emptySet();

    }

    @Override
    public long solve(List<String> input, Void unused) {
        return 0;
    }

}
