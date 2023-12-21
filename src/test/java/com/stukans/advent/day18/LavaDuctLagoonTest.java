package com.stukans.advent.day18;

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

class LavaDuctLagoonTest {

    private static final String folder = "/puzzle18";

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of(folder + "/short1.txt", 62)
                , Arguments.of(folder + "/data.txt", 0)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output) throws IOException {
        URL url = LavaDuctLagoonTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new LavaDuctLagoon().solve(input);
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
        URL url = LavaDuctLagoonTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new LavaDuctLagoon().solve(input, null);
        Assertions.assertEquals(output, result);
    }


}