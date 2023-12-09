package com.stukans.advent.day9;

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

class MirageMaintenanceTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle9/short.txt", 114L, true),
                Arguments.of("/puzzle9/short.txt", 2L, false),
                Arguments.of("/puzzle9/data.txt", 1581679977L, true),
                Arguments.of("/puzzle9/data.txt", 889L, false)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, boolean partOne) throws IOException {
        URL url = MirageMaintenanceTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new MirageMaintenance().solve(input, partOne);
        System.out.println("Part: " + result);
        Assertions.assertEquals(output, result);
    }

}