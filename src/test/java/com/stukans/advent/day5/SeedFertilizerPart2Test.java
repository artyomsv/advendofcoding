package com.stukans.advent.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

class SeedFertilizerPart2Test {

    @Test
    void example() throws IOException {
        URL url = SeedFertilizerPart2Test.class.getResource("/puzzle5/short.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());

        long result = new SeedFertilizer_Part2().solve(input);
        System.out.println("Part2: " + result);
        Assertions.assertEquals(46, result);
    }

    @Test
    void real() throws IOException {
        URL url = SeedFertilizerPart2Test.class.getResource("/puzzle5/data.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());

        long result = new SeedFertilizer_Part2().solve(input);
        System.out.println("Part2: " + result);
        Assertions.assertEquals(219529182, result);
    }


}