package com.stukans.advent._2024;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class PermutationsTest {
    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(1, 2)
                , Arguments.of(2, 4)
                , Arguments.of(3, 8)
                , Arguments.of(4, 16)
                , Arguments.of(5, 32)
                , Arguments.of(6, 64)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void name(int depth, int combinations) {
        List<Character> operators = List.of('+', '*');
        List<List<Character>> permutation = Permutations.permutation(operators, depth, Character.class);
        Assertions.assertEquals(combinations, permutation.size());
        System.out.println();
    }

}