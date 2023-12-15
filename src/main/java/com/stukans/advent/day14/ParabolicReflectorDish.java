package com.stukans.advent.day14;

import com.stukans.advent.Puzzle;

import java.util.List;

public class ParabolicReflectorDish extends Puzzle {

    public long solve(List<String> input, int cycles) {
        char[][] matrix = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        printTheMatrix(matrix);
        if (cycles == -1) {
            tiltNorth(matrix);
            printTheMatrix(matrix);
            return weight(matrix);
        }

        int n, w, s, e;
        for (int i = 0; i < cycles; i++) {
            tiltNorth(matrix);
            tiltWest(matrix);
            tiltSouth(matrix);
            tiltEast(matrix);
        }

        return weight(matrix);
    }

    public void tiltSouth(char[][] matrix) {
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

    public void tiltEast(char[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = matrix[y].length - 1; x >= 0; x--) {
                if (matrix[y][x] == '.' ||
                        matrix[y][x] == '#' ||
                        (x < matrix[y].length - 1 && matrix[y][x + 1] == '#') ||
                        (x == matrix[y].length - 1 && matrix[y][x] == 'O')
                ) {
                    continue;
                }
                int xx = x;
                while (true) {
                    xx++;
                    if (xx > matrix[y].length - 1) {
                        break;
                    }
                    char location = matrix[y][xx];
                    if (location == '#' || location == 'O') {
                        break;
                    }
                }
                matrix[y][x] = '.';
                matrix[y][xx - 1] = 'O';

            }
        }

    }

    public void tiltWest(char[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == '.' ||
                        matrix[y][x] == '#' ||
                        (x > 0 && matrix[y][x - 1] == '#') ||
                        (x == 0 && matrix[y][x] == 'O')
                ) {
                    continue;
                }
                int xx = x;
                while (true) {
                    xx--;
                    if (xx < 0) {
                        break;
                    }
                    char location = matrix[y][xx];
                    if (location == '#' || location == 'O') {
                        break;
                    }
                }
                matrix[y][x] = '.';
                matrix[y][xx + 1] = 'O';

            }
        }

    }

    public void tiltNorth(char[][] matrix) {
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

    public int weight(char[][] matrix) {
        int weight = 0;
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
