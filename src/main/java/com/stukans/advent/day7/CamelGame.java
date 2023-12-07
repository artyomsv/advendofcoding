package com.stukans.advent.day7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CamelGame {

    private final Map<Character, Integer> hands;
    private final boolean partOne;

    public record Pair(Character[] hands, String raw, long bid) {
    }

    public record Game(int type, Character[] hands, String raw, long bid) {
    }

    public CamelGame(boolean partOne) {
        this.partOne = partOne;

        hands = new HashMap<>();
        hands.put('2', 1);
        hands.put('3', 2);
        hands.put('4', 3);
        hands.put('5', 4);
        hands.put('6', 5);
        hands.put('7', 6);
        hands.put('8', 7);
        hands.put('9', 8);
        hands.put('T', 9);
        hands.put('J', partOne ? 10 : 0);
        hands.put('Q', 11);
        hands.put('K', 12);
        hands.put('A', 13);
    }

    public long solve(List<String> inputs) {
        Map<Integer, List<Game>> collect = inputs.stream()
                .map(String::strip)
                .map(it -> it.split(" "))
                .map(it -> {
                    String hand = it[0].strip();
                    char[] raw = hand.toCharArray();
                    return new Pair(convert(raw), hand, Long.parseLong(it[1].strip()));
                })
                .map(it -> new Game(partOne ? type(it.hands) : typeWithJokers(it.hands), it.hands, it.raw, it.bid))
                .collect(Collectors.groupingBy(game -> game.type));

        final AtomicInteger rank = new AtomicInteger(1);
        long result = 0;
        for (int i = 0; i <= 6; i++) {
            result += Stream.ofNullable(collect.get(i)).flatMap(List::stream).sorted(
                            Comparator.comparing(
                                    game -> game.hands,
                                    (o1, o2) -> {
                                        for (int j = 0; j < o1.length; j++) {
                                            Integer left = hands.get(o1[j]);
                                            Integer right = hands.get(o2[j]);
                                            int compared = left.compareTo(right);
                                            if (compared != 0) {
                                                return compared;
                                            }
                                        }
                                        return 0;
                                    }
                            )

                    )
                    .map(it -> {
                        int andIncrement = rank.getAndIncrement();
                        long l = it.bid * andIncrement;
                        System.out.printf("rank:%d bid:%d hand:%s result:%d\n", andIncrement, it.bid, it.raw, l);
                        return l;
                    })
                    .reduce(Long::sum)
                    .orElse(0L);
        }

        return result;
    }

    public static Character[] convert(char[] hands) {
        Character[] characterArray = new Character[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }

    public int type(Character[] hands) {
        Map<Character, Integer> map = handsMap(hands);
        Collection<Integer> values = map.values();
        return typeNoJokers(values);
    }

    private static int typeNoJokers(Collection<Integer> values) {
        if (values.contains(5)) {
            return 6; // Five of a kind
        } else if (values.contains(4)) {
            return 5; // Four of a kind
        } else if (values.contains(3) && values.contains(2)) {
            return 4; // Full house
        } else if (values.contains(3) && !values.contains(2)) {
            return 3; // Three of a kind
        } else if (values.contains(2) && values.size() == 3) {
            return 2; // Two pair
        } else if (values.contains(2) && values.size() == 4) {
            return 1; // One pair
        } else {
            return 0; // High card
        }
    }

    private static Map<Character, Integer> handsMap(Character[] hands) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < hands.length; i++) {
            char current = hands[i];
            Integer value = map.get(current);
            map.put(current, value == null ? 1 : value + 1);
        }
        return map;
    }

    public int typeWithJokers(Character[] hands) {
        Map<Character, Integer> map = handsMap(hands);

        if (!map.containsKey('J')) {
            return typeNoJokers(map.values());
        }

        if (map.get('J') == 5) {
            return 6;
        }
        Set<Character> characters = map.keySet().stream().filter(it -> it != 'J').collect(Collectors.toSet());
        int rank = 0;
        for (Character character : characters) {
            List<Integer> updatedValues = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getKey() == 'J') {
                    continue;
                }
                if (entry.getKey() == character) {
                    updatedValues.add(entry.getValue() + map.get('J'));
                } else {
                    updatedValues.add(entry.getValue());
                }
            }
            int i = typeNoJokers(updatedValues);
            if (i > rank) {
                rank = i;
            }
        }

        return rank;
    }

}
