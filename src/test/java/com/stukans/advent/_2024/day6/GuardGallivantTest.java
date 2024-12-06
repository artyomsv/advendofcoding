package com.stukans.advent._2024.day6;

import com.stukans.advent._2024.Coordinates;
import com.stukans.advent._2024.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Stream;

class GuardGallivantTest {

    private static final String folder = "/2024/day6";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 41)
                , Arguments.of(folder + "/input.txt", 4711)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output1) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new GuardGallivant();

        Assertions.assertEquals(output1, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 6)
                , Arguments.of(folder + "/input.txt", 1562)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new GuardGallivant();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

    private static Stream<Arguments> inputs3() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", Coordinates.of(4, 5), false)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(4, 4), false)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(4, 3), false)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(3, 6), true)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(2, 6), false)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(6, 7), true)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(7, 7), true)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(1, 8), true)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(3, 8), true)
                ,Arguments.of(folder + "/test1.txt", Coordinates.of(7, 9), true)
//                , Arguments.of(folder + "/input.txt", -1)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs3")
    void loops(String path, Coordinates obstruction, boolean expected) {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        GuardGallivant guardGallivant = new GuardGallivant();

        char[][] characters = guardGallivant.asCharacters(file);
        boolean looped = guardGallivant.isLooped(characters, guardGallivant.getStartingPoint(characters).get(), obstruction);
        Assertions.assertEquals(expected, looped);
    }
}