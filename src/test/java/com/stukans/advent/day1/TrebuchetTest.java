package com.stukans.advent.day1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class TrebuchetTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Day1");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("==========");
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(List.of(new String[]{"1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"}), 142, false),
                Arguments.of(List.of(new String[]{"1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"}), 142, true),
                Arguments.of(List.of(new String[]{"two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"}), 209, false),
                Arguments.of(List.of(new String[]{"two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"}), 281, true)
        );
    }

    @ParameterizedTest
    @MethodSource("input")
    void testInputLine(List<String> input, Integer result, Boolean replace) {
        Assertions.assertEquals(result, new Trebuchet().solve(input, replace));
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("1", 11),
                Arguments.of("12", 12),
                Arguments.of("11", 11),
                Arguments.of("av1nb3", 13),
                Arguments.of("12345", 15),
                Arguments.of("a1b2c3d4e5f6r", 16),
                Arguments.of("avbg7vbnn7", 77),
                Arguments.of("avbgonevbnthreen", 13),
                Arguments.of("oneone", 11),
                Arguments.of("daaonefdjfjbhszero", 10),

                Arguments.of("eightwo", 82),
                Arguments.of("two1nine", 29),
                Arguments.of("eightwothree", 83),
                Arguments.of("abcone2threexyz", 13),
                Arguments.of("xtwone3four", 24),
                Arguments.of("4nineeightseven2", 42),
                Arguments.of("zoneight234", 14),
                Arguments.of("7pqrstsixteen", 76),
                Arguments.of("xgdmcrgfkqeightsevensevenflzvzcss1", 81)
        );

    }

    @ParameterizedTest
    @MethodSource("arguments")
    void testParsingFunction(String input, Integer result) {
        Assertions.assertEquals(result, new Trebuchet().apply(input, true));
    }

    @Test
    void testNoReplacement() throws IOException {
        URL url = this.getClass().getResource("/puzzle1/data.txt");
        String path = url.getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        System.out.println("Part1: " + new Trebuchet().solve(input, false));
    }

    @Test
    void testWithReplacement() throws IOException {
        URL url = this.getClass().getResource("/puzzle1/data.txt");
        String path = url.getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        System.out.println("Part2: " + new Trebuchet().solve(input, true));
    }


}
