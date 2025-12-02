package com.stukans.advent._2023.day2;

import java.util.HashMap;
import java.util.Map;

public class CubesSet {

    private static final Map<String, Integer> cubes = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    private final Map<String, Integer> set;

    public CubesSet(String input) {
        Map<String, Integer> map = new HashMap<>();
        String[] split = input.strip().split(",");
        for (String s : split) {
            String[] values = s.strip().split(" ");
            map.put(values[1], Integer.parseInt(values[0]));
        }
        this.set = map;
    }

    public boolean isValid() {
        boolean valid = true;
        for (Map.Entry<String, Integer> entry : set.entrySet()) {
            if (entry.getValue().compareTo(cubes.get(entry.getKey())) > 0) {
                valid = false;
            }
        }
        return valid;
    }

    public Map<String, Integer> getSet() {
        return set;
    }
}
