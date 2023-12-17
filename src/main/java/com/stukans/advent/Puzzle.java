package com.stukans.advent;

import java.util.List;

public abstract class Puzzle<T> {

    public abstract long solve(List<String> input);

    public abstract long solve(List<String> input, T t);

    protected void printTheMatrix(char[][] matrix) {
        System.out.println();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }
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

    public static class Pair<T, E> {

        final T t;
        final E e;

        public static <E, T> Pair<E, T> of(E e, T t) {
            return new Pair<>(e, t);
        }

        private Pair(T t, E e) {
            this.t = t;
            this.e = e;
        }
    }

}
