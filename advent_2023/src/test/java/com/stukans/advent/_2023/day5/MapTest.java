package com.stukans.advent._2023.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class MapTest {

    private static final List<MapLine> SOIL = List.of(
            new MapLine(50, 98, 2),
            new MapLine(52, 50, 48)
    );
    private static final List<MapLine> FERTILIZER = List.of(
            new MapLine(0, 15, 37),
            new MapLine(37, 52, 2),
            new MapLine(39, 0, 15)
    );
    private static final List<MapLine> WATER = List.of(
            new MapLine(49, 53, 8),
            new MapLine(0, 11, 42),
            new MapLine(42, 0, 7),
            new MapLine(57, 7, 4)
    );

    private static final List<MapLine> LIGHT = List.of(
            new MapLine(88, 18, 7),
            new MapLine(18, 25, 70)
    );

    private static final List<MapLine> TEMPERATURE = List.of(
            new MapLine(45, 77, 23),
            new MapLine(81, 45, 19),
            new MapLine(68, 64, 13)
    );

    private static final List<MapLine> HUMIDITY = List.of(
            new MapLine(0, 69, 1),
            new MapLine(1, 0, 69)
    );

    private static final List<MapLine> LOCATION = List.of(
            new MapLine(60, 56, 37),
            new MapLine(56, 93, 4)
    );

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("1", SOIL, 79, 81),
                Arguments.of("1", SOIL, 14, 14),
                Arguments.of("1", SOIL, 55, 57),
                Arguments.of("1", SOIL, 13, 13),

                Arguments.of("1", FERTILIZER, 81, 81),
                Arguments.of("1", FERTILIZER, 14, 53),
                Arguments.of("1", FERTILIZER, 57, 57),
                Arguments.of("1", FERTILIZER, 13, 52),

                Arguments.of("1", WATER, 81, 81),
                Arguments.of("1", WATER, 53, 49),
                Arguments.of("1", WATER, 57, 53),
                Arguments.of("1", WATER, 52, 41),

                Arguments.of("1", LIGHT, 81, 74),
                Arguments.of("1", LIGHT, 49, 42),
                Arguments.of("1", LIGHT, 53, 46),
                Arguments.of("1", LIGHT, 41, 34),

                Arguments.of("1", TEMPERATURE, 74, 78),
                Arguments.of("1", TEMPERATURE, 42, 42),
                Arguments.of("1", TEMPERATURE, 46, 82),
                Arguments.of("1", TEMPERATURE, 34, 34),

                Arguments.of("1", HUMIDITY, 78, 78),
                Arguments.of("1", HUMIDITY, 42, 43),
                Arguments.of("1", HUMIDITY, 82, 82),
                Arguments.of("1", HUMIDITY, 34, 35),

                Arguments.of("1", LOCATION, 78, 82),
                Arguments.of("1", LOCATION, 43, 43),
                Arguments.of("1", LOCATION, 82, 86),
                Arguments.of("1", LOCATION, 35, 35)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String type, List<MapLine> lines, long seed, long location) {
        Assertions.assertEquals(location, new Map(type, lines).calculate(seed));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test2(String type, List<MapLine> lines, long seed, long location) {
        Assertions.assertEquals(seed, new Map(type, lines).reverseCalculate(location));
    }

    @Test
    void test() {
        new Map("test", FERTILIZER).mapping();
    }

}