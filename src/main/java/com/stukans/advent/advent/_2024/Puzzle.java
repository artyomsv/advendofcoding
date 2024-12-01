package com.stukans.advent.advent._2024;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Puzzle {


    public abstract long solution(File file);

    protected char[] convert(Character[] array) {
        Arrays.stream(array)

    }

    protected List<String[]> load(final File file, int elements) {
        return load(file).stream().map(it -> {
            String[] split = it.split(" ");
            if (split.length != elements) {
                throw new IllegalArgumentException("Wrong number of elements");
            }
            return split;
        }).toList();
    }

    protected List<String> load(final File file) {
        List<String> lines = new ArrayList<String>();
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
