package com.stukans.advent._2024;

import org.junit.jupiter.api.Test;

class PuzzleTest {

    @Test
    void name() {
        Puzzle puzzle = new Puzzle() {
        };

        Integer[][] rotate = puzzle.rotate(new Integer[][]{{1, 2}, {3, 4}, {5, 6}}, Integer.class);
        System.out.println();
    }
}