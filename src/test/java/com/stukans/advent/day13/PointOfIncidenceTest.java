package com.stukans.advent.day13;

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

class PointOfIncidenceTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle13/short1.txt", 405, 1)
                , Arguments.of("/puzzle13/short2.txt", 8, 1)
                , Arguments.of("/puzzle13/data.txt", 7633, 1)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output) throws IOException {
        URL url = PointOfIncidenceTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new PointOfIncidence().solve(input);
        Assertions.assertEquals(output, result);
    }

}