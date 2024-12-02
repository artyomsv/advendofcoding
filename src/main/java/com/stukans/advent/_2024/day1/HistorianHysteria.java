package com.stukans.advent._2024.day1;

import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HistorianHysteria extends Puzzle {

    @Override
    public long solution1(File file) {
        Integer[][] numbers = asNumbers(file, 2);
        Integer[][] rotate = rotate(numbers, Integer.class);

        Integer[] array1 = Arrays.stream(rotate[0]).sorted().toArray(Integer[]::new);
        Integer[] array2 = Arrays.stream(rotate[1]).sorted().toArray(Integer[]::new);

        if (array1.length != array2.length) {
            throw new IllegalArgumentException("Arrays are not equal");
        }

        long result = 0;
        for (int i = 0; i < array1.length; i++) {
            result += Math.abs(array1[i] - array2[i]);
        }

        return result;
    }

    @Override
    public long solution2(File file) {
        Integer[][] numbers = asNumbers(file, 2);
        Integer[][] rotate = rotate(numbers, Integer.class);

        Integer[] array1 = Arrays.stream(rotate[0]).sorted().toArray(Integer[]::new);
        Map<Integer, Long> map = Arrays.stream(rotate[1]).sorted().collect(Collectors.groupingBy(Integer::valueOf, Collectors.counting()));

        long result = 0;
        for (Integer i : array1) {
            result += i * map.getOrDefault(i, 0L);
        }
        return result;
    }
}
