package com.stukans.advent.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CubesSetTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("3 blue, 4 red", true),
                Arguments.of("1 red, 2 green, 6 blue", true),
                Arguments.of("2 green", true),
                Arguments.of("1 blue, 2 green", true),
                Arguments.of("8 green, 6 blue, 20 red", false),
                Arguments.of("3 green, 15 blue, 14 red", false),
                Arguments.of("12 red, 13 green, 14 blue", true),
                Arguments.of("13 red, 13 green, 14 blue", false),
                Arguments.of("12 red, 14 green, 14 blue", false),
                Arguments.of("12 red, 13 green, 15 blue", false)

        );

    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void test(String input, Boolean output) {
        Assertions.assertEquals(output, new CubesSet(input).isValid());
    }


}