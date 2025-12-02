package com.stukans.advent._2024.day6;

import com.stukans.advent._2024.Coordinate;
import com.stukans.advent._2024.Direction;
import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GuardGallivant extends Puzzle {

    @Override
    public long solution1(File file) {
        char[][] characters = asCharacters(file);
        Coordinate coordinate = getStartingPoint(characters).orElseThrow(() -> new RuntimeException("No start point"));

        long answer = 1;
        Direction direction = Direction.NORTH;
        do {
            Coordinate nextCoordinate = coordinate.move(direction);
            Integer x = nextCoordinate.x();
            Integer y = nextCoordinate.y();

            if (x < 0 || x >= characters.length || y < 0 || y >= characters[0].length) {
                break;
            }

            if (characters[y][x] == '#') {
                direction = direction.next();
            } else {
                if (characters[coordinate.y()][coordinate.x()] != 'X') {
                    answer++;
                    characters[coordinate.y()][coordinate.x()] = 'X';
                }
                coordinate = nextCoordinate;
                //print(characters, character -> character == 'X', character -> character == '#');
            }
        } while (true);

        return answer;
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);
        Coordinate initial = getStartingPoint(characters).orElseThrow(() -> new RuntimeException("No start point"));

        long answer = 0;
        Direction direction = Direction.NORTH;
        Coordinate current = initial;
        Set<Coordinate> foundLoopedObstructionCoordinates = new HashSet<>();
        do {
            Coordinate nextCoordinate = current.move(direction);
            Integer x = nextCoordinate.x();
            Integer y = nextCoordinate.y();

            if (x < 0 || x >= characters.length || y < 0 || y >= characters[0].length) {
                break;
            }

            if (characters[y][x] == '#') {
                direction = direction.next();
            } else {
                if (!foundLoopedObstructionCoordinates.contains(nextCoordinate)) {
                    boolean isLooped = isLooped(characters, initial, nextCoordinate);
                    if (isLooped) {
                        answer++;
                        foundLoopedObstructionCoordinates.add(nextCoordinate);
                    }
                }

//                if (characters[current.y()][current.x()] != 'X') {
//                    answer++;
//                    characters[current.y()][current.x()] = 'X';
//                }
                current = nextCoordinate;
                //print(characters, character -> character == 'X', character -> character == '#');
            }
        } while (true);


        return answer;
    }

    public boolean isLooped(char[][] characters, final Coordinate initial, final Coordinate obstruction) {
        char buffer = characters[obstruction.y()][obstruction.x()];
        characters[obstruction.y()][obstruction.x()] = 'O';
        Set<Pair<Coordinate, Direction>> visited = new HashSet<>();
        try {
            Direction direction = Direction.NORTH;

            Coordinate current = initial;
            do {
                Pair<Coordinate, Direction> pair = Pair.of(current, direction);
                if (visited.contains(pair)) {
                    //print(characters, character -> character == 'O', character -> character == '#');
                    return true;
                } else {
                    visited.add(pair);
                }

                Coordinate nextCoordinate = direction.nextCoordinates(current);
                Integer x = nextCoordinate.x();
                Integer y = nextCoordinate.y();

                if (x < 0 || x >= characters.length || y < 0 || y >= characters[0].length) {
                    return false;
                }

                if (characters[y][x] == '#' || (nextCoordinate.equals(obstruction))) {
                    direction = direction.next();
                } else {
//                    if (characters[current.y()][current.x()] != 'X') {
//                        characters[current.y()][current.x()] = 'X';
//                    }
                    current = nextCoordinate;
                    //print(characters, character -> character == 'X', character -> character == '#');
                }

            } while (true);

        } finally {
            characters[obstruction.y()][obstruction.x()] = buffer;
        }

    }

    protected Optional<Coordinate> getStartingPoint(char[][] characters) {
        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                if (characters[y][x] == '^') {
                    return Optional.of(Coordinate.of(x, y));
                }
            }
        }
        return Optional.empty();
    }
}
