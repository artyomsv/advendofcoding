package com.stukans.advent._2024.day2;

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

class RedNosedReportsTest {

    private static final String folder = "/2024/day2";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 2)
                , Arguments.of(folder + "/input.txt", 371)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output1) throws IOException {
        URL url = RedNosedReportsTest.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new RedNosedReports();

        Assertions.assertEquals(output1, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 4)
                , Arguments.of(folder + "/input.txt", 426)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = RedNosedReportsTest.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new RedNosedReports();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

    private static Stream<Arguments> inputs3() {
        return Stream.of(
                Arguments.of(new Integer[]{38, 41, 44, 47, 50, 47}, false)
                ,Arguments.of(new Integer[]{-1, 41, 44, 47, 50, 47}, false)
                ,Arguments.of(new Integer[]{38, -1, 44, 47, 50, 47}, false)
                ,Arguments.of(new Integer[]{38, 41, -1, 47, 50, 47}, false)
                ,Arguments.of(new Integer[]{38, 41, 44, -1, 50, 47}, false)
                ,Arguments.of(new Integer[]{38, 41, 44, 47, -1, 47}, false)
                ,Arguments.of(new Integer[]{38, 41, 44, 47, 50, -1}, true)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs3")
    void puzzle3(Integer[] numbers, boolean isCorrect) throws IOException {
        RedNosedReports puzzle = new RedNosedReports();

        Assertions.assertEquals(isCorrect, puzzle.isCorrect(numbers).isCorrect(), "Solution 2 not correct");
    }


}