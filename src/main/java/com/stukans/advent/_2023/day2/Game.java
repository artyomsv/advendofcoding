package com.stukans.advent._2023.day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Game {

    private final int number;

    private final List<CubesSet> list;

    public Game(String text) {
        String[] s1 = text.split(":");
        String[] s2 = s1[0].split(" ");
        String[] s3 = s1[1].split(";");
        this.number = Integer.parseInt(s2[1]);
        this.list = Stream.of(s3).map(CubesSet::new).toList();
    }

    public boolean isValid() {
        boolean valid = true;
        for (CubesSet cubesSet : list) {
            if (!cubesSet.isValid()) {
                valid = false;
            }
        }
        return valid;
    }

    public int getNumber() {
        return number;
    }

    public int power() {
        Map<String, Integer> map = new HashMap<>();
        for (CubesSet cubesSet : list) {
            for (Map.Entry<String, Integer> entry : cubesSet.getSet().entrySet()) {
                if (!map.containsKey(entry.getKey())) {
                    map.put(entry.getKey(), entry.getValue());
                } else {
                    Integer current = map.get(entry.getKey());
                    if (current < entry.getValue()) {
                        map.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        return map.values().stream().reduce((i1, i2) -> i1 * i2).orElseThrow(() -> new RuntimeException("Failed to calc the power"));
    }
}
