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
    void example() throws IOException {
        URL url = SeedFertilizerTest.class.getResource("/puzzle5/short.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result1 = new SeedFertilizer().part1(input);
        System.out.println("Part1: " + result1);
        Assertions.assertEquals(35, result1);

        long result2 = new SeedFertilizer().part2(input);
        System.out.println("Part2: " + result2);
        Assertions.assertEquals(46, result2);
    }

    @Test
    void real() throws IOException {
        URL url = SeedFertilizerTest.class.getResource("/puzzle5/data.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new SeedFertilizer().part1(input);
        System.out.println("Part1: " + result);
        Assertions.assertEquals(403695602, result);

        long result2 = new SeedFertilizer().part2(input);
        System.out.println("Part2: " + result2);
        Assertions.assertEquals(219529182, result2);
    }


}