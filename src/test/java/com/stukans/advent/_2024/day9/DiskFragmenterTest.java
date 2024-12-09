package com.stukans.advent._2024.day9;

import com.stukans.advent._2024.Puzzle;
import com.stukans.advent._2024.day8.ResonantCollinearity;
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

class DiskFragmenterTest {

    private static final String folder = "/2024/day9";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 1928)
                , Arguments.of(folder + "/input.txt", 6299243228569L)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new DiskFragmenter();

        Assertions.assertEquals(output, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 2858)
                , Arguments.of(folder + "/input.txt", -1)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new DiskFragmenter();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

//    private static Stream<Arguments> inputs3() {
//        return Stream.of(
//                Arguments.of("0..111....22222", "022111222......")
//                , Arguments.of("00...111...2...333.44.5555.6666.777.888899", "0099811188827773336446555566..............")
//
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("inputs3")
//    void puzzleFragment(String input, String expected) throws IOException {
//        DiskFragmenter puzzle = new DiskFragmenter();
//
//        StringBuilder builder = new StringBuilder(input);
//        Assertions.assertEquals(expected, puzzle.fragment(builder).toString());
//    }

//    private static Stream<Arguments> inputs4() {
//        return Stream.of(
//                Arguments.of("12345", "0..111....22222")
//                , Arguments.of("2333133121414131402", "00...111...2...333.44.5555.6666.777.888899")
//
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("inputs4")
//    void puzzleFragmentView(String input, String expected) throws IOException {
//        DiskFragmenter puzzle = new DiskFragmenter();
//        Assertions.assertEquals(expected, puzzle.buildFragmentedView(input.toCharArray()).toString());
//    }


}