package com.stukans.advent._2023.day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class CamelGameTest {
    private static Stream<Arguments> argumentsPartOne() {
        return Stream.of(
                Arguments.of("32T3K", 1),
                Arguments.of("T55J5", 3),
                Arguments.of("KK677", 2),
                Arguments.of("KTJJT", 2),
                Arguments.of("QQQJA", 3),
                Arguments.of("12111", 5),
                Arguments.of("33322", 4),
                Arguments.of("22222", 6)

        );
    }

    @ParameterizedTest
    @MethodSource("argumentsPartOne")
    void test1(String input, int rank) {
        Assertions.assertEquals(rank, new CamelGame(true).type(CamelGame.convert(input.toCharArray())));
    }

    private static Stream<Arguments> argumentsPartTwo() {
        return Stream.of(
                Arguments.of("32T3K", 1),
                Arguments.of("T55J5", 5),
                Arguments.of("KK677", 2),
                Arguments.of("KTJJT", 5),
                Arguments.of("QQQJA", 5),
                Arguments.of("1J111", 6),
                Arguments.of("JJJJJ", 6),
                Arguments.of("122J1", 4),
                Arguments.of("33322", 4),
                Arguments.of("33J22", 4),
                Arguments.of("33JJ2", 5),
                Arguments.of("3JJJ2", 5),
                Arguments.of("33JJJ", 6),
                Arguments.of("22222", 6)

        );
    }

    @ParameterizedTest
    @MethodSource("argumentsPartTwo")
    void test2(String input, int rank) {
        Assertions.assertEquals(rank, new CamelGame(false).typeWithJokers(CamelGame.convert(input.toCharArray())));
    }

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle7/short.txt", 6440, true),
                Arguments.of("/puzzle7/short.txt", 5905, false),
                Arguments.of("/puzzle7/data.txt", 251927063, true),
                Arguments.of("/puzzle7/data.txt", 255632664, false)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, boolean partOne) throws IOException {
        URL url = CamelGameTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new CamelGame(partOne).solve(input);
        System.out.println("Part: " + result);
        Assertions.assertEquals(output, result);
    }

}