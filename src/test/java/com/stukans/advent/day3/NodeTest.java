package com.stukans.advent.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NodeTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new char[][]{
                                ".321......".toCharArray(),
                                "..........".toCharArray()
                        }, 3, 0, 321, true
                ),
                Arguments.of(
                        new char[][]{
                                "...124....".toCharArray(),
                                "..........".toCharArray()
                        }, 3, 0, 124, false
                ),
                Arguments.of(
                        new char[][]{
                                "1.........".toCharArray(),
                                "..........".toCharArray()
                        }, 0, 0, 1, true
                ),
                Arguments.of(
                        new char[][]{
                                "1.........".toCharArray(),
                                "..........".toCharArray()
                        }, 0, 0, 1, false
                ),
                Arguments.of(
                        new char[][]{
                                ".........1".toCharArray(),
                                "..........".toCharArray()
                        }, 9, 0, 1, true
                ),
                Arguments.of(
                        new char[][]{
                                ".........1".toCharArray(),
                                "..........".toCharArray()
                        }, 9, 0, 1, false
                )
        );

    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void test(char[][] input, int x, int y, Integer output, boolean traverseLeft) {
        Assertions.assertEquals(String.valueOf(output), new Node('!', new Location(4, 1)).traverse(input, x, y, traverseLeft));
    }

}