package com.stukans.advent.day5;

import java.util.List;
import java.util.stream.Stream;

public class SeedFertilizer_Part1 extends AbstractSeedFertilizer {

    @Override
    public List<SeedProvider> seeds(String input) {
        return List.of(
                new SimpleSeedProvider(
                        Stream.of(input.strip().split(":")[1].strip().split(" "))
                                .map(String::strip)
                                .filter(it -> !it.isBlank())
                                .map(Long::parseLong)
                                .toList()
                )
        );
    }

}
