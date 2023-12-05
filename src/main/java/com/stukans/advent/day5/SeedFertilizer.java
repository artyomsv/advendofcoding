package com.stukans.advent.day5;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class SeedFertilizer {

    public long part1(List<String> input) {
        Instant start = Instant.now();
        long calculate = calculate(input, seedsPart1(input.get(0)));
        System.out.println("Finished: " + Duration.between(start, Instant.now()).toMillis());
        return calculate;
    }

    public long part2(List<String> input) {
        Instant start = Instant.now();
        final List<SeedProvider> seedProviders = seedsPart2(input.get(0));
        final List<CompletableFuture<Long>> futures = new ArrayList<>();
        final ForkJoinPool executor = ForkJoinPool.commonPool();
        for (SeedProvider seedProvider : seedProviders) {

            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> calculate(input, seedProvider), executor);
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray((IntFunction<CompletableFuture<?>[]>) value -> new CompletableFuture[0]))
                .join();

        long result = Long.MAX_VALUE;
        for (CompletableFuture<Long> future : futures) {
            try {
                Long r = future.get();
                if (r < result) {
                    result = r;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Finished: " + Duration.between(start, Instant.now()).toMillis());
        return result;
    }

    private long calculate(List<String> input, SeedProvider provider) {
        List<Map> maps = maps(input);
        long result = Long.MAX_VALUE;
        while (provider.hasNext()) {
            long innerSeed = provider.next();
            for (Map map : maps) {
                innerSeed = map.calculate(innerSeed);
            }
            if (innerSeed < result) {
                result = innerSeed;
            }
        }

        return result;
    }

    public SeedProvider seedsPart1(String input) {
        return new SimpleSeedProvider(
                Stream.of(input.strip().split(":")[1].strip().split(" "))
                        .map(String::strip)
                        .filter(it -> !it.isBlank())
                        .map(Long::parseLong)
                        .toList()
        );
    }

    public List<SeedProvider> seedsPart2(String input) {
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

    public List<Map> maps(List<String> input) {
        List<Map> maps = new ArrayList<>();

        String type = null;
        List<MapLine> mapLines = null;
        for (int i = 1; i < input.size(); i++) {
            String line = input.get(i).strip();
            if (line.isBlank()) {
                continue;
            }

            if (line.endsWith(":")) {
                if (type != null && !type.isBlank() && !mapLines.isEmpty()) {
                    maps.add(new Map(type, mapLines));
                }

                type = line.split(" ")[0].strip();
                mapLines = new ArrayList<>();
            } else {
                mapLines.add(new MapLine(line));
            }

        }
        maps.add(new Map(type, mapLines));

        return maps;

    }

}
