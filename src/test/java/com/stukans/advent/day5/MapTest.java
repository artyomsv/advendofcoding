package com.stukans.advent.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class MapTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of()
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String type, List<MapLine> lines, int seed, int output) {
        Assertions.assertEquals(output, new Map(type, lines).calculate(seed));
    }

}