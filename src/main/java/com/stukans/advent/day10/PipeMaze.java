package com.stukans.advent.day10;

import java.util.*;
import java.util.stream.Stream;

public class PipeMaze {

    long solve(List<String> input, boolean partOne) {
        System.out.print("\n\n   ");
        for (int i = 0; i < input.get(0).length(); i++) {
            System.out.print(i < 100 ? 0 : i / 100);
        }
        System.out.print("\n   ");
        for (int i = 0; i < input.get(0).length(); i++) {
            System.out.print(i < 100 ? (i < 10 ? 0 : i / 10) : (i - 100) / 10);
        }
        System.out.print("\n   ");
        for (int i = 0; i < input.get(0).length(); i++) {
            System.out.print(i < 10 ? i : i % 10);
        }

        System.out.println();
        for (int i = 0; i < input.size(); i++) {
            System.out.printf("%3d%s\n", i, input.get(i));
        }

        char[][] maze = new char[input.size()][];
        int startX = -1, startY = -1;
        boolean startFound = false;
        for (int i = 0; i < input.size(); i++) {
            char[] charArray = input.get(i).strip().toCharArray();
            maze[i] = charArray;

            if (!startFound) {
                for (int j = 0; j < charArray.length; j++) {
                    if (charArray[j] == 'S') {
                        startX = j;
                        startY = i;
                        startFound = true;
                        break;
                    }
                }
            }
        }

        return nonRecursive(startX, startY, maze, partOne);
    }

    private enum Direction {N, S, E, W}

    private record Location(int x, int y, Direction direction) {

        @Override
        public boolean equals(Object obj) {
            Location l = (Location) obj;
            return this.x == l.x && this.y == l.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private long nonRecursive(int startX, int startY, char[][] maze, boolean partOne) {
        Set<Location> visited = new HashSet<>();
        visited.add(new Location(startX, startY, null));
        int steps = 1;
        Optional<Location> north = direction(startX, startY - 1, maze, Direction.N);
        north.ifPresent(it -> visited.add(new Location(startX, startY - 1, null)));

        Optional<Location> south = direction(startX, startY + 1, maze, Direction.S);
        south.ifPresent(it -> visited.add(new Location(startX, startY + 1, null)));

        Optional<Location> east = direction(startX + 1, startY, maze, Direction.E);
        east.ifPresent(it -> visited.add(new Location(startX + 1, startY, null)));

        Optional<Location> west = direction(startX - 1, startY, maze, Direction.W);
        west.ifPresent(it -> visited.add(new Location(startX - 1, startY, null)));

        List<Location> list = Stream.of(north, south, east, west).filter(Optional::isPresent).map(Optional::get).toList();
        visited.addAll(list);
        steps++;

        char initialLocationPipe = identifyInitialPipeType(north, south, east, west);

        /*System.out.print("Step: " + steps);
        for (Location location : list) {
            System.out.print(" " + maze[location.y][location.x] + "[" + location.x + "," + location.y + "]");
        }
        System.out.println();
        */

        boolean loopFinished = false;
        Location[] locations = list.toArray(Location[]::new);
        while (!loopFinished) {
            try {
                steps++;
                //System.out.print("Step: " + steps);
                for (int i = 0; i < locations.length; i++) {
                    Location curr = locations[i];
                    Optional<Location> next = direction(curr.x, curr.y, maze, curr.direction);
                    if (next.isPresent()) {
                        Location location = next.get();
                        visited.add(location);
                        //System.out.print(" " + maze[location.y][location.x] + "[" + location.x + "," + location.y + "]");
                        locations[i] = location;
                    } else {
                        throw new RuntimeException("Empty optional");
                    }

                }

                boolean allEquals = true;
                for (int i = 0; i < locations.length - 1; i++) {
                    if (!locations[i].equals(locations[i + 1])) {
                        allEquals = false;
                        break;
                    }
                }

                if (allEquals) {
                    loopFinished = true;
                }
                //System.out.println();

            } catch (ArrayIndexOutOfBoundsException e) {
                return 0;
            }
        }


        Set<Location> insideLocations = new HashSet<>();
        int inside = 0;
        if (!partOne) {
            for (int y = 0; y < maze.length; y++) {
                StringBuilder builder = new StringBuilder(maze[y].length);
                int visitedVerticals = 0;
                for (int x = 0; x < maze[y].length; x++) {
                    char c = maze[y][x];
                    if (c == 'S') {
                        c = initialLocationPipe;
                    }

                    if (c != '-' && c != '.' && visited.contains(new Location(x, y, null))) {
                        builder.append(c);
                    }

                    if (c == '|' && visited.contains(new Location(x, y, null))) {
                        visitedVerticals++;
                    }

                    if (c != '.' && visited.contains(new Location(x, y, null))) {
                        continue;
                    }
                    long intersects = 0;
                    String text = builder.toString();
                    intersects += visitedVerticals;
                    intersects += (text.length() - text.replace("L7", "").replace("FJ", "").length()) / 2;

                    if (intersects % 2 != 0) {
                        inside++;
                        insideLocations.add(new Location(x, y, null));
                    }
                }
            }
        }

        System.out.print("\n\n   ");
        for (int i = 0; i < maze.length; i++) {
            System.out.print(i < 100 ? 0 : i / 100);
        }
        System.out.print("\n   ");
        for (int i = 0; i < maze.length; i++) {
            System.out.print(i < 100 ? (i < 10 ? 0 : i / 10) : (i - 100) / 10);
        }
        System.out.print("\n   ");
        for (int i = 0; i < maze.length; i++) {
            System.out.print(i < 10 ? i : i % 10);
        }

        System.out.println();
        for (int y = 0; y < maze.length; y++) {
            System.out.printf("%3d", y);
            for (int x = 0; x < maze[y].length; x++) {
                char c = maze[y][x];
                Location curr = new Location(x, y, null);
                if (c == '.' && !insideLocations.contains(curr)) {
                    System.out.print('.');
                    continue;
                } else if (c == '.' || insideLocations.contains(curr)) {
                    System.out.print('I');
                    continue;
                }

                if (visited.contains(curr)) {
                    System.out.print(map(maze[y][x]));
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
        }


        return partOne ? steps : inside;
    }

    private Character identifyInitialPipeType(Optional<Location> north, Optional<Location> south, Optional<Location> east, Optional<Location> west) {
        if (north.isPresent() && south.isPresent()) {
            return '|';
        } else if (east.isPresent() && west.isPresent()) {
            return '-';
        } else if (north.isPresent() && east.isPresent()) {
            return 'L';
        } else if (north.isPresent() && west.isPresent()) {
            return 'J';
        } else if (south.isPresent() && west.isPresent()) {
            return '7';
        }
        return 'F';
    }

    private Character map(char c) {
        if (c == 'L') {
            return '└';
        } else if (c == 'J') {
            return '┘';
        } else if (c == '7') {
            return '┐';
        } else if (c == 'F') {
            return '┌';
        } else if (c == '|') {
            return '│';
        } else if (c == '-') {
            return '─';
        } else {
            return c;
        }
    }

    private Optional<Location> direction(int x, int y, char[][] maze, Direction direction) {
        char tile;
        try {
            tile = maze[y][x];
            if (tile == '.') {
                return Optional.empty();
            }

            if (direction == Direction.N) {
                if (tile != '|' && tile != 'F' && tile != '7') {
                    return Optional.empty();
                }
                if (tile == '|') {
                    return getLocationNorth(x, y);
                } else if (tile == 'F') {
                    return getLocationEast(x, y);
                } else {
                    return getLocationWest(x, y);
                }

            } else if (direction == Direction.S) {
                if (tile != '|' && tile != 'J' && tile != 'L') {
                    return Optional.empty();
                }
                if (tile == '|') {
                    return getLocationSouth(x, y);
                } else if (tile == 'J') {
                    return getLocationWest(x, y);
                } else {
                    return getLocationEast(x, y);
                }

            } else if (direction == Direction.E) {
                if (tile != '-' && tile != 'J' && tile != '7') {
                    return Optional.empty();
                }
                if (tile == '-') {
                    return getLocationEast(x, y);
                } else if (tile == 'J') {
                    return getLocationNorth(x, y);
                } else {
                    return getLocationSouth(x, y);
                }

            } else if (direction == Direction.W) {
                if (tile != '-' && tile != 'L' && tile != 'F') {
                    return Optional.empty();
                }
                if (tile == '-') {
                    return getLocationWest(x, y);
                } else if (tile == 'L') {
                    return getLocationNorth(x, y);
                } else {
                    return getLocationSouth(x, y);
                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private static Optional<Location> getLocationEast(int x, int y) {
        return Optional.of(new Location(x + 1, y, Direction.E));
    }

    private static Optional<Location> getLocationSouth(int x, int y) {
        return Optional.of(new Location(x, y + 1, Direction.S));
    }

    private static Optional<Location> getLocationNorth(int x, int y) {
        return Optional.of(new Location(x, y - 1, Direction.N));
    }

    private static Optional<Location> getLocationWest(int x, int y) {
        return Optional.of(new Location(x - 1, y, Direction.W));
    }

}
