package com.stukans.advent.day5;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class IncrementalSeedProvider implements SeedProvider {

    private final long start;
    private final long end;
    private final AtomicLong current;

    public IncrementalSeedProvider(long start, long end) {
        this.start = start;
        this.end = end;
        current = new AtomicLong(start);
    }

    @Override
    public Long next() {
        return current.getAndIncrement();
    }

    @Override
    public boolean hasNext() {
        return current.longValue() <= end;
    }

    @Override
    public long size() {
        return end - start;
    }

    @Override
    public LongStream stream() {
        return LongStream.range(start, end);
    }

    @Override
    public String toString() {
        return "{" + start + "," + end + '}';
    }
}
