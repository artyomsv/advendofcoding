package com.stukans.advent.day14;

import java.util.List;

public class ParabolicReflectorDish {

    public long solve(List<String> input) {
        char[][] matrix = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }

        rotateNorth(matrix);

        System.out.println();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }

        return weight(matrix);
    }

    public void rotateNorth(char[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                if (matrix[y][x] == '.' ||
                        matrix[y][x] == '#' ||
                        (y > 0 && matrix[y - 1][x] == '#') ||
                        (y == 0 && matrix[y][x] == 'O')
                ) {
                    continue;
                }

                int yy = y;
                while (true) {
                    yy--;
                    if (yy < 0) {
                        break;
                    }
                    char location = matrix[yy][x];
                    if (location == '#' || location == 'O') {
                        break;
                    }
                }

                matrix[y][x] = '.';
                matrix[yy + 1][x] = 'O';
            }

        }
    }

    public void rotateSouth(char[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = matrix.length - 1; y >= 0; y--) {
                if (matrix[y][x] == '.' ||
                        matrix[y][x] == '#' ||
                        (y < matrix.length - 1 && matrix[y + 1][x] == '#') ||
                        (y == matrix.length - 1 && matrix[y][x] == 'O')
                ) {
                    continue;
                }

                int yy = y;

                while (true) {
                    yy++;
                    if (yy > matrix.length - 1) {
                        break;
                    }
                    char location = matrix[yy][x];
                    if (location == '#' || location == 'O') {
                        break;
                    }
                }
                matrix[y][x] = '.';
                matrix[yy - 1][x] = 'O';
            }

        }
    }

    public long weight(char[][] matrix) {
        long weight = 0;
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                if (matrix[y][x] == 'O') {
                    weight += matrix.length - y;
                }
            }

        }
        return weight;
    }


}
