package com.stukans.advent.day17;

import com.stukans.advent.Puzzle;

import java.util.*;

class ClumsyCrucible extends Puzzle<Void> {

    List<Location> finished = new ArrayList<>();

    @Override
    public long solve(List<String> input) {
        int[][] matrix = convertToIntArray(input);
        char[][] print = convert(matrix);
        Node[][] nodes = new Node[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            nodes[y] = new Node[matrix[y].length];
            for (int x = 0; x < matrix[y].length; x++) {
                nodes[y][x] = new Node(Location.of(x, y), (x == 0 && y == 0) ? 0 : Integer.MAX_VALUE);
            }
        }

        Set<Node> set = new HashSet<>();
        Location start = Location.of(0, 0, Direction.E);
        print[start.y()][start.x()] = '█';
        go2(start, matrix, nodes, print, set);


        for (int y = 0; y < nodes.length; y++) {
            System.out.println();
            for (int x = 0; x < nodes[y].length; x++) {
                System.out.printf("%3d ", nodes[y][x].getWeight());
            }
        }

        return finished.iterator().next().getNode(nodes).getWeight();
    }

    private void go2(Location location, int[][] array, Node[][] nodes, char[][] print, Set<Node> visited) {
        visited.add(location.getNode(nodes));
        print[location.y()][location.x()] = '█';

        List<Location> currentLocations = List.of(location);

        PriorityQueue<Location> queue = new PriorityQueue<>();

        while (!currentLocations.isEmpty()) {
            List<Location> newCurrentLocations = new ArrayList<>();

            for (Location current : currentLocations) {
                Node currentNode = current.getNode(nodes);

                print[current.y()][current.x()] = '█';
                List<Location> possibleLocations = new ArrayList<>();
                current.toLeft(array).ifPresent(possibleLocations::add);
                current.toRight(array).ifPresent(possibleLocations::add);
                current.further(array).ifPresent(possibleLocations::add);

                List<com.stukans.advent.Pair<Location, Node>> list = possibleLocations.stream()
                        .map(location1 -> new com.stukans.advent.Pair<>(location1, location1.getNode(nodes)))
                        .sorted(Comparator.comparing(o -> o.getV().getWeight()))
                        .toList();

                for (com.stukans.advent.Pair<Location, Node> pair : list) {
                    Node node = pair.getV();
                    if (visited.contains(node)) {
                        continue;
                    }
                    int locationWeight = pair.getT().calcWeight(array) + currentNode.getWeight();
                    if (locationWeight < node.getWeight()) {
                        node.setWeight(locationWeight);
                    }

                    if (pair.getT().endReached(array)) {
                        finished.add(pair.getT());
                        print[pair.getT().y()][pair.getT().x()] = 'X';
                        continue;
                    }

                    newCurrentLocations.add(pair.getT());
                    print[pair.getT().y()][pair.getT().x()] = '#';
                }
                visited.add(currentNode);
            }
            //printTheMatrix(print);
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
