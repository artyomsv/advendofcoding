package com.stukans.advent._2023.day5;

import java.util.ArrayList;
import java.util.List;

public class MapLine {

    final long destinationRangeStart;
    final long sourceRangeStart;
    final long rangeLength;

    public MapLine(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    public MapLine(String input) {
        String[] split = input.strip().split(" ");
        this.destinationRangeStart = Long.parseLong(split[0].strip());
        this.sourceRangeStart = Long.parseLong(split[1].strip());
        this.rangeLength = Long.parseLong(split[2].strip());
    }

    public long location(long seed) {
        if (seed >= sourceRangeStart && seed < sourceRangeStart + rangeLength) {
            return destinationRangeStart + (seed - sourceRangeStart);
        }
        return -1;
    }

    public long seed(long location) {
        if (location >= destinationRangeStart && location < destinationRangeStart + rangeLength) {
            return sourceRangeStart + Math.abs(location - destinationRangeStart);
        }
        return location;
    }

    public List<Pair> pairs() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < rangeLength; i++) {
            pairs.add(new Pair(destinationRangeStart + i, sourceRangeStart + i));
        }
        return pairs;
    }

    @Override
    public String toString() {
        return "{" +
                "destinationRangeStart=" + destinationRangeStart +
                ", sourceRangeStart=" + sourceRangeStart +
                ", rangeLength=" + rangeLength +
                '}';
    }
}
