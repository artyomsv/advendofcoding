package com.stukans.advent.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

class SeedFertilizerTest {

    @Test
    void test() throws IOException {
        URL url = SeedFertilizerTest.class.getResource("/puzzle5/data.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        int result = new SeedFertilizer().part1(input);
        System.out.println("Part1: " + result);
        Assertions.assertEquals(25010, result);

        int result2 = new SeedFertilizer().part2(input);
        System.out.println("Part2: " + result2);
        Assertions.assertEquals(9924412, result2);
    }


}