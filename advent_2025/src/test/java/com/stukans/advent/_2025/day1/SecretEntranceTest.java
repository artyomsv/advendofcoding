package com.stukans.advent._2025.day1;

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

class SecretEntranceTest {

    private static final String folder = "/2025/day1";

    private static Stream<Arguments> puzzle1() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 3)
                , Arguments.of(folder + "/input.txt", 982)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle1")
    void puzzle1(String path, long output) throws IOException {
        URL url = SecretEntrance.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new SecretEntrance();

        Assertions.assertEquals(output, puzzle.solution1(file));
    }

    private static Stream<Arguments> puzzle2() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 6)
                , Arguments.of(folder + "/input.txt", 6106)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle2")
    void puzzle2(String path, long output) throws IOException {
        URL url = SecretEntrance.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new SecretEntrance();

        Assertions.assertEquals(output, puzzle.solution2(file));
    }

}