package com.stukans.advent._2024.day8;

import com.stukans.advent._2024.Coordinate;
import com.stukans.advent._2024.Permutations;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResonantCollinearity extends Puzzle {

    @Override
    public long solution1(File file) {
        char[][] characters = asCharacters(file);

        Map<Character, List<Coordinate>> map = collectAntenasCoordinates(characters);

        Set<Coordinate> uniqueCoordinates = new HashSet<>();

        for (Map.Entry<Character, List<Coordinate>> entry : map.entrySet()) {
            List<Coordinate> coordinates = entry.getValue();
            List<Set<Coordinate>> permutation = Permutations.permutationUnique(coordinates, 2, Coordinate.class);
            for (Set<Coordinate> set : permutation) {
                Iterator<Coordinate> iterator = set.iterator();
                Coordinate firstAntena = iterator.next();
                Coordinate secondAntena = iterator.next();
                if (iterator.hasNext()) {
                    throw new IllegalArgumentException("Expeted maximum 2 elements");
                }

                int xDistance = Math.abs(firstAntena.x() - secondAntena.x());
                int yDistance = Math.abs(firstAntena.y() - secondAntena.y());

                int firstAntinodeX;
                int secondAntinodeX;
                if (firstAntena.x() < secondAntena.x()) {
                    firstAntinodeX = firstAntena.x() - xDistance;
                    secondAntinodeX = secondAntena.x() + xDistance;
                } else if (firstAntena.x() == secondAntena.x()) {
                    firstAntinodeX = firstAntena.x();
                    secondAntinodeX = secondAntena.x();
                } else {
                    firstAntinodeX = firstAntena.x() + xDistance;
                    secondAntinodeX = secondAntena.x() - xDistance;
                }

                int firstAntinodeY;
                int secondAntinodeY;
                if (firstAntena.y() < secondAntena.y()) {
                    firstAntinodeY = firstAntena.y() - yDistance;
                    secondAntinodeY = secondAntena.y() + yDistance;
                } else if (firstAntena.y() == secondAntena.y()) {
                    firstAntinodeY = firstAntena.y();
                    secondAntinodeY = secondAntena.y();
                } else {
                    firstAntinodeY = firstAntena.y() + yDistance;
                    secondAntinodeY = secondAntena.y() - yDistance;
                }

                int yMax = characters[0].length;
                int xMax = characters.length;

                Coordinate firstAntinode = Coordinate.of(firstAntinodeX, firstAntinodeY);
                if (firstAntinode.isInside(xMax, yMax)) {
                    uniqueCoordinates.add(firstAntinode);
                    if (characters[firstAntinode.y()][firstAntinode.x()] == '.') {
                        characters[firstAntinode.y()][firstAntinode.x()] = '#';
                    }
                }


                Coordinate secondAntinode = Coordinate.of(secondAntinodeX, secondAntinodeY);
                if (secondAntinode.isInside(xMax, yMax)) {
                    uniqueCoordinates.add(secondAntinode);
                    if (characters[secondAntinode.y()][secondAntinode.x()] == '.') {
                        characters[secondAntinode.y()][secondAntinode.x()] = '#';
                    }
                }
            }
        }

        printWithCharacterPredicates(characters, character -> character == '#', character -> character == 'A', character -> character == '0');
        return uniqueCoordinates.size();
    }

    private Map<Character, List<Coordinate>> collectAntenasCoordinates(char[][] characters) {
        Map<Character, List<Coordinate>> map = new HashMap<>();
        for (int y = 0; y < characters.length; y++) {
            final int yC = y;
            for (int x = 0; x < characters[y].length; x++) {
                char c = characters[y][x];
                if (c == '.') {
                    continue;
                }

                final int xC = x;
                map.compute(c, (character, coordinates) -> {
                    if (coordinates == null) {
                        coordinates = new LinkedList<>();
                    }
                    coordinates.add(Coordinate.of(xC, yC));
                    return coordinates;
                });
            }
        }
        return map;
    }

    @Override
    public long solution2(File file) {
        char[][] characters = asCharacters(file);

        Map<Character, List<Coordinate>> map = collectAntenasCoordinates(characters);

        Set<Coordinate> uniqueCoordinates = new HashSet<>();

        for (Map.Entry<Character, List<Coordinate>> entry : map.entrySet()) {
            Character key = entry.getKey();
            if (key == 'T') {
            }
            List<Coordinate> coordinates = entry.getValue();
            uniqueCoordinates.addAll(coordinates);
            List<Set<Coordinate>> permutation = Permutations.permutationUnique(coordinates, 2, Coordinate.class);
            for (Set<Coordinate> set : permutation) {
                Iterator<Coordinate> iterator = set.iterator();
                Coordinate firstAntena = iterator.next();
                Coordinate secondAntena = iterator.next();
                if (iterator.hasNext()) {
                    throw new IllegalArgumentException("Expeted maximum 2 elements");
                }

                int xDistance = Math.abs(firstAntena.x() - secondAntena.x());
                int yDistance = Math.abs(firstAntena.y() - secondAntena.y());

                uniqueCoordinates = extracted(firstAntena, secondAntena, xDistance, yDistance, characters, uniqueCoordinates);


            }
        }

        printWithCharacterPredicates(characters, character -> character == '#', character -> character == 'A', character -> character == '0');
        return uniqueCoordinates.size();
    }

    private static Set<Coordinate> extracted(Coordinate firstAntena, Coordinate secondAntena, int xDistance, int yDistance, char[][] characters, Set<Coordinate> uniqueCoordinates) {
        int yMax = characters[0].length;
        int xMax = characters.length;
        if (!firstAntena.isInside(yMax, characters.length) && !secondAntena.isInside(yMax, xMax)) {
            return uniqueCoordinates;
        }

        int firstAntinodeX;
        int secondAntinodeX;
        if (firstAntena.x() < secondAntena.x()) {
            firstAntinodeX = firstAntena.x() - xDistance;
            secondAntinodeX = secondAntena.x() + xDistance;
        } else if (firstAntena.x() == secondAntena.x()) {
            firstAntinodeX = firstAntena.x();
            secondAntinodeX = secondAntena.x();
        } else {
            firstAntinodeX = firstAntena.x() + xDistance;
            secondAntinodeX = secondAntena.x() - xDistance;
        }

        int firstAntinodeY;
        int secondAntinodeY;
        if (firstAntena.y() < secondAntena.y()) {
            firstAntinodeY = firstAntena.y() - yDistance;
            secondAntinodeY = secondAntena.y() + yDistance;
        } else if (firstAntena.y() == secondAntena.y()) {
            firstAntinodeY = firstAntena.y();
            secondAntinodeY = secondAntena.y();
        } else {
            firstAntinodeY = firstAntena.y() + yDistance;
            secondAntinodeY = secondAntena.y() - yDistance;
        }

        Coordinate firstAntinode = Coordinate.of(firstAntinodeX, firstAntinodeY);
        if (firstAntinode.isInside(xMax, yMax)) {
            uniqueCoordinates.add(firstAntinode);
            if (characters[firstAntinode.y()][firstAntinode.x()] == '.') {
                characters[firstAntinode.y()][firstAntinode.x()] = '#';
            }
        }


        Coordinate secondAntinode = Coordinate.of(secondAntinodeX, secondAntinodeY);
        if (secondAntinode.isInside(xMax, yMax)) {
            uniqueCoordinates.add(secondAntinode);
            if (characters[secondAntinode.y()][secondAntinode.x()] == '.') {
                characters[secondAntinode.y()][secondAntinode.x()] = '#';
            }
        }

        return extracted(firstAntinode, secondAntinode, xDistance, yDistance, characters, uniqueCoordinates);
    }
}
