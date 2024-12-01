package com.stukans.advent._2023.day6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Race {

    final long time;
    final long distance;

    public Race(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

    public int calculate() {
        Map<Integer, List<Long>> collect = LongStream.range(1, time - 1)
                .map(it -> it * (time - it)).boxed()
                .collect(Collectors.groupingBy(integer -> integer > distance ? 1 : 0));
        return collect.get(1).size();
    }

}
