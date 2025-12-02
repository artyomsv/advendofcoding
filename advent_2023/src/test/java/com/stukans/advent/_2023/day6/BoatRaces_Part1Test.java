package com.stukans.advent._2023.day6;

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

class BoatRaces_Part1Test {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("/2023/puzzle6/short.txt", 288),
                Arguments.of("/2023/puzzle6/short2.txt", 71503),
                Arguments.of("/2023/puzzle6/data.txt", 2269432),
                Arguments.of("/2023/puzzle6/data2.txt", 35865985)

        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void example(String file, long output) throws IOException {
        URL url = BoatRaces_Part1Test.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new BoatRaces_Part1().solve(input);
        System.out.println("Part: " + result);
        Assertions.assertEquals(output, result);
    }

}