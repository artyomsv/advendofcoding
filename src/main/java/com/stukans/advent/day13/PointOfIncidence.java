package com.stukans.advent.day13;

import com.stukans.advent.Puzzle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PointOfIncidence extends Puzzle {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public long solve(List<String> input, boolean smudged) {
        List<Block> blocks = new ArrayList<>();
        List<String> blockLines = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.strip().isBlank()) {
                blocks.add(new Block(convert(blockLines)));
                blockLines = new ArrayList<>();
                continue;
            }
            blockLines.add(line);
        }
        blocks.add(new Block(convert(blockLines)));

        return blocks.stream()
                .map(Block::calc)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private static List<Pair> filterOutPairs(List<Pair> horizontalHashesPairs, List<Integer> horizontalHashes) {
        List<Pair> filteredPairs = new ArrayList<>();
        for (Pair pair : horizontalHashesPairs) {
            if (pair.i1 == 0 || pair.i2 == horizontalHashes.size()) {
                filteredPairs.add(pair);
                continue;
            }
            boolean validPair = true;
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            for (int j1 = pair.i1, j2 = pair.i2; j1 >= 0 && j2 < horizontalHashes.size(); j1--, j2++) {
                try {
                    left = j1;
                    right = j2;
                    if (!horizontalHashes.get(j1).equals(horizontalHashes.get(j2))) {
                        validPair = false;
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(pair + " - " + j1 + "," + j2);
                }
            }
            if (validPair && (left == 0 || right >= horizontalHashes.size() - 1)) {
                filteredPairs.add(pair);
            }
        }
        return filteredPairs;
    }

    private record Pair(int i1, int i2) {
        @Override
        public String toString() {
            return "[" + i1 + "," + i2 + ']';
        }
    }

    private class Block {
        private List<Pair> horizontalHashesPairs;
        private List<Integer> horizontalHashes;
        private List<Pair> verticalHashesPairs;
        private List<Integer> verticalHashes;
        private final char[][] block;

        public int y = 0;
        public int x = 0;
        boolean xRotates = false;

        public Block(char[][] block) {
            this.block = block;
        }

        public Optional<Block> smudged() {
            char[][] newArr = new char[block.length][];
            for (int i = 0; i < block.length; i++) {
                newArr[i] = Arrays.copyOf(block[i], block[i].length);
            }
            newArr[y][x] = newArr[y][x] == '#' ? '.' : '#';

            if (x >= block[y].length - 1) {
                x = 0;
                xRotates = true;
            } else {
                x++;
            }

            if (xRotates) {
                y++;
                xRotates = false;
            }

            if (y >= block.length - 1) {
                return Optional.empty();
            }

            return Optional.of(new Block(newArr));
        }

        public void calculatePairs() {
            horizontal(block);
            vertical(block);
        }

        private void horizontal(char[][] block) {
            List<Integer> hashes = new ArrayList<>();
            List<Pair> pairs = new ArrayList<>();

            int prev = -1;
            int index = 0;
            for (char[] line : block) {
                int hash = Objects.hash(new String(line));
                hashes.add(hash);
                if (hash == prev) {
                    pairs.add(new Pair(index - 1, index));
                }
                prev = hash;
                index++;
            }
            this.horizontalHashesPairs = new ArrayList<>(filterOutPairs(pairs, hashes));
            this.horizontalHashes = hashes;
        }

        private void vertical(char[][] block) {
            List<Integer> hashes = new ArrayList<>();
            int prev = -1;
            int y = 0;
            int length = block[y].length;
            List<Pair> pairs = new ArrayList<>();
            for (int x = 0; x < length; x++) {
                StringBuilder builder = new StringBuilder();
                for (y = 0; y < block.length; y++) {
                    builder.append(block[y][x]);
                }
                String line = builder.toString();
                int hash = Objects.hash(line);
                hashes.add(hash);
                if (prev == hash) {
                    pairs.add(new Pair(x - 1, x));
                }
                prev = hash;
            }

            this.verticalHashesPairs = new ArrayList<>(filterOutPairs(pairs, hashes));
            this.verticalHashes = hashes;
        }

        private int calc() {
            calculatePairs();

            if (horizontalHashesPairs.isEmpty() && verticalHashesPairs.isEmpty()) {
                return 0;
            }

            System.out.println();
            System.out.print("  ");
            for (int i = 1; i < block[0].length + 1; i++) {
                System.out.print(i < 10 ? " " : i / 10);
            }
            System.out.println();
            System.out.print("  ");
            for (int i = 1; i < block[0].length + 1; i++) {
                System.out.print(i < 10 ? i : i % 10);
            }
            System.out.println();
            Set<Integer> h = horizontalHashesPairs.stream().flatMap(pair -> Stream.of(pair.i1, pair.i2)).collect(Collectors.toSet());
            Set<Integer> v = verticalHashesPairs.stream().flatMap(pair -> Stream.of(pair.i1, pair.i2)).collect(Collectors.toSet());

            for (int i = 0; i < block.length; i++) {
                System.out.printf("%2d", i + 1);
                for (int j = 0; j < block[i].length; j++) {
//                    char c = block.get(i).charAt(j) == '.' ? '.' : '#';
                    char c = block[i][j] == '.' ? '█' : '#';
                    if (h.contains(i) && v.contains(j)) {
                        System.out.printf("%s%s%s", ANSI_PURPLE, c, ANSI_RESET);
                    } else if (h.contains(i) && !v.contains(j)) {
                        System.out.printf("%s%s%s", ANSI_RED, c, ANSI_RESET);
                    } else if (!h.contains(i) && v.contains(j)) {
                        System.out.printf("%s%s%s", ANSI_GREEN, c, ANSI_RESET);
                    } else {
                        System.out.printf("%s", c);
                    }
                }
                System.out.println();

            }

            Map<Integer, Pair> vertical = calcReflection(verticalHashesPairs, verticalHashes);
            Map<Integer, Pair> horizontal = calcReflection(horizontalHashesPairs, horizontalHashes);

            OptionalInt maxVertical = vertical.keySet().stream().mapToInt(it -> it).max();
            OptionalInt maxHorizontal = horizontal.keySet().stream().mapToInt(it -> it).max();

            int vert = maxVertical.orElse(-1);
            int horiz = maxHorizontal.orElse(-1);

            if (vert > horiz) {
                Pair pair = vertical.get(vert);
                int result = pair.i1 + 1;
                System.out.println("result: " + result);
                System.out.println();
                return result;
            } else {
                Pair pair = horizontal.get(horiz);
                int result = (pair.i1 + 1) * 100;
                System.out.println("result: " + result);
                System.out.println();
                return result;
            }
        }

        private Map<Integer, Pair> calcReflection(List<Pair> pairs, List<Integer> hashes) {
            Map<Integer, Pair> map = new HashMap<>();
            for (Pair pair : pairs) {
                int left, right;
                int counter = 0;
                int location1 = pair.i1;
                int location2 = pair.i2;
                do {
                    left = hashes.get(location1--);
                    right = hashes.get(location2++);
                    if (left == right) {
                        counter++;
                    }

                } while (left == right && location1 >= 0 && location2 < hashes.size() - 1);
                map.put(counter, pair);
            }
            return map;
        }

    }


}
