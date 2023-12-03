package com.stukans.advent.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class GearRatiosTest {

    @Test
    void test() {

        char[][] input = new char[][]{
                "........11".toCharArray(),
                "467..114..".toCharArray(),
                "...*......".toCharArray(),
                "..35..633.".toCharArray(),
                "......#...".toCharArray(),
                "617*......".toCharArray(),
                ".....+.58.".toCharArray(),
                "..592.....".toCharArray(),
                "......755.".toCharArray(),
                "...$.*....".toCharArray(),
                ".664.598..".toCharArray()
        };


        Assertions.assertEquals(4361, new GearRatios().solve(input));
    }

    @Test
    void testPossibleGamesPower() throws IOException {
        URL url = GearRatiosTest.class.getResource("/puzzle3/data.txt");
        List<String> input = Files.readAllLines(Path.of(url.getPath()));
        char[][] array = input.stream().map(String::toCharArray).toArray(char[][]::new);
        System.out.println("Part1: " + new GearRatios().solve(array));
    }

}