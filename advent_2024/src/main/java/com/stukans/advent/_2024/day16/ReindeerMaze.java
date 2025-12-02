package com.stukans.advent._2024.day16;

import com.stukans.advent._2024.Coordinate;
import com.stukans.advent._2024.Direction;
import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;

class ReindeerMaze extends Puzzle {

    public static void main(String[] args) {
        char[][] array = new char[][]{
                "###############".toCharArray(),
                "#.....#.#.#.#.#".toCharArray(),
                "#.###.#.#.#.#.#".toCharArray(),
                "#S..#E#...#...#".toCharArray(),
                "###############".toCharArray()
        };

        ReindeerMaze maze = new ReindeerMaze();
        System.out.println(maze.solve(array));
        ;

    }

    @Override
    public long solution1(File file) {

        char[][] characters = asCharacters(file);
        return solve(characters);
    }

    private long solve(char[][] characters) {
        Coordinate start = findFirst(characters, character -> character == 'S');
        Coordinate end = findFirst(characters, character -> character == 'E');

        Queue<Pair<Coordinate, Direction>> queue = new LinkedList<>();
        queue.add(Pair.of(start, Direction.EAST));

        Map<Coordinate, Long> map = new HashMap<>();
        map.put(start, 0L);

        Map<Coordinate, Set<Direction>> visited = new HashMap<>();
        Set<Direction> objects = new HashSet<>();
        objects.add(Direction.WEST);
        visited.put(start, objects);
        Set<Coordinate> rotate = new HashSet<>();
        printWithCoordinatesPredicate(characters);

        while (!queue.isEmpty()) {
            Pair<Coordinate, Direction> poll = queue.poll();

            Direction current = poll.getRight();
            Coordinate location = poll.getLeft();
            System.out.println(map.get(location));
            printWithCoordinatesPredicate(characters, it -> it.equals(location));
            System.out.println();
            for (Direction direction : current.further()) {
                Coordinate move = location.move(direction);

                printWithCoordinatesPredicate(characters, it -> it.equals(location), it -> it.equals(move));
                if (move.equals(characters, '#')) {
                    continue;
                }


                if (visited.containsKey(move) && visited.get(move).contains(direction.opposite())) {
                    continue;
                }

                if (!move.equals(end)) {
                    queue.add(Pair.of(move, direction));
                }

                Long scores = map.get(location);
                if (current != direction) {
                    
                }
//                if (map.containsKey(move)) {
//                    if (map.get(move) > ) {
//                        map.put(move, );
//                    }
//                } else {
//                    map.put(move, );
//                }

                visited.compute(move, (coordinate, directions) -> {
                    if (directions == null) {
                        directions = new HashSet<>();
                    }
                    directions.add(direction);
                    return directions;
                });
            }

        }

        printWithCoordinatesPredicate(characters, it -> rotate.contains(it));

//        Pair<Long, Long> longLongPair = map.get(end);
//        Long l = longLongPair.getLeft();
        return -1;
    }



}
