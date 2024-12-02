package com.stukans.advent._2023.day12;

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

class HotSpringsTest {

    private static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of("/2023/puzzle12/short1.txt", 21, 1)
                , Arguments.of("/2023/puzzle12/short1.txt", 525152, 5)
                , Arguments.of("/2023/puzzle12/data.txt", 7633, 1)
                //, Arguments.of("/puzzle12/data.txt", 0, 5)

        );
    }

    @ParameterizedTest
    @MethodSource("games")
    void example(String file, long output, int unfolding) throws IOException {
        URL url = HotSpringsTest.class.getResource(file);
        String path = Objects.requireNonNull(url).getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        long result = new HotSprings().solve(input, unfolding);
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> springs() {
        return Stream.of(
                Arguments.of("???.###", new Integer[]{1, 1, 3}, 1L, 1)
                , Arguments.of("???.###?", new Integer[]{1, 1, 3}, 1L, 1)
                , Arguments.of("???.###", new Integer[]{1, 1, 3}, 1L, 2)
//                , Arguments.of("???.###", new Integer[]{1, 1, 3}, 1L, 3)
//                , Arguments.of("???.###", new Integer[]{1, 1, 3}, 1L, 4)
//                , Arguments.of("???.###", new Integer[]{1, 1, 3}, 1L, 5)

                , Arguments.of(".??..??...?##.", new Integer[]{1, 1, 3}, 4L, 1)
                , Arguments.of(".??..??...?##.", new Integer[]{1, 1, 3}, 32L, 2)
//                , Arguments.of(".??..??...?##.", new Integer[]{1, 1, 3}, 256L, 3)
//                , Arguments.of(".??..??...?##.", new Integer[]{1, 1, 3}, 2048L, 4)
//                , Arguments.of(".??..??...?##.", new Integer[]{1, 1, 3}, 16384L, 5)

                , Arguments.of("?#?#?#?#?#?#?#?", new Integer[]{1, 3, 1, 6}, 1L, 1)
                , Arguments.of("?#?#?#?#?#?#?#?", new Integer[]{1, 3, 1, 6}, 1L, 2)
//                , Arguments.of("?#?#?#?#?#?#?#?", new Integer[]{1, 3, 1, 6}, 1L, 3)
//                , Arguments.of("?#?#?#?#?#?#?#?", new Integer[]{1, 3, 1, 6}, 1L, 4)
//                , Arguments.of("?#?#?#?#?#?#?#?", new Integer[]{1, 3, 1, 6}, 1L, 5)

                , Arguments.of("????.#...#...", new Integer[]{4, 1, 1}, 1L, 1)
                , Arguments.of("????.#...#...", new Integer[]{4, 1, 1}, 2L, 2)
//                , Arguments.of("????.#...#...", new Integer[]{4, 1, 1}, 4L, 3)
//                , Arguments.of("????.#...#...", new Integer[]{4, 1, 1}, 8L, 4)
//                , Arguments.of("????.#...#...", new Integer[]{4, 1, 1}, 16L, 5)

                , Arguments.of("????.######..#####.", new Integer[]{1, 6, 5}, 4L, 1)
                , Arguments.of("????.######..#####.", new Integer[]{1, 6, 5}, 20L, 2)
//                , Arguments.of("????.######..#####.", new Integer[]{1, 6, 5}, 100L, 3)
//                , Arguments.of("????.######..#####.", new Integer[]{1, 6, 5}, 500L, 4)
//                , Arguments.of("????.######..#####.", new Integer[]{1, 6, 5}, 2500L, 5)

                , Arguments.of("?###????????", new Integer[]{3, 2, 1}, 10L, 1)
                , Arguments.of("?###????????", new Integer[]{3, 2, 1}, 150L, 2)
//                , Arguments.of("?###????????", new Integer[]{3, 2, 1}, 2250L, 3)
//                , Arguments.of("?###????????", new Integer[]{3, 2, 1}, 33750L, 4)
//                , Arguments.of("?###????????", new Integer[]{3, 2, 1}, 506250L, 5)

                //, Arguments.of("???#?????#?.#???#???", new Integer[]{9, 7}, 506250L, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("springs")
    void springs(String springs, Integer[] groups, Long result, int unfolded) {
        Assertions.assertEquals(result, new HotSprings().permutationsValues(springs, groups, unfolded).size());
    }

    private static Stream<Arguments> permutations() {
        return Stream.of(
                Arguments.of("?", new Integer[]{1}, List.of("#"), 1)
                , Arguments.of("??", new Integer[]{1}, List.of("#.", ".#"), 1)
                , Arguments.of("??", new Integer[]{1}, List.of("#.#..", "#..#.", "#...#", ".#.#.", ".#..#", "..#.#"), 2)
                , Arguments.of("??", new Integer[]{2}, List.of("##"), 1)
                , Arguments.of("???", new Integer[]{1, 1}, List.of("#.#"), 1)
                , Arguments.of("???", new Integer[]{2}, List.of("##.", ".##"), 1)
                , Arguments.of("????", new Integer[]{2}, List.of("##..", ".##.", "..##"), 1)
                , Arguments.of("????", new Integer[]{2, 1}, List.of("##.#"), 1)
                , Arguments.of("????", new Integer[]{1, 2}, List.of("#.##"), 1)
                , Arguments.of("????", new Integer[]{3}, List.of("###.", ".###"), 1)
                , Arguments.of("??????", new Integer[]{3}, List.of("###...", ".###..", "..###.", "...###"), 1)

                , Arguments.of("#?", new Integer[]{1}, List.of("#."), 1)
                , Arguments.of("#?", new Integer[]{1}, List.of("#..#."), 2)//"#??#?"
                , Arguments.of("#?", new Integer[]{1}, List.of("#..#..#."), 3)//"#??#??#?"
                , Arguments.of("?#", new Integer[]{1}, List.of(".#"), 1)
                , Arguments.of("#?", new Integer[]{2}, List.of("##"), 1)
                , Arguments.of("?#", new Integer[]{2}, List.of("##"), 1)
                , Arguments.of("?#???", new Integer[]{2}, List.of("##...", ".##.."), 1)
                , Arguments.of("???#?", new Integer[]{2}, List.of("..##.", "...##"), 1)
                , Arguments.of("?#?#?", new Integer[]{3}, List.of(".###."), 1)
                , Arguments.of("?##?##?", new Integer[]{2, 2}, List.of(".##.##."), 1)
                , Arguments.of("?##?##?", new Integer[]{3, 3}, List.of("###.###"), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("permutations")
    void permutations(String springs, Integer[] groups, List<String> result, int unfold) {
        Assertions.assertEquals(result, new HotSprings().permutationsValues(springs, groups, unfold));
    }

}