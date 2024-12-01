package com.stukans.advent._2023.day8;

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

class HauntedWastelandTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle8/short.txt", 2L, true),
                Arguments.of("/puzzle8/data.txt", 11567L, true),
                Arguments.of("/puzzle8/data.txt", 9858474970153L, false)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, boolean partOne) throws IOException {
        URL url = HauntedWastelandTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new HauntedWasteland().solve(input, partOne);
        System.out.println("Part: " + result);
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> argumentsPartOne() {
        return Stream.of(
                Arguments.of(
                        """
                                RL
                                                                
                                AAA = (BBB, CCC)
                                BBB = (DDD, EEE)
                                CCC = (ZZZ, GGG)
                                DDD = (DDD, DDD)
                                EEE = (EEE, EEE)
                                GGG = (GGG, GGG)
                                ZZZ = (ZZZ, ZZZ)
                                """, 2
                ),
                Arguments.of(
                        """
                                LLR
                                                                
                                AAA = (BBB, BBB)
                                BBB = (AAA, ZZZ)
                                ZZZ = (ZZZ, ZZZ)
                                """, 6
                )

        );
    }

    @ParameterizedTest
    @MethodSource("argumentsPartOne")
    void test1(String input, int rank) {
        Assertions.assertEquals(rank, new HauntedWasteland().solve(input.lines().toList(), true));
    }

    private static Stream<Arguments> argumentsPartTwo() {
        return Stream.of(
                Arguments.of(
                        """
                                LR
                                                                
                                11A = (11B, XXX)
                                11B = (XXX, 11Z)
                                11Z = (11B, XXX)
                                22A = (22B, XXX)
                                22B = (22C, 22C)
                                22C = (22Z, 22Z)
                                22Z = (22B, 22B)
                                XXX = (XXX, XXX)
                                """, 6
                )

        );
    }

    @ParameterizedTest
    @MethodSource("argumentsPartTwo")
    void test2(String input, int rank) {
        Assertions.assertEquals(rank, new HauntedWasteland().solve(input.lines().toList(), false));
    }

}