package com.stukans.advent._2024;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.Arrays;

public abstract class Puzzle {


    public long solution1(File file) {
        return 0;
    }

    public long solution2(File file) {
        return 0;
    }

    protected char[] convert(Character[] array) {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].charValue();
        }
        return result;
    }

    protected Character[] convert(char[] array) {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    protected <T> T[][] rotate(T[][] array, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[][] rotatedMatrix = (T[][]) Array.newInstance(clazz, array[0].length, array.length);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                rotatedMatrix[j][i] = array[i][j];
            }
        }

        return rotatedMatrix;

    }

    protected Integer[][] asNumbers(File file, int elements) {
        return Arrays.stream(load(file, elements))
                .map(it -> Arrays.stream(it).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);
    }

    protected String[][] load(final File file, int elements) {
        return Arrays.stream(load(file)).map(it -> {
            String[] split = Arrays.stream(it.split(" ")).filter(s -> !s.isBlank()).toArray(String[]::new);
            if (split.length != elements) {
                throw new IllegalArgumentException("Wrong number of elements");
            }
            return split;
        }).toArray(String[][]::new);
    }

    protected String[] load(final File file) {
        try {
            return Files.readAllLines(file.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
