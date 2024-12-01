package com.stukans.advent._2023.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

class ScratchcardsTest {

    @Test
    void test() throws IOException {
        URL url = ScratchcardsTest.class.getResource("/puzzle4/data.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        int result = new Scratchcards().part1(input);
        System.out.println("Part1: " + result);
        Assertions.assertEquals(25010, result);

        int result2 = new Scratchcards().part2(input);
        System.out.println("Part2: " + result2);
        Assertions.assertEquals(9924412, result2);
    }

    @Test
    void test2() throws IOException {
        URL url = ScratchcardsTest.class.getResource("/puzzle4/short.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());

        int result = new Scratchcards().part2(input);
        System.out.println("Part2: " + result);
        Assertions.assertEquals(30, result);
    }


}