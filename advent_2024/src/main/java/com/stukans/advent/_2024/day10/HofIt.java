package com.stukans.advent._2024.day10;

import com.stukans.advent._2024.Coordinate;
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

        List<Coordinate> startingPoints = getStartingPoints(characters);
        List<List<Coordinate>> es = new ArrayList<>();
        for (Coordinate startingPoint : startingPoints) {
            es.add(List.of(startingPoint));
        }
        List<List<Coordinate>> recursive = recursive(es, 1, characters);
        List<Integer> scores = recursive.stream().map(HashSet::new).map(Set::size).toList();
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);

        List<Coordinate> startingPoints = getStartingPoints(characters);
        List<List<Coordinate>> es = new ArrayList<>();
        for (Coordinate startingPoint : startingPoints) {
            es.add(List.of(startingPoint));
        }
        List<List<Coordinate>> recursive = recursive(es, 1, characters);
        List<Integer> scores = recursive.stream().map(List::size).toList();
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    public List<List<Coordinate>> recursive(List<List<Coordinate>> coordinates, int nextHigh, char[][] characters) {
        if (nextHigh > 9) {
            return coordinates;
        }

        List<List<Coordinate>> result = new ArrayList<>();
        for (List<Coordinate> coordinate : coordinates) {
            List<Coordinate> newLeaf = new ArrayList<>();
            for (Coordinate c : coordinate) {
                Coordinate north = c.move(Direction.NORTH);
                if (north.isInside(characters) && Character.isDigit(north.getValue(characters)) && Character.getNumericValue(north.getValue(characters)) == nextHigh) {
                    newLeaf.add(north);
                }

                Coordinate east = c.move(Direction.EAST);
                if (east.isInside(characters) && Character.isDigit(east.getValue(characters)) && Character.getNumericValue(east.getValue(characters)) == nextHigh) {
                    newLeaf.add(east);
                }

                Coordinate south = c.move(Direction.SOUTH);
                if (south.isInside(characters) && Character.isDigit(south.getValue(characters)) && Character.getNumericValue(south.getValue(characters)) == nextHigh) {
                    newLeaf.add(south);
                }

                Coordinate west = c.move(Direction.WEST);
                if (west.isInside(characters) && Character.isDigit(west.getValue(characters)) && Character.getNumericValue(west.getValue(characters)) == nextHigh) {
                    newLeaf.add(west);
                }

            }
            result.add(newLeaf);


        }

        nextHigh++;
        return recursive(result, nextHigh, characters);
    }


    private List<Coordinate> getStartingPoints(char[][] characters) {
        List<Coordinate> coordinates = new ArrayList<>();

        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                if (characters[y][x] == '0') {
                    coordinates.add(Coordinate.of(x, y));
                }
            }
        }
        return coordinates;
    }
}
