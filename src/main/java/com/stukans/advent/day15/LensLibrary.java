package com.stukans.advent.day15;

import com.stukans.advent.Puzzle;

import java.util.*;
import java.util.function.UnaryOperator;

public class LensLibrary extends Puzzle<Long> {

    @Override
    public long solve(List<String> input) {
        long sum = 0;
        for (String string : input) {
            StringTokenizer tokenizer = new StringTokenizer(string, ",");
            while (tokenizer.hasMoreElements()) {
                String strippedInput = tokenizer.nextToken().strip();
                if (strippedInput.isBlank()) {
                    continue;
                }
                sum += hash(strippedInput);
            }
        }
        return sum;
    }

    @Override
    public long solve(List<String> input, Long aLong) {
        Map<Long, List<Lens>> map = new HashMap<>();

        for (String string : input) {
            StringTokenizer tokenizer = new StringTokenizer(string, ",");
            while (tokenizer.hasMoreElements()) {
                String strippedInput = tokenizer.nextToken().strip();
                if (strippedInput.isBlank()) {
                    continue;
                }
                if (strippedInput.contains("=")) {
                    String[] split = strippedInput.split("=");
                    String label = split[0].strip();
                    int focalLength = Integer.parseInt(split[1].strip());

                    long hash = hash(label);
                    List<Lens> list = map.getOrDefault(hash, new LinkedList<>());

                    Lens lens = new Lens(label, focalLength);
                    if (list.contains(lens)) {
                        list.replaceAll(new UnaryOperator<Lens>() {
                            @Override
                            public Lens apply(Lens lens) {
                                return lens;
                            }
                        });
                    } else {
                        list.add(lens);
                    }
                    map.put(hash, list);
                } else {
                    String[] split = strippedInput.split("-");
                    String label = split[0].strip();
                    long hash = hash(label);

                    List<Lens> list = map.getOrDefault(hash, new LinkedList<>());
                    if (!list.isEmpty()) {
                        list.remove(new Lens(label, -1));
                        map.put(hash, list);
                    }
                }
            }
        }

        return 0;
    }

    private record Lens(String label, int focalLength) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lens lens = (Lens) o;
            return Objects.equals(label, lens.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
    }

    public long hash(String input) {
        System.out.println(input);
        long current = 0;
        for (int i = 0; i < input.length(); i++) {
            int ascii = input.charAt(i);
            current += ascii;
            current *= 17;
            current %= 256;
        }
        return current;
    }
}
