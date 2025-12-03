package com.stukans.advent._2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InvalidIdIdentifierTest {

    private static Stream<Arguments> arguments1() {
        return Stream.of(
                Arguments.of("11", true)
                , Arguments.of("22", true)
                , Arguments.of("333", false)
                , Arguments.of("1333", false)
                , Arguments.of("3133", false)
                , Arguments.of("3313", false)
                , Arguments.of("111", false)
                , Arguments.of("12312", false)
                , Arguments.of("1212", true)
                , Arguments.of("123123", true)
                , Arguments.of("123123123123", true)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments1")
    void invalidIdIdentifier1(String number, boolean notValid) throws IOException {
        Assertions.assertEquals(notValid, InvalidIdIdentifier.isInvalid1(number));
    }

    private static Stream<Arguments> arguments2() {
        return Stream.of(
                Arguments.of("11", true)
                , Arguments.of("22", true)
                , Arguments.of("99", true)
                , Arguments.of("111", true)
                , Arguments.of("999", true)
                , Arguments.of("1010", true)
                , Arguments.of("1188511885", true)
                , Arguments.of("222222", true)
                , Arguments.of("446446", true)
                , Arguments.of("38593859", true)
                , Arguments.of("565656", true)
                , Arguments.of("824824824", true)
                , Arguments.of("2121212121", true)
                , Arguments.of("12", false)
                , Arguments.of("13", false)
                , Arguments.of("100", false)
                , Arguments.of("1002", false)
                , Arguments.of("1188511884", false)
                , Arguments.of("1698524", false)
                , Arguments.of("38593859", true)
                , Arguments.of("1", false)
                , Arguments.of("2", false)
                , Arguments.of("3", false)
                , Arguments.of("4", false)
                , Arguments.of("5", false)
                , Arguments.of("6", false)
                , Arguments.of("7", false)
                , Arguments.of("8", false)
                , Arguments.of("9", false)
                , Arguments.of("10", false)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments2")
    void invalidIdIdentifier2(String number, boolean notValid) throws IOException {
        Assertions.assertEquals(notValid, InvalidIdIdentifier.isInvalid2(number));
    }

    @Test
    void name() {
        assertArrayEquals(new String[]{"11885", "11885"}, InvalidIdIdentifier.splitIntoChunks(new StringBuilder("1188511885"), 2));
        assertArrayEquals(new String[]{"12", "12", "12", "12"}, InvalidIdIdentifier.splitIntoChunks(new StringBuilder("12121212"), 4));
        assertArrayEquals(new String[]{"12", "12", "12"}, InvalidIdIdentifier.splitIntoChunks(new StringBuilder("121212"), 3));
        assertArrayEquals(new String[]{"121", "121"}, InvalidIdIdentifier.splitIntoChunks(new StringBuilder("121121"), 2));
    }
}