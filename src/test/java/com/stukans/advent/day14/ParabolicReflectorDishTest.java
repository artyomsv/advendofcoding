package com.stukans.advent.day14;

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

class ParabolicReflectorDishTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/puzzle14/short1.txt", 136, -1)
                //Arguments.of("/puzzle14/short1.txt", 136, 1000000000)
                , Arguments.of("/puzzle14/data.txt", 106648, -1)
//                 Arguments.of("/puzzle14/data.txt", 106648, 200)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, int cycles) throws IOException {
        URL url = ParabolicReflectorDishTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new ParabolicReflectorDish().solve(input, cycles);
        Assertions.assertEquals(output, result);
    }


}