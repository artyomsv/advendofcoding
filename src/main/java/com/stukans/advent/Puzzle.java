package com.stukans.advent;

public class Puzzle {

    protected void printTheMatrix(char[][] matrix) {
        System.out.println();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }
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
