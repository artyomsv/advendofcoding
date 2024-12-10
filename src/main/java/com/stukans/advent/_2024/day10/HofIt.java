package com.stukans.advent._2024.day10;

import com.stukans.advent._2024.Coordinates;
import com.stukans.advent._2024.Direction;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HofIt extends Puzzle {

    @Override
    public long solution1(File file) {
        char[][] characters = asCharacters(file);

        List<Coordinates> startingPoints = getStartingPoints(characters);
        List<List<Coordinates>> es = new ArrayList<>();
        for (Coordinates startingPoint : startingPoints) {
            es.add(List.of(startingPoint));
        }
        List<List<Coordinates>> recursive = recursive(es, 1, characters);
        List<Integer> scores = recursive.stream().map(HashSet::new).map(Set::size).toList();
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);

        List<Coordinates> startingPoints = getStartingPoints(characters);
        List<List<Coordinates>> es = new ArrayList<>();
        for (Coordinates startingPoint : startingPoints) {
            es.add(List.of(startingPoint));
        }
        List<List<Coordinates>> recursive = recursive(es, 1, characters);
        List<Integer> scores = recursive.stream().map(List::size).toList();
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    public List<List<Coordinates>> recursive(List<List<Coordinates>> coordinates, int nextHigh, char[][] characters) {
        if (nextHigh > 9) {
            return coordinates;
        }

        List<List<Coordinates>> result = new ArrayList<>();
        for (List<Coordinates> coordinate : coordinates) {
            List<Coordinates> newLeaf = new ArrayList<>();
            for (Coordinates c : coordinate) {
                Coordinates north = c.move(Direction.NORTH);
                if (north.isInside(characters) && Character.isDigit(north.getValue(characters)) && Character.getNumericValue(north.getValue(characters)) == nextHigh) {
                    newLeaf.add(north);
                }

                Coordinates east = c.move(Direction.EAST);
                if (east.isInside(characters) && Character.isDigit(east.getValue(characters)) && Character.getNumericValue(east.getValue(characters)) == nextHigh) {
                    newLeaf.add(east);
                }

                Coordinates south = c.move(Direction.SOUTH);
                if (south.isInside(characters) && Character.isDigit(south.getValue(characters)) && Character.getNumericValue(south.getValue(characters)) == nextHigh) {
                    newLeaf.add(south);
                }

                Coordinates west = c.move(Direction.WEST);
                if (west.isInside(characters) && Character.isDigit(west.getValue(characters)) && Character.getNumericValue(west.getValue(characters)) == nextHigh) {
                    newLeaf.add(west);
                }

            }
            result.add(newLeaf);


        }

        nextHigh++;
        return recursive(result, nextHigh, characters);
    }


    private List<Coordinates> getStartingPoints(char[][] characters) {
        List<Coordinates> coordinates = new ArrayList<>();

        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                if (characters[y][x] == '0') {
                    coordinates.add(Coordinates.of(x, y));
                }
            }
        }
        return coordinates;
    }
}
