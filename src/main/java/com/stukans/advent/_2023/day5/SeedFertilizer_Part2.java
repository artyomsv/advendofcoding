package com.stukans.advent._2023.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SeedFertilizer_Part2 extends AbstractSeedFertilizer {

    @Override
    public List<SeedProvider> seeds(String input) {
        String[] split = input.strip().split(":");
        List<Long> seedRoots = Stream.of(split[1].strip().split(" "))
                .map(String::strip)
                .filter(it -> !it.isBlank())
                .map(Long::parseLong)
                .toList();

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < seedRoots.size(); i += 2) {
            pairs.add(new Pair(seedRoots.get(i), seedRoots.get(i) + seedRoots.get(i + 1)));
        }

        return pairs
                .stream()
                .sorted(Comparator.comparing(pair -> pair.left))
                .map((Function<Pair, SeedProvider>) pair -> new IncrementalSeedProvider(pair.left, pair.right))
                .toList();
    }

}
