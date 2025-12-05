package com.stukans.advent._2025.day5;

import com.stukans.advent._2025.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Stream;

class CafeteriaTest {
    private static final String folder = "/2025/day5";

    private static Stream<Arguments> puzzle1() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 3)
                , Arguments.of(folder + "/input.txt", 513)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle1")
    void puzzle1(String path, long output) throws IOException {
        URL url = Cafeteria.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new Cafeteria();

        Assertions.assertEquals(output, puzzle.solution1(file));
    }

    private static Stream<Arguments> puzzle2() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 14)
                , Arguments.of(folder + "/input.txt", 339668510830757L)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle2")
    void puzzle2(String path, long output) throws IOException {
        URL url = Cafeteria.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new Cafeteria();

        Assertions.assertEquals(output, puzzle.solution2(file));
    }
}