package com.stukans.advent._2023.day10;

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

class PipeMazeTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle10/short1.txt", 4, true)
                , Arguments.of("/puzzle10/short2.txt", 8, true)
                , Arguments.of("/puzzle10/short3.txt", 23, true)
                , Arguments.of("/puzzle10/short3.txt", 4, false)
                , Arguments.of("/puzzle10/short33.txt", 4, false)
                , Arguments.of("/puzzle10/short4.txt", 70, true)
                , Arguments.of("/puzzle10/short4.txt", 8, false)
                , Arguments.of("/puzzle10/short5.txt", 80, true)
                , Arguments.of("/puzzle10/short5.txt", 10, false)
                , Arguments.of("/puzzle10/data.txt", 6903, true)
                , Arguments.of("/puzzle10/data.txt", 265, false)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, boolean partOne) throws IOException {
        URL url = PipeMazeTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new PipeMaze().solve(input, partOne);
        Assertions.assertEquals(output, result);
    }

}