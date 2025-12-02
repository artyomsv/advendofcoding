package com.stukans.advent._2023.day5;

import java.util.Comparator;
import java.util.List;

public class Map {

    final String type;
    final List<MapLine> lines;

    public Map(String type, List<MapLine> lines) {
        this.type = type;
        this.lines = lines;
    }

    public long calculate(Long seed) {
        for (MapLine line : lines) {
            long location = line.location(seed);
            if (location != -1) {
                return location;
            }
        }
        return seed;
    }

    public long reverseCalculate(Long location) {
        for (MapLine line : lines) {
            long seed = line.seed(location);
            if (seed != location) {
                return seed;
            }
        }
        return location;
    }

    public void mapping() {
        lines.stream().sorted(Comparator.comparing(mapLine -> mapLine.destinationRangeStart))
                .map(MapLine::pairs)
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

    public List<MapLine> stream() {
        return lines.stream().sorted(Comparator.comparing(mapLine -> mapLine.destinationRangeStart))
                .toList();
    }

}
