package com.stukans.advent._2023.day16;

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

class TheFloorWillBeLavaTest {

    private static final String folder = "/puzzle16";

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of(folder + "/short1.txt", 46)
                , Arguments.of(folder + "/data.txt", 6855)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output) throws IOException {
        URL url = TheFloorWillBeLavaTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new TheFloorWillBeLava().solve(input);
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> games2() {
        return Stream.of(
                Arguments.of(folder + "/short1.txt", 51)
                , Arguments.of(folder + "/data.txt", 7513)

        );
    }

    @ParameterizedTest
    @MethodSource("games2")
    void games2(String file, long output) throws IOException {
        URL url = TheFloorWillBeLavaTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new TheFloorWillBeLava().solve(input, -1L);
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> line() {
        return Stream.of(
                Arguments.of(
                        """
                                ...
                                ...
                                ...
                                """,
                        3)
                , Arguments.of(
                        """
                                ..\\
                                ...
                                ...
                                """,
                        5
                )
                , Arguments.of(
                        """
                                ./.
                                ...
                                ...
                                """,
                        2
                )
                , Arguments.of(
                        """
                                .\\.
                                /-.
                                ...
                                """,
                        6
                )
                , Arguments.of(
                        """
                                -.\\
                                ...
                                \\./
                                """,
                        8
                )
                , Arguments.of(
                        """
                                --\\
                                ...
                                --/
                                """,
                        7
                )
        );
    }

    @ParameterizedTest
    @MethodSource("line")
    void line(String input, int result) {
        Assertions.assertEquals(result, new TheFloorWillBeLava().solve(input.lines().toList()));
    }

}