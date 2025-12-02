package com.stukans.advent._2023.day8;

import java.util.*;

public class HauntedWasteland {

    public record Pair(String left, String right) {
    }

    public long solve(List<String> input, boolean partOne) {
        Iterator<String> iterator = input.iterator();
        char[] directions = iterator.next().strip().toCharArray();
        Map<String, Pair> map = buildLocationsMap(iterator);
        if (partOne) {
            return calculatePartOne(directions, map);
        } else {
            return calculatePartTwo(directions, map);
        }
    }

    private static Map<String, Pair> buildLocationsMap(Iterator<String> iterator) {
        Map<String, Pair> map = new HashMap<>();
        while (iterator.hasNext()) {
            String line = iterator.next().strip();
            if (line.isBlank()) {
                continue;
            }

            String[] s1 = line.strip().split("=");
            String[] s2 = s1[1].strip().replace("(", "").replace(")", "").split(",");
            String key = s1[0].strip();

            map.put(key, new Pair(s2[0].strip(), s2[1].strip()));
        }
        return map;
    }

    private static long calculatePartTwo(char[] directions, Map<String, Pair> map) {
        List<String> locationsEndingWithA = map.keySet().stream().filter(it -> it.endsWith("Z")).toList();
        List<Long> allSteps = new ArrayList<>();
        for (String location : locationsEndingWithA) {
            String current = location;
            long innerSteps = 0;
            boolean endNotFound = true;
            while (endNotFound) {
                for (char direction : directions) {
                    Pair pair = map.get(current);
                    current = direction == 'R' ? pair.right() : pair.left();
                    innerSteps++;
                    if (current.endsWith("Z")) {
                        endNotFound = false;
                        break;
                    }
                }
            }
            allSteps.add(innerSteps);
        }

        Long[] array = allSteps.toArray(Long[]::new);
        long lcm = lcm(array);

        return lcm;
    }

    private static long calculatePartOne(char[] directions, Map<String, Pair> map) {
        long steps = 0;
        boolean endNotFound = true;
        String current = "AAA";
        while (endNotFound) {
            for (char direction : directions) {
                Pair pair = map.get(current);
                current = direction == 'R' ? pair.right() : pair.left();
                steps++;
                if (current.equals("ZZZ")) {
                    endNotFound = false;
                    break;
                }
            }
        }
        return steps;
    }

    private static long gcd(Long a, Long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static long lcm(Long a, Long b) {
        return a * (b / gcd(a, b));
    }

    private static long lcm(Long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

}
