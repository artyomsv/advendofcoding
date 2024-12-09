package com.stukans.advent._2024.day9;

import com.stukans.advent._2024.Puzzle;

import java.io.File;

public class DiskFragmenter extends Puzzle {

    @Override
    public long solution1(File file) {
        long answer = 0;

        String[] load = load(file);
        if (load.length > 1) {
            throw new RuntimeException("Too many loads!");
        }
        char[] charArray = load[0].toCharArray();



        return answer;
    }
}
