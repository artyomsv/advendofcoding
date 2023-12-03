package com.stukans.advent.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

class GearRatiosTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..........".toCharArray()
                        }, 0
                ),

                Arguments.of(
                        new char[][]{
                                "11...*..11".toCharArray(),
                                "...&......".toCharArray()
                        }, 0
                ),

                Arguments.of(
                        new char[][]{
                                "..1.......".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!1......".toCharArray(),
                                "..........".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!12.....".toCharArray(),
                                "..........".toCharArray()
                        }, 12
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                "..1.......".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                ".1!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "41!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 41
                ),
                Arguments.of(
                        new char[][]{
                                ".1........".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "...1......".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                "...1......".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                ".1........".toCharArray()
                        }, 1
                ),
                Arguments.of(
                        new char[][]{
                                ".1.1......".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 2
                ),
                Arguments.of(
                        new char[][]{
                                ".111......".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 111
                ),
                Arguments.of(
                        new char[][]{
                                "..11......".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 11
                ),
                Arguments.of(
                        new char[][]{
                                "11.11.....".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 22
                ),
                Arguments.of(
                        new char[][]{
                                "11111.....".toCharArray(),
                                "..!.......".toCharArray(),
                                "..........".toCharArray()
                        }, 11111
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                ".11.......".toCharArray()
                        }, 11
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                ".111......".toCharArray()
                        }, 111
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                ".1.1......".toCharArray()
                        }, 2
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                ".1.11.....".toCharArray()
                        }, 12
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                "11.11.....".toCharArray()
                        }, 22
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                "11111.....".toCharArray()
                        }, 11111
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                ".111......".toCharArray()
                        }, 111
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                "..11......".toCharArray()
                        }, 11
                ),
                Arguments.of(
                        new char[][]{
                                "..........".toCharArray(),
                                "..!.......".toCharArray(),
                                "11........".toCharArray()
                        }, 11
                ),
                Arguments.of(
                        new char[][]{
                                "...100....".toCharArray(),
                                "..!.......".toCharArray(),
                                "10........".toCharArray()
                        }, 110
                ),
                Arguments.of(
                        new char[][]{
                                "......999...".toCharArray(),
                                "........*...".toCharArray(),
                                "........367.".toCharArray()
                        }, 1366
                ),
                Arguments.of(
                        new char[][]{
                                "........11".toCharArray(),
                                "467..114..".toCharArray(),
                                "...*......".toCharArray(),
                                "..35..633.".toCharArray(),
                                "......#...".toCharArray(),
                                "617*......".toCharArray(),
                                ".....+.58.".toCharArray(),
                                "..592.....".toCharArray(),
                                "......755.".toCharArray(),
                                "...$.*....".toCharArray(),
                                ".664.598..".toCharArray()
                        }, 4361
                )

        );

    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void test(char[][] input, Integer output) {
        Assertions.assertEquals(output, new GearRatios().solve(input, false));
    }


    @Test
    void test() throws IOException {
        URL url = GearRatiosTest.class.getResource("/puzzle3/data.txt");
        String path = url.getFile();
        List<String> input = Files.readAllLines(new File(path).toPath());
        char[][] array = input.stream().map(String::toCharArray).toArray(char[][]::new);
        int result = new GearRatios().solve(array, false);
        System.out.println("Part1: " + result);
        Assertions.assertEquals(528819, result);

        int result2 = new GearRatios().solve(array, true);
        System.out.println("Part2: " + result2);
        Assertions.assertEquals(80403602, result2);
    }

    @Test
    void testAsterixGears() {
        char[][] array = new char[][]{
                "467..114..".toCharArray(),
                "...*......".toCharArray(),
                "..35..633.".toCharArray(),
                "......#...".toCharArray(),
                "617*......".toCharArray(),
                ".....+.58.".toCharArray(),
                "..592.....".toCharArray(),
                "......755.".toCharArray(),
                "...$.*....".toCharArray(),
                ".664.598..".toCharArray()
        };
        int result = new GearRatios().solve(array, true);
        System.out.println("Part1: " + result);
        Assertions.assertEquals(467835, result);
    }


}