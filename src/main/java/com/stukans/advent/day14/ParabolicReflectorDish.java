package com.stukans.advent.day14;

import com.stukans.advent.Puzzle;

import java.util.*;

public class ParabolicReflectorDish extends Puzzle {

    public long solve(List<String> input, int cycles) {
        char[][] matrix = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        //printTheMatrix(matrix);
        if (cycles == -1) {
            tiltNorth(matrix);
            printTheMatrix(matrix);
            return weight(matrix);
        }

        for (int i = 0; i < cycles; i++) {
            tiltNorth(matrix);
            tiltWest(matrix);
            tiltSouth(matrix);
            tiltEast(matrix);
            //System.out.printf("%d%c", weight, i % 7 == 0 ? '\n' : ' ');
        }

        return weight(matrix);
    }

    public static List<List<Integer>> generateAllSubsequences(List<Integer> list) {
        List<List<Integer>> subsequences = new ArrayList<>();
        int n = list.size();

        // There are 2^n possible subsequences
        for (int i = 0; i < (1 << n); i++) { // equivalent to 2^n
            List<Integer> subsequence = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                // Check if the j-th bit in i is set (which means include the element in the subsequence)
                if ((i & (1 << j)) != 0) {
                    subsequence.add(list.get(j));
                }
            }

            subsequences.add(subsequence);
        }

        return subsequences;
    }

    public static List<List<Integer>> findRepetitiveSubsequences(List<Integer> list, int subsequenceLength) {
        if (subsequenceLength <= 0 || list.size() < 2 * subsequenceLength) {
            return java.util.Collections.emptyList();
        }

        Map<String, List<Integer>> sequences = new HashMap<>();
        List<List<Integer>> repetitiveSubsequences = new ArrayList<>();
        Set<String> addedHashes = new HashSet<>();

        for (int i = 0; i <= list.size() - subsequenceLength; i++) {
            List<Integer> subsequence = list.subList(i, i + subsequenceLength);
            String hash = subsequence.toString(); // Using string representation as hash for simplicity.

            if (!sequences.containsKey(hash)) {
                sequences.put(hash, new ArrayList<>(subsequence));
            } else if (addedHashes.add(hash)) {
                repetitiveSubsequences.add(subsequence);
            }
        }

        return repetitiveSubsequences;
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
