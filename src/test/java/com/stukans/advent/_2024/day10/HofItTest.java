package com.stukans.advent._2024.day10;

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

class HofItTest {

    private static final String folder = "/2024/day10";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test2.txt", 2)
                , Arguments.of(folder + "/test3.txt", 3)
                , Arguments.of(folder + "/test4.txt", 4)
                , Arguments.of(folder + "/test36.txt", 36)
                , Arguments.of(folder + "/input.txt", 531)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new HofIt();

        Assertions.assertEquals(output, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test2_2.txt", 3)
                , Arguments.of(folder + "/test3_2.txt", 13)
                , Arguments.of(folder + "/test4_2.txt", 227)
                , Arguments.of(folder + "/test36.txt", 81)
                , Arguments.of(folder + "/input.txt", 1210)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new HofIt();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

}