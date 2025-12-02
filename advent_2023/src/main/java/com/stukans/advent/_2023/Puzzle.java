package com.stukans.advent._2023;

import java.util.Arrays;
import java.util.List;

public abstract class Puzzle<T> {

    public abstract long solve(List<String> input);

    public abstract long solve(List<String> input, T t);

    protected void printTheMatrix(int[][] matrix) {
        System.out.println();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }
    }

    protected void printTheMatrix(char[][] matrix) {
        System.out.println();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                char c = matrix[y][x];
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public int[][] copy(int[][] array) {
        int[][] newArray = new int[array.length][];
        for (int y = 0; y < array.length; y++) {
            newArray[y] = Arrays.copyOf(array[y], array[y].length);
        }
        return newArray;
    }

    public char[][] convert(int[][] array) {
        char[][] newArray = new char[array.length][];
        for (int y = 0; y < array.length; y++) {
            newArray[y] = new char[array[y].length];
            for (int x = 0; x < array[y].length; x++) {
                newArray[y][x] = (char) (array[y][x] + 48);
            }
        }
        return newArray;
    }

    protected char[][] convert(List<String> input) {
        char[][] arr = new char[input.size()][];
        for (int j = 0; j < input.size(); j++) {
            arr[j] = input.get(j).toCharArray();
        }
        return arr;
    }

    protected int[][] convertToIntArray(List<String> input) {
        int[][] arr = new int[input.size()][];
        for (int j = 0; j < input.size(); j++) {
            char[] charArray = input.get(j).toCharArray();
            int[] line = new int[charArray.length];
            arr[j] = line;
            for (int i = 0; i < charArray.length; i++) {
                line[i] = Character.digit(charArray[i], 10);
            }
        }
        return arr;
    }

    protected Character[] convert(char[] hands) {
        Character[] characterArray = new Character[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }

    protected char[] convert(Character[] hands) {
        char[] characterArray = new char[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }

}
