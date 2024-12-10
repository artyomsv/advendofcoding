package com.stukans.advent._2024.day9;

import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class DiskFragmenterTest {

    private static final String folder = "/2024/day9";

    private static Stream<Arguments> inputs1() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 1928)
                , Arguments.of(folder + "/input.txt", 6299243228569L)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs1")
    void puzzle1(String path, long output) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new DiskFragmenter();

        Assertions.assertEquals(output, puzzle.solution1(file), "Solution 1 not correct");
    }

    private static Stream<Arguments> inputs2() {
        return Stream.of(
                Arguments.of(folder + "/test1.txt", 2858)
                , Arguments.of(folder + "/input.txt", 6326952672104L)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs2")
    void puzzle2(String path, long output2) throws IOException {
        URL url = this.getClass().getResource(path);
        File file = new File(Objects.requireNonNull(url).getFile());
        Assertions.assertTrue(file.exists());

        Puzzle puzzle = new DiskFragmenter();

        Assertions.assertEquals(output2, puzzle.solution2(file), "Solution 2 not correct");
    }

    private static Stream<Arguments> inputs3() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 1, -1, -1, 1, 1), 2, Pair.of(3, 4))
                , Arguments.of(List.of(1, 1, 1, -1, -1, -1, 1), 2, Pair.of(3, 4))
                , Arguments.of(List.of(1, 1, -1, -1, -1, -1, 1), 2, Pair.of(2, 3))
                , Arguments.of(List.of(-1, 1, -1, -1, -1, -1, 1), 2, Pair.of(2, 3))
                , Arguments.of(List.of(-1, -1, -1, -1, -1, -1, 1), 2, Pair.of(0, 1))
                , Arguments.of(List.of(-1, -1, -1, 1, -1, -1, 1), 3, Pair.of(0, 2))
                , Arguments.of(List.of(-1, -1, -1, 1, -1, -1, 1), 1, Pair.of(0, 0))
                , Arguments.of(List.of(-1, -1, -1, 1, -1, -1, 1), 4, null)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs3")
    void findFreeBlockToTake(List<Integer> list, int size, Pair<Integer, Integer> expected) {
        DiskFragmenter fragmenter = new DiskFragmenter();
        Pair<Integer, Integer> pair = fragmenter.findFreeBlockToTake(list, size);
        Assertions.assertEquals(expected, pair);

    }

    private static Stream<Arguments> inputs4() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 1, -1, -1, 1, 1), Pair.of(5, 6))
                , Arguments.of(List.of(1, 1, 1, -1, -1, -1, 1), Pair.of(6, 6))
                , Arguments.of(List.of(1, 1, -1, -1, -1, 1, -1), Pair.of(5, 5))
                , Arguments.of(List.of(-1, 1, -1, -1, 1, 1, -1), Pair.of(4, 5))
                , Arguments.of(List.of(-1, -1, 1, 1, -1, -1, -1), Pair.of(2, 3))
                , Arguments.of(List.of(-1, 1, 1, 1, 1, -1, -1), Pair.of(1, 4))
                , Arguments.of(List.of(1, -1, -1, -1, -1, -1, -1), Pair.of(0, 0))
                , Arguments.of(List.of(-1, -1, -1, -1, -1, -1, -1), null)

        );
    }

    @ParameterizedTest
    @MethodSource("inputs4")
    void findFileBlockToTake(List<Integer> list, Pair<Integer, Integer> expected) {
        DiskFragmenter fragmenter = new DiskFragmenter();
        Pair<Integer, Integer> pair = fragmenter.findFileBlockToMove(list, list.size() - 1);
        Assertions.assertEquals(expected, pair);

    }

    private static Stream<Arguments> inputs5() {
        return Stream.of(
                Arguments.of(List.of(4, 3, 3, -1, 2, 1, 1),
                        List.of(Pair.of(5, 6), Pair.of(4, 4), Pair.of(1, 2), Pair.of(0, 0)))

        );
    }

    @ParameterizedTest
    @MethodSource("inputs5")
    void findFileBlocksToTake(List<Integer> list, List<Pair<Integer, Integer>> expected) {
        DiskFragmenter fragmenter = new DiskFragmenter();
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        int from = list.size() - 1;
        do {
            Pair<Integer, Integer> pair = fragmenter.findFileBlockToMove(list, from);
            pairs.add(pair);
            from = pair.getLeft() - 1;

        } while (from >= 0);
        Assertions.assertEquals(expected, pairs);

    }


}