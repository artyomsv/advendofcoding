package com.stukans.advent.day5;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.IntFunction;

public abstract class AbstractSeedFertilizer implements SeedFertilizer {

    @Override
    public long solve(List<String> input) {
        Instant start = Instant.now();
        final List<SeedProvider> seedProviders = seeds(input.get(0));
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


    protected long calculate(List<String> input, SeedProvider provider) {
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

    private List<Map> maps(List<String> input) {
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
