package com.stukans.advent._2024;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

public abstract class Puzzle {

    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_GREEN = "\u001B[32m";
    protected static final String ANSI_PURPLE = "\u001B[35m";

    private static final List<String> colours = List.of(
            ANSI_RED, ANSI_GREEN, ANSI_PURPLE
    );

    protected final static Logger LOGGER = Logger.getLogger(Puzzle.class.getName());

    public Puzzle() {
    }

    public long solution1(File file) {
        return 0;
    }

    public long solution2(File file) {
        return 0;
    }

    public void printWithCoordinatesPredicate(char[][] array, Predicate<Coordinate>... predicates) {

        System.out.print("    ");
        for (int x = 0; x < array[0].length; x++) {
            System.out.print(x);
        }
        System.out.println();
        for (int y = 0; y < array.length; y++) {
            System.out.printf("%3d ", y);
            for (int x = 0; x < array[y].length; x++) {
                char c = array[y][x];

                if (predicates == null || predicates.length == 0) {
                    System.out.print(c);
                } else {
                    boolean handled = false;
                    Iterator<String> iterator = colours.iterator();
                    for (Predicate<Coordinate> predicate : predicates) {
                        String next = iterator.next();
                        if (predicate.test(Coordinate.of(x, y))) {
                            System.out.printf("%s%c%s", next, c, ANSI_RESET);
                            handled = true;
                            break;
                        }
                    }
                    if (!handled) {
                        System.out.print(c);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printWithIntegerPredicates(int[][] array, Predicate<Integer>... predicates) {

        System.out.print("    ");
        for (int x = 0; x < array[0].length; x++) {
            System.out.print(x);
        }
        System.out.println();
        for (int y = 0; y < array.length; y++) {
            System.out.printf("%3d ", y);
            for (int x = 0; x < array[y].length; x++) {
                int c = array[y][x];

                if (predicates == null || predicates.length == 0) {
                    System.out.print(c);
                } else {
                    boolean handled = false;
                    Iterator<String> iterator = colours.iterator();
                    for (Predicate<Integer> predicate : predicates) {
                        String next = iterator.next();
                        if (predicate.test(c)) {
                            System.out.printf("%s%d%s", next, c, ANSI_RESET);
                            handled = true;
                            break;
                        }
                    }
                    if (!handled) {
                        System.out.print(c);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printWithCharacterPredicates(char[][] array, Predicate<Character>... predicates) {

        System.out.print(" ");
        for (int x = 0; x < array[0].length; x++) {
            System.out.print(x);
        }
        System.out.println();
        for (int y = 0; y < array.length; y++) {
            System.out.print(y);
            for (int x = 0; x < array[y].length; x++) {
                char c = array[y][x];

                if (predicates == null || predicates.length == 0) {
                    System.out.print(c);
                } else {
                    boolean handled = false;
                    Iterator<String> iterator = colours.iterator();
                    for (Predicate<Character> predicate : predicates) {
                        String next = iterator.next();
                        if (predicate.test(c)) {
                            System.out.printf("%s%c%s", next, c, ANSI_RESET);
                            handled = true;
                            break;
                        }
                    }
                    if (!handled) {
                        System.out.print(c);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void print(List<Integer> values) {
        System.out.println(values);
    }

    public char[] convert(Character[] array) {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].charValue();
        }
        return result;
    }

    public Character[] convert(char[] array) {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public <T> T[][] rotate90Clockwise(T[][] array, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[][] rotatedMatrix = (T[][]) Array.newInstance(clazz, array[0].length, array.length);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                rotatedMatrix[j][i] = array[i][j];
            }
        }

        return rotatedMatrix;

    }

    public char[][] rotate90Clockwise(char[][] array) {
        @SuppressWarnings("unchecked")
        char[][] rotatedMatrix = new char[array[0].length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                rotatedMatrix[j][i] = array[i][j];
            }
        }

        return rotatedMatrix;
    }

    public char[][] rotate90CounterClockwise(char[][] array) {
        int m = array.length;
        int n = array[0].length;
        @SuppressWarnings("unchecked")
        char[][] rotatedMatrix = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotatedMatrix[n - 1 - j][i] = array[i][j];
            }
        }

        return rotatedMatrix;

    }


    public char[][] asCharacters(File file) {
        return Arrays.stream(load(file))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public Integer[][] asNumbers(File file) {
        return Arrays.stream(loadDimensional(file))
                .map(it -> Arrays.stream(it).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);
    }

    public Integer[][] asNumbers(File file, int elements) {
        return Arrays.stream(load(file, elements))
                .map(it -> Arrays.stream(it).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);
    }

    public String[][] load(final File file, int elements) {
        return Arrays.stream(load(file)).map(it -> {
            String[] split = Arrays.stream(it.split(" ")).filter(s -> !s.isBlank()).toArray(String[]::new);
            if (split.length != elements) {
                throw new IllegalArgumentException("Wrong number of elements");
            }
            return split;
        }).toArray(String[][]::new);
    }

    public String[][] loadDimensional(final File file) {
        Function<String, String[]> mapper = it -> Arrays
                .stream(it.split(" ")).filter(s -> !s.isBlank()).toArray(String[]::new);

        return Arrays.stream(load(file))
                .map(mapper).toArray(String[][]::new);
    }

    public String[] load(final File file) {
        try {
            return Files.readAllLines(file.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> rotate45Right(char[][] array) {
        int m = array.length;
        int n = array[0].length;
        int i_max = m - 1;
        int j_max = n - 1;

        int k = 0;

        StringBuilder builder = new StringBuilder(array.length);
        int i, j;
        List<String> list = new ArrayList<>();
        do {
            Pair<Integer, Integer> pair = cantorPairingFunction(k);
            i = pair.getLeft();
            j = pair.getRight();

            if ((i <= i_max) && (j <= j_max)) {
                builder.append(array[i][j]);
            }
            if (i == 0) {
                list.add(builder.toString());
                builder = new StringBuilder(array.length);
            }
            k++;
        } while ((i != i_max) || (j != j_max));

        list.add(builder.toString());
        return list;
    }

    public List<String> rotate45Left(char[][] array) {
        array = rotate90CounterClockwise(array);

        int m = array.length;
        int n = array[0].length;
        int i_max = m - 1;
        int j_max = n - 1;

        int k = 0;

        StringBuilder builder = new StringBuilder(array.length);
        int i, j;
        List<String> list = new ArrayList<>();
        do {
            Pair<Integer, Integer> pair = cantorPairingFunction(k);
            i = pair.getLeft();
            j = pair.getRight();

            if ((i <= i_max) && (j <= j_max)) {
                builder.append(array[i][j]);
            }
            if (i == 0) {
                list.add(builder.reverse().toString());
                builder = new StringBuilder(array.length);
            }
            k++;
        } while ((i != i_max) || (j != j_max));

        list.add(builder.reverse().toString());
        return list;
    }

    private Pair<Integer, Integer> cantorPairingFunction(int k) {
        double w = Math.floor((Math.sqrt(8 * k + 1) - 1) / 2);
        double t = (w * w + w) / 2;
        int j = (int) (k - t);
        int i = (int) (w - j);
        return Pair.of(i, j);
    }

    protected <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunkedList = new ArrayList<>();
        int listSize = list.size();

        for (int i = 0; i < listSize; i += chunkSize) {
            // Create a sublist from the original list
            List<T> chunk = list.subList(i, Math.min(listSize, i + chunkSize));
            // Add the sublist to the list of chunks
            chunkedList.add(new ArrayList<>(chunk));
        }

        return chunkedList;
    }

    public int counter = 0;

    protected Set<Coordinate> floodFill(Coordinate point, char[][] characters, char c, Direction from) {
        return floodFill(point, characters, c, from, new HashSet<>());
    }

    private Set<Coordinate> floodFill(Coordinate point, char[][] characters, char c, Direction from, Set<Coordinate> visited) {
        counter++;
        if (!point.isInside(characters) || !point.equals(characters, c) || visited.contains(point)) {
            return visited;
        }
        visited.add(point);
        Direction opposite = from.opposite();
        if (opposite != Direction.NORTH) {
            Coordinate move = point.move(Direction.NORTH);
            if (!visited.contains(move)) {
                floodFill(move, characters, c, Direction.NORTH, visited);
            }
        }
        if (opposite != Direction.EAST) {
            Coordinate move = point.move(Direction.EAST);
            if (!visited.contains(move)) {
                floodFill(move, characters, c, Direction.EAST, visited);
            }
        }

        if (opposite != Direction.SOUTH) {
            Coordinate move = point.move(Direction.SOUTH);
            if (!visited.contains(move)) {
                floodFill(move, characters, c, Direction.SOUTH, visited);
            }
        }

        if (opposite != Direction.WEST) {
            Coordinate move = point.move(Direction.WEST);
            if (!visited.contains(move)) {
                floodFill(move, characters, c, Direction.WEST, visited);
            }
        }

        return visited;
    }


    //Greatest Common Divisor
    protected long gcd(Long a, Long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    //Least Common Multiple
    protected long lcm(Long a, Long b) {
        return a * (b / gcd(a, b));
    }

    //Least Common Multiple
    protected long lcm(Long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    protected Coordinate findFirst(char[][] array, Predicate<Character> predicate) {
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                if (predicate.test(array[y][x])) {
                    return Coordinate.of(x, y);
                }
            }
        }
        throw new RuntimeException("Robot not found");
    }

    protected <T> List<Coordinate> findAll(T[][] array, Predicate<T> predicate) {
        List<Coordinate> list = new ArrayList<>();
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                if (predicate.test(array[y][x])) {
                    list.add(Coordinate.of(x, y));
                }
            }
        }
        return list;
    }

    protected <T> Set<Coordinate> findUnique(T[][] array, Predicate<T> predicate) {
        Set<Coordinate> set = new HashSet<>();
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                if (predicate.test(array[y][x])) {
                    set.add(Coordinate.of(x, y));
                }
            }
        }
        return set;
    }


}
