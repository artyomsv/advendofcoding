package com.stukans.advent._2024;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PuzzleTest {

    @Test
    void rotate90() {
        Puzzle puzzle = new Puzzle() {
        };
        Integer[][] rotate = puzzle.rotate90Clockwise(new Integer[][]{{1, 2}, {3, 4}, {5, 6}}, Integer.class);
        System.out.println();
    }

    @Test
    void rotate45Right() {
        Puzzle puzzle = new Puzzle() {
        };

        List<String> list = puzzle.rotate45Right(new char[][]{
                "1234".toCharArray(),
                "5678".toCharArray(),
                "9012".toCharArray(),
                "3456".toCharArray(),
        });

        Assertions.assertIterableEquals(
                List.of(
                        "1",
                        "52",
                        "963",
                        "3074",
                        "418",
                        "52",
                        "6"
                ),
                list
        );

    }

    @Test
    void rotate45Left() {
        Puzzle puzzle = new Puzzle() {
        };

        char[][] array = {
                "1234".toCharArray(),
                "5678".toCharArray(),
                "9012".toCharArray(),
                "3456".toCharArray(),
        };

        List<String> list = puzzle.rotate45Left(array);
        for (String s : list) {
            System.out.println(s);
        }
        Assertions.assertIterableEquals(
                List.of(
                        "4",
                        "83",
                        "272",
                        "6161",
                        "505",
                        "49",
                        "3"
                ),
                list
        );

    }

    @Test
    void lcm() {
        Puzzle puzzle = new Puzzle() {
        };

        System.out.println(puzzle.lcm(94L, 34L));
    }
}