package com.stukans.advent.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GearRatios {


    public int solve(char[][] input) {
        int result = 0;

        int[] locationStart = new int[2];
        int[] locationEnd = new int[2];

        boolean numberFound = false;
        List<Character> foundDigits = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                char current = input[i][j];

                if (current == '.') {
                    //System.out.print(current);
                    continue;
                }

                if (Character.isDigit(current) && !numberFound) {
                    numberFound = true;
                    foundDigits.add('.');
                    locationStart = new int[]{i, j};
                }
                //System.out.print(current);

                if (numberFound) {
                    foundDigits.add(current);
                }

                if (Character.isDigit(current) && numberFound && (j == input[i].length - 1 || !Character.isDigit(input[i][j + 1]))) {
                    //go around
                    locationEnd = new int[]{i, j};
                    numberFound = false;
                    foundDigits.add('.');
                    char[][] chars = buildDigitArrayBlock(foundDigits);
                    if (evaluate(chars, input, locationStart)) {
                        String number = foundDigits.stream()
                                .filter(it -> it != '.')
                                .map(character -> Character.digit(character, 10))
                                .map(String::valueOf)
                                .collect(Collectors.joining());
                        result += Integer.parseInt(number);
                    }
                    foundDigits.clear();
                }

            }

        }
        return result;
    }

    public boolean evaluate(char[][] block, char[][] input, int[] locationStart) {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                try {
                    int i1 = locationStart[0] - 1 + i;
                    int i2 = locationStart[1] - 1 + j;
                    char current = input[i1][i2];
                    block[i][j] = current;
                    if (!Character.isDigit(current) && current != '.') {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //System.out.println("Out of bound");
                }
            }

        }
        return false;
    }

    public static char[][] buildDigitArrayBlock(List<Character> number) {
        int height = 3;
        int width = number.size();
        char[][] block = new char[height][width];

        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                if (i != 1) {
                    block[i][j] = '.';
                } else {
                    block[1][j] = number.get(j);
                }
            }
        }
        //System.out.println(block);
        return block;
    }

    //public static boolean checkAround(int[][] start, int[][] end, char[][] array) {

    //}

}
