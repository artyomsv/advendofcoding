package com.stukans.advent.day16;

import com.stukans.advent.Puzzle;

import java.util.*;
import java.util.stream.LongStream;

class TheFloorWillBeLava extends Puzzle<Long> {

    @Override
    public long solve(List<String> input) {
        char[][] source = convert(input);

        char[][] energizedMatrix = new char[source.length][source[0].length];
        go(source, energizedMatrix, new Location(0, 0), BeamDirection.E);

        return sumUpEnergizedTiles(energizedMatrix);
    }

    @Override
    public long solve(List<String> input, Long aLong) {
        char[][] source = convert(input);
        return LongStream.of(
                calcHorizontalStartingPoint(source, 0, BeamDirection.S),
                calcHorizontalStartingPoint(source, source.length - 1, BeamDirection.N),
                calcVerticalStartingPoint(source, 0, BeamDirection.E),
                calcVerticalStartingPoint(source, source[0].length - 1, BeamDirection.W)
        ).max().orElse(-1L);
    }

    private long calcVerticalStartingPoint(char[][] source, int x, BeamDirection beamDirection) {
        long maxEnergized = 0;
        for (int y = 0; y < source.length; y++) {
            long result = getMaxEnergized(source, x, y, beamDirection);
            if (result > maxEnergized) {
                maxEnergized = result;
            }
        }
        return maxEnergized;
    }

    private long calcHorizontalStartingPoint(char[][] source, int y, BeamDirection beamDirection) {
        long maxEnergized = 0;
        for (int x = 0; x < source[0].length; x++) {
            long result = getMaxEnergized(source, x, y, beamDirection);
            if (result > maxEnergized) {
                maxEnergized = result;
            }
        }
        return maxEnergized;
    }

    private long getMaxEnergized(char[][] source, int x, int y, BeamDirection beamDirection) {
        long maxEnergized = 0;
        map = new HashMap<>();
        char[][] energizedMatrix = new char[source.length][source[y].length];
        go(source, energizedMatrix, new Location(x, y), beamDirection);
        long summedUpEnergizedTiles = sumUpEnergizedTiles(energizedMatrix);
        if (summedUpEnergizedTiles > maxEnergized) {
            maxEnergized = summedUpEnergizedTiles;
        }
        return maxEnergized;
    }


    private enum BeamDirection {N, W, S, E}

    private Map<Location, Set<BeamDirection>> map = new HashMap<>();

    private long sumUpEnergizedTiles(char[][] energizedMatrix) {
        long count = 0;
        for (int y = 0; y < energizedMatrix.length; y++) {
//            System.out.println();
            for (int x = 0; x < energizedMatrix[y].length; x++) {
                if (energizedMatrix[y][x] == '#') {
//                    System.out.print(energizedMatrix[y][x]);
                    count++;
                } else {
//                    System.out.print('.');
                }
            }

        }
//        System.out.println();
//        System.out.println(count);
        return count;
    }

    private void go(char[][] source, char[][] energizedMatrix, Location location, BeamDirection direction) {
        char nextCharacter;
        try {
            nextCharacter = source[location.y][location.x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }

        energizedMatrix[location.y][location.x] = '#';

        if (map.containsKey(location) && map.get(location).contains(direction)) {
            return;
        } else {
            Set<BeamDirection> orDefault = map.getOrDefault(location, new HashSet<>());
            orDefault.add(direction);
            map.put(location, orDefault);
        }

        if (nextCharacter == '.') {
            Location next = location.next(direction);
            go(source, energizedMatrix, next, direction);
        } else if (nextCharacter == '/') {
            BeamDirection beamDirection = nextBeamDirectionIfSlashFound(direction);
            go(source, energizedMatrix, location.next(beamDirection), beamDirection);
        } else if (nextCharacter == '\\') {
            BeamDirection beamDirection = nextBeamDirectionIfBackSlashFound(direction);
            go(source, energizedMatrix, location.next(beamDirection), beamDirection);
        } else if (nextCharacter == '|') {
            for (BeamDirection beamDirection : nextBeamDirectionIfPipeFound(direction)) {
                go(source, energizedMatrix, location.next(beamDirection), beamDirection);
            }
        } else if (nextCharacter == '-') {
            for (BeamDirection beamDirection : nextBeamDirectionIfMinusFound(direction)) {
                go(source, energizedMatrix, location.next(beamDirection), beamDirection);
            }
        }

    }

    private List<BeamDirection> nextBeamDirectionIfPipeFound(BeamDirection current) {
        return switch (current) {
            case N -> List.of(BeamDirection.N);
            case W, E -> List.of(BeamDirection.N, BeamDirection.S);
            case S -> List.of(BeamDirection.S);
        };
    }

    private List<BeamDirection> nextBeamDirectionIfMinusFound(BeamDirection current) {
        return switch (current) {
            case N, S -> List.of(BeamDirection.W, BeamDirection.E);
            case W -> List.of(BeamDirection.W);
            case E -> List.of(BeamDirection.E);
        };
    }

    private BeamDirection nextBeamDirectionIfSlashFound(BeamDirection current) {
        return switch (current) {
            case N -> BeamDirection.E;
            case W -> BeamDirection.S;
            case S -> BeamDirection.W;
            case E -> BeamDirection.N;
        };
    }

    private BeamDirection nextBeamDirectionIfBackSlashFound(BeamDirection current) {
        return switch (current) {
            case N -> BeamDirection.W;
            case W -> BeamDirection.N;
            case S -> BeamDirection.E;
            case E -> BeamDirection.S;
        };
    }

    private record Location(int x, int y) {

        public Location next(BeamDirection direction) {
            return switch (direction) {
                case N -> north();
                case W -> west();
                case S -> south();
                case E -> east();
            };
        }

        private Location north() {
            return new Location(x, y - 1);
        }

        private Location west() {
            return new Location(x - 1, y);
        }

        private Location south() {
            return new Location(x, y + 1);
        }

        private Location east() {
            return new Location(x + 1, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
