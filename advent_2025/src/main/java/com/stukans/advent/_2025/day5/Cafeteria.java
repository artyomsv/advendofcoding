package com.stukans.advent._2025.day5;

import com.stukans.advent._2025.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Cafeteria extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] load = load(file);

        Set<Long> allIds = new HashSet<>();
        Set<Long> freshIds = new HashSet<>();
        List<Range> ranges = new ArrayList<>();

        parseInputData(load, allIds, ranges);

        for (Long id : allIds) {
            for (Range range : ranges) {
                if (range.isInRange(id)) {
                    freshIds.add(id);
                }
            }
        }

        return freshIds.size();
    }

    @Override
    public long solution2(File file) {
        String[] load = load(file);
        List<Range> ranges = new ArrayList<>();
        parseInputData(load, new HashSet<>(), ranges);

        List<Range> noDuplicates = ranges.stream().distinct().toList();

        List<Range> normalizedByFrom = noDuplicates.stream().collect(Collectors.groupingBy(Range::from, Collectors.toList()))
                .entrySet()
                .stream()
                .flatMap(processFrom())
                .toList();

        List<Range> normalizedByTo = normalizedByFrom.stream().collect(Collectors.groupingBy(Range::to, Collectors.toList()))
                .entrySet()
                .stream()
                .flatMap(processTo())
                .sorted(Comparator.comparing(Range::from).thenComparing(Range::to))
                .toList();


        List<Range> mergedList = new ArrayList<>();
        Range current = normalizedByTo.getFirst();
        for (int i = 0; i < normalizedByTo.size(); i++) {

            boolean shallMerge = false;
            if (i < normalizedByTo.size() - 1) {
                shallMerge = normalizedByTo.get(i + 1).from <= current.to;
            }

            System.out.printf("f:%d t:%d - o:%b\n",
                    current.from(),
                    current.to(),
                    shallMerge
            );

            if (shallMerge) {
                Range next = normalizedByTo.get(i + 1);
                current = new Range(current.from, Math.max(next.to, current.to));
            } else {
                mergedList.add(current);
                if (i < normalizedByTo.size() - 1) {
                    current = normalizedByTo.get(i + 1);
                }
            }

        }
//        for (Range range : mergedList) {
//            System.out.println(range);
//        }

        return mergedList.stream().map(Range::count).peek(System.out::println).mapToLong(Long::longValue).sum();
    }

    private static Function<Map.Entry<Long, List<Range>>, Stream<Range>> processTo() {
        return entry -> {
            List<Range> value = entry.getValue();
            Iterator<Range> iterator = value.iterator();
            if (!iterator.hasNext()) {
                return Stream.empty();
            }
            Range current = iterator.next();
            if (!iterator.hasNext()) {
                return Stream.of(current);
            }

            do {
                Range next = iterator.next();
                if (next.from < current.from) {
                    current = next;
                }
            } while (iterator.hasNext());

            return Stream.of(current);
        };
    }

    private static Function<Map.Entry<Long, List<Range>>, Stream<Range>> processFrom() {
        return entry -> {
            List<Range> value = entry.getValue();
            Iterator<Range> iterator = value.iterator();
            if (!iterator.hasNext()) {
                return Stream.empty();
            }
            Range current = iterator.next();
            if (!iterator.hasNext()) {
                return Stream.of(current);
            }

            do {
                Range next = iterator.next();
                if (next.to > current.to) {
                    current = next;
                }
            } while (iterator.hasNext());

            return Stream.of(current);
        };
    }

    private static void parseInputData(String[] load, Set<Long> ids, List<Range> ranges) {
        boolean switchedParser = false;
        for (String value : load) {
            if (value.trim().isBlank()) {
                switchedParser = true;
                continue;
            }

            if (switchedParser) {
                ids.add(Long.parseLong(value));
            } else {
                String[] split = value.split("-");
                ranges.add(new Range(
                        Long.parseLong(split[0].trim()),
                        Long.parseLong(split[1].trim())
                ));
            }

        }
    }

    private record Range(long from, long to) {

        public boolean isInRange(long value) {
            return value >= from && value <= to;
        }

        public long count() {
            return to - from + 1;
        }

    }

}
