package com.stukans.advent._2024.day12;

import com.stukans.advent._2024.Coordinates;
import com.stukans.advent._2024.Direction;
import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class GardenGroups extends Puzzle {

    public static void main(String[] args) {

    }

    @Override
    public long solution1(File file) {

        char[][] characters = asCharacters(file);

        List<Pair<Character, Set<Coordinates>>> gardens = new ArrayList<>();

        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                Coordinates point = Coordinates.of(x, y);
                if (checkIfCoordinateAlreadyBelongToAnotherGarden(gardens, point)) {
                    continue;
                }
                Set<Coordinates> visited = floodFill(point, characters, point.getValue(characters), Direction.EAST);
                if (!visited.isEmpty()) {
                    gardens.add(Pair.of(point.getValue(characters), visited));
                }
                //printWithCoordinatesPredicate(characters, visited::contains);

            }

        }

        long answer = 0;

        for (Pair<Character, Set<Coordinates>> entry : gardens) {
            Set<Coordinates> garden = entry.getRight();
            Collection<Coordinates> border = new ArrayList<>();
            //System.out.println(entry.getLeft() + ":");
            for (Coordinates coordinate : garden) {
                Collection<Coordinates> neigbours = getValidNeigbours(coordinate.neighbors(), garden);
                //System.out.println(coordinate + ": " + neigbours.size() + " | " + neigbours);
                border.addAll(neigbours);
            }
            long multiplication = (long) garden.size() * border.size();
            //System.out.println(entry.getLeft() + " plants:" + garden.size() + " borders: " + border.size() + " result: " + multiplication);
            answer += multiplication;
        }


        //System.out.println("Found: " + gardens.size() + " gardens");
        return answer;
    }

    private Collection<Coordinates> getValidNeigbours(Collection<Coordinates> neighbours, Set<Coordinates> garden) {
        List<Coordinates> coordinates = new ArrayList<>();
        for (Coordinates neighbour : neighbours) {
            if (!garden.contains(neighbour)) {
                coordinates.add(neighbour);
            }
        }
        return coordinates;
    }

    private boolean checkIfCoordinateAlreadyBelongToAnotherGarden(List<Pair<Character, Set<Coordinates>>> gardens, Coordinates point) {
        for (Pair<Character, Set<Coordinates>> garden : gardens) {
            if (garden.getRight().contains(point)) {
                return true;
            }
        }
        return false;
    }

}
