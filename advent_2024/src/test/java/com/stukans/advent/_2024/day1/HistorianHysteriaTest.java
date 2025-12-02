package com.stukans.advent._2024.day1;

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

class HistorianHysteriaTest {

    private static final String folder = "/2024/day1";

    private static Stream<Arguments> puzzle1() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 11, 31)
                , Arguments.of(folder + "/input.txt", 1223326, 21070419)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle1")
    void puzzle1(String path, long output1, long output2) throws IOException {
        URL url = HistorianHysteriaTest.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new HistorianHysteria();

        Assertions.assertEquals(output1, puzzle.solution1(file));
        Assertions.assertEquals(output2, puzzle.solution2(file));
    }

}