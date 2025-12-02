package com.stukans.advent._2024.day11;

import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlutonianPebbles extends Puzzle {

    private List<Rule> rules = List.of(new ZeroRule(), new EvenRule(), new NotEvenRule());

    @Override
    public long solution1(File file) {
        String[] split = load(file)[0].split(" ");

        List<String> stones = Arrays.stream(split).toList();

        Map<Pair<String, Integer>, Long> memo = new HashMap<>();

        int maxBlinks = 25;
        long result = 0;
        for (String stone : stones) {
            result += recursive(stone, maxBlinks, memo);
        }
        return result;
    }

    @Override
    public long solution2(File file) {
        String[] split = load(file)[0].split(" ");

        List<String> stones = Arrays.stream(split).toList();

        Map<Pair<String, Integer>, Long> memo = new HashMap<>();

        int maxBlinks = 75;
        long result = 0;
        for (String stone : stones) {
            result += recursive(stone, maxBlinks, memo);
        }
        return result;
    }

    private long recursive(String value, int blink, Map<Pair<String, Integer>, Long> memo) {
        if (blink <= 0) {
            return 1;
        }

        Pair<String, Integer> key = Pair.of(value, blink);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        for (Rule rule : rules) {
            List<String> eval = rule.eval(value);
            if (!eval.isEmpty()) {
                long result = 0;
                for (String s : eval) {
                    result += recursive(s, blink - 1, memo);
                }
                memo.put(key, result);
                break;
            }
        }
        return memo.get(key);

    }

    class ZeroRule implements Rule {

        @Override
        public List<String> eval(String input) {
            if (input.equals("0")) {
                return List.of("1");
            }
            return Collections.emptyList();
        }
    }

    class NotEvenRule implements Rule {
        @Override
        public List<String> eval(String input) {
            if (input.length() % 2 == 1) {
                return List.of(String.valueOf(Long.parseLong(input) * 2024));
            }
            return Collections.emptyList();
        }
    }

    class EvenRule implements Rule {
        @Override
        public List<String> eval(String input) {
            if (input.length() % 2 == 0) {
                String left = input.substring(0, input.length() / 2);
                String right = input.substring(input.length() / 2);
                return List.of(String.valueOf(Long.parseLong(left)), String.valueOf(Long.parseLong(right)));
            }
            return Collections.emptyList();
        }
    }

    interface Rule {
        List<String> eval(String input);
    }
}
