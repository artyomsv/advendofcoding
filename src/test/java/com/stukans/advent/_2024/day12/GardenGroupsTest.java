package com.stukans.advent._2024.day12;

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

class GardenGroupsTest {

    private static final String folder = "/2024/day12";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/small.txt", 140)
                , Arguments.of(folder + "/small2.txt", 772)
                , Arguments.of(folder + "/test.txt", 1930)
                , Arguments.of(folder + "/input.txt", 1489582)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new GardenGroups();

        Assertions.assertEquals(output, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/small.txt", 80)
                , Arguments.of(folder + "/small2.txt", 436)
                , Arguments.of(folder + "/small3.txt", 236)
                , Arguments.of(folder + "/small4.txt", 368)
                , Arguments.of(folder + "/test.txt", 1206)
                , Arguments.of(folder + "/input.txt", -1)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new GardenGroups();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

}