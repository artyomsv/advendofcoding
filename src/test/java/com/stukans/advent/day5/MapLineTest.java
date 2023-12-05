package com.stukans.advent.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class MapLineTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(50, 98, 2, List.of(new Pair(50, 98), new Pair(51, 99))),
                Arguments.of(0, 3, 3, List.of(new Pair(0, 3), new Pair(1, 4), new Pair(2, 5)))

        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int destinationRangeStart, int sourceRangeStart, int rangeLength, List<Pair> output) {
        Assertions.assertEquals(output, new MapLine(destinationRangeStart, sourceRangeStart, rangeLength).pairs());
    }

}