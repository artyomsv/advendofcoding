package com.stukans.advent.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BoatRaces_Part1 implements BoatRaces {
    @Override
    public int solve(List<String> input) {
        String[] s1 = Stream.of(input.get(0).strip().split(":")[1].strip().split(" ")).filter(it -> !it.isBlank()).toArray(String[]::new);
        String[] s2 = Stream.of(input.get(1).strip().split(":")[1].strip().split(" ")).filter(it -> !it.isBlank()).toArray(String[]::new);

        List<Race> races = new ArrayList<>();
        for (int i = 0; i < s1.length; i++) {
            races.add(new Race(Long.parseLong(s1[i].strip()), Long.parseLong(s2[i].strip())));
        }
        return races.stream().map(Race::calculate).reduce((i1, i2) -> i1 * i2).orElse(0);
    }
}
