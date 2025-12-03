package com.stukans.advent._2025.day2;

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

class GiftShopTest {
    private static final String folder = "/2025/day2";

    private static Stream<Arguments> puzzle1() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 1227775554L)
                , Arguments.of(folder + "/input.txt", 40214376723L)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle1")
    void puzzle1(String path, long output) throws IOException {
        URL url = GiftShop.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new GiftShop();

        Assertions.assertEquals(output, puzzle.solution1(file));
    }

    private static Stream<Arguments> puzzle2() {
        return Stream.of(
                Arguments.of(folder + "/test.txt", 4174379265L)
                , Arguments.of(folder + "/input.txt", 50793864718L)

        );
    }

    @ParameterizedTest
    @MethodSource("puzzle2")
    void puzzle2(String path, long output) throws IOException {
        URL url = GiftShop.class.getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new GiftShop();

        Assertions.assertEquals(output, puzzle.solution2(file));
    }

}