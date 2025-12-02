package com.stukans.advent._2024.day5;

import com.stukans.advent._2024.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class PrintQueueTest {

    private static final String folder = "/2024/day5";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 61)
                , Arguments.of(folder + "/test2.txt", 143)
                , Arguments.of(folder + "/input.txt", 4662)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output1) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new PrintQueue();

        Assertions.assertEquals(output1, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test2.txt", 123)
                , Arguments.of(folder + "/input.txt", 5900)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new PrintQueue();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }


    @Test
    void name() {
        PrintQueue printQueue = new PrintQueue();
        List<Integer> list = printQueue.getList("1,2,3,4,5");
        Assertions.assertIterableEquals(List.of(1, 2, 3, 4, 5), list);
    }

    @Test
    void middlePage() {
        Assertions.assertEquals(3, new PrintQueue.Pages(List.of(1, 2, 3, 4, 5)).middlePage());
        Assertions.assertEquals(4, new PrintQueue.Pages(List.of(1, 2, 3, 4, 5, 6, 7)).middlePage());
        Assertions.assertEquals(1, new PrintQueue.Pages(List.of(1)).middlePage());
        Assertions.assertEquals(2, new PrintQueue.Pages(List.of(1, 2, 3)).middlePage());
    }
}