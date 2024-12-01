package com.stukans.advent._2023.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RaceTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(7, 9, 4),
                Arguments.of(15, 40, 8),
                Arguments.of(30, 200, 9)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int time, int destination, int result) {
        Assertions.assertEquals(result, new Race(time, destination).calculate());
    }

}