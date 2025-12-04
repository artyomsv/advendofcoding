package com.stukans.advent._2025.day4;

import com.stukans.advent._2025.Coordinate;
import com.stukans.advent._2025.Direction;
import com.stukans.advent._2025.Puzzle;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

class PrintingDepartment extends Puzzle {

    @Override
    public long solution1(File file) {
        char[][] characters = asCharacters(file);
        long result = 0;
        Set<Coordinate> coordinates = new HashSet<>();
        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                Coordinate coordinate = Coordinate.of(x, y);
                if (coordinate.getValue(characters) == '@') {
                    int count = count(coordinate, characters);
                    if (count < 4) {
                        result++;
                        coordinates.add(coordinate);
                    }
                }

            }
        }
        return result;
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);
        long result = 0;
        long phaseResult;
        do {
            Set<Coordinate> coordinates = new HashSet<>();
            phaseResult = 0;
            for (int y = 0; y < characters.length; y++) {
                for (int x = 0; x < characters[y].length; x++) {
                    Coordinate coordinate = Coordinate.of(x, y);
                    if (coordinate.getValue(characters) == '@') {
                        int count = count(coordinate, characters);
                        if (count < 4) {
                            phaseResult++;
                            coordinates.add(coordinate);
                        }
                    }

                }
            }
            replace(characters, coordinates, () -> '.');
            result += phaseResult;
        } while (phaseResult > 0);

        return result;
    }

    private int count(Coordinate coordinate, char[][] characters) {
        int count = 0;
        for (Direction direction : Direction.values()) {
            Coordinate move = coordinate.move(direction);
            if (move.isInside(characters) && move.getValue(characters) == '@') {
                count++;
            }
        }
        return count;

    }

}
