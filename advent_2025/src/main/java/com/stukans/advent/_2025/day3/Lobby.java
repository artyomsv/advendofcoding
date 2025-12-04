package com.stukans.advent._2025.day3;

import com.stukans.advent._2025.Puzzle;

import java.io.File;

class Lobby extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] strings = load(file);

        long result = 0;
        for (int y = 0; y < strings.length; y++) {
            char[] arr = strings[y].toCharArray();
            Result first = getResult(arr, 0, arr.length - 2);
            Result second = getResult(arr, first.index + 1, arr.length -1);
            System.out.println(first.value + " " + second.value);
            result += Long.parseLong(String.valueOf(first.value) + second.value);
        }

        return result;
    }

    @Override
    public long solution2(File file) {
        String[] strings = load(file);

        long result = 0;
        for (int y = 0; y < strings.length; y++) {
            char[] arr = strings[y].toCharArray();
            Result r1 = getResult(arr, 0, arr.length - 12);
            Result r2 = getResult(arr, r1.index + 1, arr.length - 11);
            Result r3 = getResult(arr, r2.index + 1, arr.length - 10);
            Result r4 = getResult(arr, r3.index + 1, arr.length - 9);
            Result r5 = getResult(arr, r4.index + 1, arr.length - 8);
            Result r6 = getResult(arr, r5.index + 1, arr.length - 7);
            Result r7 = getResult(arr, r6.index + 1, arr.length - 6);
            Result r8 = getResult(arr, r7.index + 1, arr.length - 5);
            Result r9 = getResult(arr, r8.index + 1, arr.length - 4);
            Result r10 = getResult(arr, r9.index + 1, arr.length - 3);
            Result r11 = getResult(arr, r10.index + 1, arr.length - 2);
            Result r12 = getResult(arr, r11.index + 1, arr.length - 1);
            String string = new StringBuilder().append(r1.value)
                    .append(r2.value)
                    .append(r3.value)
                    .append(r4.value)
                    .append(r5.value)
                    .append(r6.value)
                    .append(r7.value)
                    .append(r8.value)
                    .append(r9.value)
                    .append(r10.value)
                    .append(r11.value)
                    .append(r12.value)
                    .toString();
            //System.out.println(string);
            result += Long.parseLong(string);
        }

        return result;
    }

    private static Result getResult(char[] arr, int from, int to) {
        char first = arr[from];
        int firstIndex = from;
        for (int x = from + 1; x <= to; x++) {
            char next = arr[x];
            if (next > first) {
                first = next;
                firstIndex = x;
            }
        }
        return new Result(first, firstIndex);
    }

    private record Result(char value, int index) {
    }
}
