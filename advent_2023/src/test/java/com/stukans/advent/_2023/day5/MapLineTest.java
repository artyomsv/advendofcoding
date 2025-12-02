package com.stukans.advent._2023.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MapLineTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(50, 98, 5, 5, -1),
                Arguments.of(50, 98, 5, 49, -1),
                Arguments.of(50, 98, 5, 56, -1),
                Arguments.of(50, 98, 5, 98, 50),
                Arguments.of(50, 98, 5, 99, 51),
                Arguments.of(50, 98, 5, 100, 52),
                Arguments.of(50, 98, 5, 101, 53),
                Arguments.of(50, 98, 5, 102, 54),
                Arguments.of(50, 98, 5, 55, -1),
                Arguments.of(50, 98, 5, 0, -1)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int destinationRangeStart, int sourceRangeStart, int rangeLength, int seed, int location) {
        Assertions.assertEquals(location, new MapLine(destinationRangeStart, sourceRangeStart, rangeLength).location(seed));
    }


    private static Stream<Arguments> arguments2() {
        return Stream.of(
                Arguments.of(50, 98, 5, 5, 5),
                Arguments.of(50, 98, 5, 49, 49),
                Arguments.of(50, 98, 5, 56, 56),
                Arguments.of(50, 98, 5, 98, 50),
                Arguments.of(50, 98, 5, 99, 51),
                Arguments.of(50, 98, 5, 100, 52),
                Arguments.of(50, 98, 5, 101, 53),
                Arguments.of(50, 98, 5, 102, 54),
                Arguments.of(50, 98, 5, 55, 55),
                Arguments.of(50, 98, 5, 0, 0)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments2")
    void test2(int destinationRangeStart, int sourceRangeStart, int rangeLength, int seed, int location) {
        Assertions.assertEquals(seed, new MapLine(destinationRangeStart, sourceRangeStart, rangeLength).seed(location));
    }

}