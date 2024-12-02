package com.stukans.advent._2023.day13;

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
                Arguments.of("/2023/puzzle13/short1.txt", 405, false)
//                Arguments.of("/puzzle13/short1.txt", 405, true)
                , Arguments.of("/2023/puzzle13/short2.txt", 8, false)
                , Arguments.of("/2023/puzzle13/short3.txt", 1800, false)
                , Arguments.of("/2023/puzzle13/short4.txt", 2, false)
                , Arguments.of("/2023/puzzle13/short5.txt", 1, false)
                , Arguments.of("/2023/puzzle13/short6.txt", 7, false)
                , Arguments.of("/2023/puzzle13/short7.txt", 7, false)
                , Arguments.of("/2023/puzzle13/short8.txt", 1, false)
                , Arguments.of("/2023/puzzle13/short9.txt", 500, false)
                , Arguments.of("/2023/puzzle13/short10.txt", 2, false)
                , Arguments.of("/2023/puzzle13/short11.txt", 1, false)
                , Arguments.of("/2023/puzzle13/short12.txt", 600, false)
                , Arguments.of("/2023/puzzle13/data.txt", 34821, false)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, boolean smudged) throws IOException {
        URL url = PointOfIncidenceTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new PointOfIncidence().solve(input, smudged);
        Assertions.assertEquals(output, result);
    }

}