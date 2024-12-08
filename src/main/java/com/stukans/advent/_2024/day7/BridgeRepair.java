package com.stukans.advent._2024.day7;

import com.stukans.advent._2024.Permutations;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class BridgeRepair extends Puzzle {

    public static final List<Character> POSSIBLE_NODES_1 = List.of('+', '*');
    public static final List<Character> POSSIBLE_NODES_2 = List.of('+', '*', '|');

    @Override
    public long solution1(File file) {
        long answer = 0;

        String[] load = load(file);
        for (String line : load) {
            answer += evaluate(line, POSSIBLE_NODES_1);
        }

        return answer;
    }

    @Override
    public long solution2(File file) {
        long answer = 0;

        String[] load = load(file);
        for (String line : load) {
            answer += evaluate(line, POSSIBLE_NODES_2);
        }

        return answer;
    }


    protected long evaluate(String line, List<Character> possibleNodes) {
        String[] split = line.split(":");
        long expected = Long.parseLong(split[0]);
        Long[] numbers = Arrays.stream(split[1].trim().split(" ")).map(Long::parseLong).toArray(Long[]::new);
        List<List<Character>> permutations = Permutations.permutation(possibleNodes, numbers.length - 1, Character.class);

        for (List<Character> permutation : permutations) {
            StringBuilder builder = new StringBuilder();
            long total = 0;
            for (int i = 0; i < permutation.size(); i++) {
                if (i == 0) {
                    switch (permutation.get(i)) {
                        case '+': {
                            total = numbers[i] + numbers[i + 1];
                            break;
                        }
                        case '*': {
                            total = numbers[i] * numbers[i + 1];
                            break;
                        }
                        case '|': {
                            total = Long.parseLong(numbers[i] + "" + numbers[i + 1]);
                            break;
                        }
                    }
                } else {
                    switch (permutation.get(i)) {
                        case '+': {
                            total += numbers[i + 1];
                            break;
                        }
                        case '*': {
                            total *= numbers[i + 1];
                            break;
                        }
                        case '|': {
                            total = Long.parseLong(total + "" + numbers[i + 1]);
                            break;
                        }
                    }
                }
            }

            if (total == expected) {
                return total;
            }
        }
        return 0;
    }

}
