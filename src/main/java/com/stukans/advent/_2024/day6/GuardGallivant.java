package com.stukans.advent._2024.day6;

import com.stukans.advent._2024.Coordinates;
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
        Coordinates coordinates = getStartingPoint(characters).orElseThrow(() -> new RuntimeException("No start point"));

        long answer = 1;
        Direction direction = Direction.NORTH;
        do {
            Coordinates nextCoordinates = coordinates.move(direction);
            Integer x = nextCoordinates.x();
            Integer y = nextCoordinates.y();

            if (x < 0 || x >= characters.length || y < 0 || y >= characters[0].length) {
                break;
            }

            if (characters[y][x] == '#') {
                direction = direction.next();
            } else {
                if (characters[coordinates.y()][coordinates.x()] != 'X') {
                    answer++;
                    characters[coordinates.y()][coordinates.x()] = 'X';
                }
                coordinates = nextCoordinates;
                //print(characters, character -> character == 'X', character -> character == '#');
            }
        } while (true);

        return answer;
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);
        Coordinates initial = getStartingPoint(characters).orElseThrow(() -> new RuntimeException("No start point"));

        long answer = 0;
        Direction direction = Direction.NORTH;
        Coordinates current = initial;
        Set<Coordinates> foundLoopedObstructionCoordinates = new HashSet<>();
        do {
            Coordinates nextCoordinates = current.move(direction);
            Integer x = nextCoordinates.x();
            Integer y = nextCoordinates.y();

            if (x < 0 || x >= characters.length || y < 0 || y >= characters[0].length) {
                break;
            }

            if (characters[y][x] == '#') {
                direction = direction.next();
            } else {
                if (!foundLoopedObstructionCoordinates.contains(nextCoordinates)) {
                    boolean isLooped = isLooped(characters, initial, nextCoordinates);
                    if (isLooped) {
                        answer++;
                        foundLoopedObstructionCoordinates.add(nextCoordinates);
                    }
                }

//                if (characters[current.y()][current.x()] != 'X') {
//                    answer++;
//                    characters[current.y()][current.x()] = 'X';
//                }
                current = nextCoordinates;
                //print(characters, character -> character == 'X', character -> character == '#');
            }
        } while (true);


        return answer;
    }

    public boolean isLooped(char[][] characters, final Coordinates initial, final Coordinates obstruction) {
        char buffer = characters[obstruction.y()][obstruction.x()];
        characters[obstruction.y()][obstruction.x()] = 'O';
        Set<Pair<Coordinates, Direction>> visited = new HashSet<>();
        try {
            Direction direction = Direction.NORTH;

            Coordinates current = initial;
            do {
                Pair<Coordinates, Direction> pair = Pair.of(current, direction);
                if (visited.contains(pair)) {
                    //print(characters, character -> character == 'O', character -> character == '#');
                    return true;
                } else {
                    visited.add(pair);
                }

                Coordinates nextCoordinates = direction.nextCoordinates(current);
                Integer x = nextCoordinates.x();
                Integer y = nextCoordinates.y();

                if (x < 0 || x >= characters.length || y < 0 || y >= characters[0].length) {
                    return false;
                }

                if (characters[y][x] == '#' || (nextCoordinates.equals(obstruction))) {
                    direction = direction.next();
                } else {
//                    if (characters[current.y()][current.x()] != 'X') {
//                        characters[current.y()][current.x()] = 'X';
//                    }
                    current = nextCoordinates;
                    //print(characters, character -> character == 'X', character -> character == '#');
                }

            } while (true);

        } finally {
            characters[obstruction.y()][obstruction.x()] = buffer;
        }

    }

    protected Optional<Coordinates> getStartingPoint(char[][] characters) {
        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                if (characters[y][x] == '^') {
                    return Optional.of(Coordinates.of(x, y));
                }
            }
        }
        return Optional.empty();
    }
}
