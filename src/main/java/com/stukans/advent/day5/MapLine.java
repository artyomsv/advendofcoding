package com.stukans.advent.day5;

import java.util.Collections;
import java.util.List;

public class MapLine {

    final int destinationRangeStart;
    final int sourceRangeStart;
    final int rangeLength;

    public MapLine(int destinationRangeStart, int sourceRangeStart, int rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    public List<Pair> pairs() {
        return Collections.emptyList();
    }

}
