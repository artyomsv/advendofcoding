package com.stukans.advent.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GameTest {


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("41 48 83 86 17 | 83 86  6 31 17  9 48 53", 8),
                Arguments.of("13 32 20 16 61 | 61 30 68 82 17 32 24 19", 2),
                Arguments.of(" 1 21 53 59 44 | 69 82 63 72 16 21 14  1", 2),
                Arguments.of("41 92 73 84 69 | 59 84 76 51 58  5 54 83", 1),
                Arguments.of("87 83 26 28 32 | 88 30 70 12 93 22 82 36", 0),
                Arguments.of("31 18 13 56 72 | 74 77 10 23 35 67 36 11", 0)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String input, Integer points) {
        Assertions.assertEquals(points, new Game(1, input).scores());
    }

}