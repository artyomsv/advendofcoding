package com.stukans.advent._2023.day15;

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

class LensLibraryTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle15/short1.txt", 1320)
                , Arguments.of("/puzzle15/data.txt", 512797)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output) throws IOException {
        URL url = LensLibraryTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new LensLibrary().solve(input);
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> games2() {
        return Stream.of(
                Arguments.of("/puzzle15/short1.txt", 145)
                , Arguments.of("/puzzle15/data.txt", 262454)

        );
    }

    @ParameterizedTest
    @MethodSource("games2")
    void games2(String file, long output) throws IOException {
        URL url = LensLibraryTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new LensLibrary().solve(input, -1L);
        Assertions.assertEquals(output, result);
    }


    private static Stream<Arguments> line() {
        return Stream.of(
                Arguments.of("HASH", 52)
                , Arguments.of("rn=1", 30)
                , Arguments.of("cm-", 253)
                , Arguments.of("qp=3", 97)
                , Arguments.of("cm=2", 47)
                , Arguments.of("qp-", 14)
                , Arguments.of("pc=4", 180)
                , Arguments.of("ot=9", 9)
                , Arguments.of("ab=5", 197)
                , Arguments.of("pc-", 48)
                , Arguments.of("pc-", 48)
                , Arguments.of("pc=6", 214)
                , Arguments.of("ot=7", 231)
        );
    }

    @ParameterizedTest
    @MethodSource("line")
    void line(String input, int result) {
        Assertions.assertEquals(result, new LensLibrary().hash(input));
    }

}