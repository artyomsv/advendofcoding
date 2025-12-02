package com.stukans.advent._2023.day15;

import com.stukans.advent._2023.Puzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;

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
        Map<Long, List<Lens>> map = new TreeMap<>();

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

                    final Lens lens = new Lens(label, focalLength);
                    if (list.contains(lens)) {
                        list.replaceAll(curr -> curr.equals(lens) ? lens : curr);
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

        long result = 0;
        for (Map.Entry<Long, List<Lens>> entry : map.entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            long boxNumber = entry.getKey() + 1;
            for (int i = 0; i < entry.getValue().size(); i++) {
                int slotNumber = i + 1;
                int focalLength = entry.getValue().get(i).focalLength;
                result += boxNumber * slotNumber * focalLength;
            }

        }

        return result;
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
