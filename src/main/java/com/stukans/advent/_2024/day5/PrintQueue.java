package com.stukans.advent._2024.day5;

import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class PrintQueue extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] load = load(file);

        Map<Integer, Set<Pair<Integer, Integer>>> pairsMap = new HashMap<>();
        List<List<Integer>> pagesList = new ArrayList<>();
        boolean finishedPairs = false;
        for (String line : load) {
            if (line.isBlank()) {
                finishedPairs = true;
                continue;
            }

            if (!finishedPairs) {
                String[] split = line.split("\\|");
                Pair<Integer, Integer> pair = Pair.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                collectPairNumbers(pairsMap, pair.getLeft(), pair);
                collectPairNumbers(pairsMap, pair.getRight(), pair);
            } else {
                pagesList.add(getList(line));
            }
        }

        long answer = 0;
        for (List<Integer> pages : pagesList) {
            boolean isCorrect = true;

            Map<Integer, Integer> order = calcOrder(pages);

            for (Integer page : pages) {
                Set<Pair<Integer, Integer>> pairs = pairsMap.get(page);
                for (Pair pair : pairs) {

                    Integer leftOrder = order.get(pair.getLeft());
                    Integer rightOrder = order.get(pair.getRight());

                    if (leftOrder == null || rightOrder == null) {
                        continue;
                    }

                    if (leftOrder > rightOrder) {
                        isCorrect = false;
                        break;
                    }

                }
            }

            if (isCorrect) {
                answer += new Pages(pages).middlePage();
            }
        }


        return answer;
    }

    @Override
    public long solution2(File file) {
        String[] load = load(file);

        Map<Integer, Set<Pair<Integer, Integer>>> pairsMap = new HashMap<>();
        List<List<Integer>> pagesList = new ArrayList<>();
        boolean finishedPairs = false;
        for (String line : load) {
            if (line.isBlank()) {
                finishedPairs = true;
                continue;
            }

            if (!finishedPairs) {
                String[] split = line.split("\\|");
                Pair<Integer, Integer> pair = Pair.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                collectPairNumbers(pairsMap, pair.getLeft(), pair);
                collectPairNumbers(pairsMap, pair.getRight(), pair);
            } else {
                pagesList.add(getList(line));
            }
        }

        long answer = 0;

        for (List<Integer> pages : pagesList) {
            Map<Integer, Integer> order = calcOrder(pages);
            Optional<Pair<Integer, Integer>> optional = findNotCorrectPair(pages, pairsMap, order);
            if (optional.isEmpty()) {
                System.out.printf("%s%s%s - Is correct\n", ANSI_RED, pages, ANSI_RESET);
            } else {
                System.out.printf("%s%s%s - Not correct\n", ANSI_GREEN, pages, ANSI_RESET);
                List<Integer> updatedPages = new ArrayList<>(pages);
                do {
                    Pair<Integer, Integer> notCorrectPair = optional.get();
                    updatedPages.set(order.get(notCorrectPair.getRight()), notCorrectPair.getLeft());
                    updatedPages.set(order.get(notCorrectPair.getLeft()), notCorrectPair.getRight());
                    order = calcOrder(updatedPages);
                    optional = findNotCorrectPair(updatedPages, pairsMap, order);
                    System.out.printf("%s - %s\n", updatedPages, optional.isEmpty());

                } while (optional.isPresent());
                answer += new Pages(updatedPages).middlePage();
            }
        }


        return answer;
    }

    private Map<Integer, Integer> calcOrder(List<Integer> pages) {
        Map<Integer, Integer> order = new HashMap<>();
        for (int i = 0; i < pages.size(); i++) {
            order.put(pages.get(i), i);
        }
        return order;
    }

    private Optional<Pair<Integer, Integer>> findNotCorrectPair(List<Integer> pages, Map<Integer, Set<Pair<Integer, Integer>>> pairsMap, Map<Integer, Integer> order) {

        for (Integer page : pages) {
            Set<Pair<Integer, Integer>> pairs = pairsMap.get(page);
            for (Pair<Integer, Integer> pair : pairs) {

                Integer leftOrder = order.get(pair.getLeft());
                Integer rightOrder = order.get(pair.getRight());

                if (leftOrder == null || rightOrder == null) {
                    continue;
                }

                if (leftOrder > rightOrder) {
                    return Optional.of(pair);
                }

            }
        }
        return Optional.empty();
    }

    protected List<Integer> getList(String line) {
        return Arrays.stream(line.split(","))
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .toList();
    }

    private static void collectPairNumbers(Map<Integer, Set<Pair<Integer, Integer>>> map, Integer value, Pair<Integer, Integer> pair) {
        Set<Pair<Integer, Integer>> leftList = map.get(value);
        if (leftList == null) {
            leftList = new HashSet<>();
        }
        leftList.add(pair);
        map.put(value, leftList);
    }

    protected record Pages(List<Integer> pages) {

        public Integer middlePage() {
            if (pages.size() % 2 == 0) {
                throw new IllegalArgumentException("even pages, cannot identify middle page");
            }
            return pages.get(pages.size() / 2);
        }

    }


}
