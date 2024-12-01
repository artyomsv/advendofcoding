package com.stukans.advent._2023.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.stukans.advent._2023.day11.CosmicExpansion.Coordinates;

class CosmicExpansionTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle11/short1.txt", 374, 1)
                , Arguments.of("/puzzle11/short1.txt", 1030, 10)
                , Arguments.of("/puzzle11/short1.txt", 8410, 100)
                , Arguments.of("/puzzle11/short1.txt", 82000210, 1000000)
                , Arguments.of("/puzzle11/data.txt", 10165598, 1)
                , Arguments.of("/puzzle11/data.txt", 678728808158L, 1000000)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, int shift) throws IOException {
        URL url = CosmicExpansionTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new CosmicExpansion().solve(input, shift);
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> destinationArgs() {
        return Stream.of(
                Arguments.of(new Coordinates(0, 0), new Coordinates(4, 5), 9)
                , Arguments.of(new Coordinates(4, 5), new Coordinates(0, 0), 9)
                , Arguments.of(new Coordinates(1, 6), new Coordinates(5, 11), 9)
                , Arguments.of(new Coordinates(5, 11), new Coordinates(1, 6), 9)

                , Arguments.of(new Coordinates(0, 0), new Coordinates(5, 10), 15)
                , Arguments.of(new Coordinates(5, 10), new Coordinates(0, 0), 15)
                , Arguments.of(new Coordinates(0, 0), new Coordinates(12, 5), 17)
                , Arguments.of(new Coordinates(12, 5), new Coordinates(0, 0), 17)
                , Arguments.of(new Coordinates(0, 0), new Coordinates(5, 0), 5)
                , Arguments.of(new Coordinates(5, 0), new Coordinates(0, 0), 5)
                , Arguments.of(new Coordinates(0, 0), new Coordinates(0, 6), 6)
                , Arguments.of(new Coordinates(0, 6), new Coordinates(0, 0), 6)
                , Arguments.of(new Coordinates(4, 0), new Coordinates(0, 2), 6)
                , Arguments.of(new Coordinates(0, 2), new Coordinates(4, 0), 6)

        );
    }

    @ParameterizedTest
    @MethodSource("destinationArgs")
    void destination(Coordinates source, Coordinates destination, long distance) {
        Assertions.assertEquals(distance, source.distance(destination, Collections.emptySet(), Collections.emptySet(), 1));
    }

}