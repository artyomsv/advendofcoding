package com.stukans.advent._2024.day3;

import com.stukans.advent._2024.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Stream;

class MullItOverTest {

    private static final String folder = "/2024/day3";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 161)
                , Arguments.of(folder + "/input.txt", 181345830)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output1) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new MullItOver();

        Assertions.assertEquals(output1, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test2.txt", 48)
                , Arguments.of(folder + "/input.txt", 98729041)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new MullItOver();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

}