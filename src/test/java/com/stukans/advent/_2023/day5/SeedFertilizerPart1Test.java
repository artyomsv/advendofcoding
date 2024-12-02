package com.stukans.advent._2023.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

class SeedFertilizerPart1Test {

    @Test
    void example() throws IOException {
        URL url = SeedFertilizerPart1Test.class.getResource("/2023/puzzle5/short.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result1 = new SeedFertilizer_Part1().solve(input);
        System.out.println("Part1: " + result1);
        Assertions.assertEquals(35, result1);
    }

    @Test
    void real() throws IOException {
        URL url = SeedFertilizerPart1Test.class.getResource("/2023/puzzle5/data.txt");
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new SeedFertilizer_Part1().solve(input);
        System.out.println("Part1: " + result);
        Assertions.assertEquals(403695602, result);
    }


}