package com.stukans.advent._2024.day7;

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

class BridgeRepairTest {

    private static final String folder = "/2024/day7";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 3749)
                , Arguments.of(folder + "/input.txt", 4998764814652L)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new BridgeRepair();

        Assertions.assertEquals(output, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 11387)
                , Arguments.of(folder + "/input.txt", 37598910447546L)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new BridgeRepair();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

    private static Stream<Arguments> inputs3() {
        return Stream.of(
                Arguments.of("190: 10 19", 190)
                , Arguments.of("3267: 81 40 27", 3267)
                , Arguments.of("83: 17 5", 0)
                , Arguments.of("156: 15 6", 0)
                , Arguments.of("7290: 6 8 6 15", 0)
                , Arguments.of("161011: 16 10 13", 0)
                , Arguments.of("192: 17 8 14", 0)
                , Arguments.of("21037: 9 7 18 13", 0)
                , Arguments.of("292: 11 6 16 20", 292)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs3")
    void puzzlePerLine(String input, int correct) throws IOException {
        BridgeRepair puzzle = new BridgeRepair();
        Assertions.assertEquals(correct, puzzle.evaluate(input, BridgeRepair.POSSIBLE_NODES_1));
    }

    private static Stream<Arguments> inputs4() {
        return Stream.of(
                Arguments.of("190: 10 19", 190)
                , Arguments.of("3267: 81 40 27", 3267)
                , Arguments.of("83: 17 5", 0)
                , Arguments.of("156: 15 6", 156)
                , Arguments.of("7290: 6 8 6 15", 7290)
                , Arguments.of("161011: 16 10 13", 0)
                , Arguments.of("192: 17 8 14", 192)
                , Arguments.of("21037: 9 7 18 13", 0)
                , Arguments.of("292: 11 6 16 20", 292)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs4")
    void puzzlePerLine2(String input, int correct) throws IOException {
        BridgeRepair puzzle = new BridgeRepair();
        Assertions.assertEquals(correct, puzzle.evaluate(input, BridgeRepair.POSSIBLE_NODES_2));
    }

}