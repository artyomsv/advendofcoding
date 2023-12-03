package com.stukans.advent.day2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class CubeConundrumTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Day2");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("==========");
    }


    @Test
    void testPossibleGamesSum() throws IOException {
        URL url = CubeConundrumTest.class.getResource("/puzzle2/data.txt");
        List<String> input = Files.readAllLines(Path.of(url.getPath()));
        System.out.println("Part1: " + new CubeConundrum().findPossibleGamesSum(input));
    }

    @Test
    void testPossibleGamesPower() throws IOException {
        URL url = CubeConundrumTest.class.getResource("/puzzle2/data.txt");
        List<String> input = Files.readAllLines(Path.of(url.getPath()));
        System.out.println("Part2: " + new CubeConundrum().totalPower(input));
    }

}