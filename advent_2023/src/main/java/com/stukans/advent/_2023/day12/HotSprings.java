package com.stukans.advent._2023.day12;

import com.stukans.advent._2023.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.stukans.advent._2023.Utils.unfold;

public class HotSprings extends Puzzle<Integer> {

    @Override
    public long solve(List<String> input) {
        return solve(input, 1);
    }

    @Override
    public long solve(List<String> input, Integer unfolding) {
        long result = 0;
        for (String s : input) {
            String[] s1 = s.split(" ");
            String line = s1[0];
            Integer[] values = Stream.of(s1[1]).map(String::strip).map(it -> it.split(",")).flatMap(Stream::of).map(String::strip).map(Integer::parseInt).toArray(Integer[]::new);
            result += permutations(line, values, unfolding);
        }

        return result;
    }

    public List<List<String>> split(List<String> list, int chunk) {
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunk) {
            lists.add(list.subList(i, Math.min(i + chunk, list.size())));
        }
        return lists;
    }

    public List<String> permutationsValues(String input, Integer[] values, int unfold) {
        String unfoldedInput = unfold(input, unfold);
        Integer[] unfoldedValues = unfold(values, unfold);
        List<char[]> permutations = permutations(0, unfoldedInput.toCharArray());
        return permutations.stream().filter(it -> Arrays.equals(verify(it), unfoldedValues)).map(String::new).toList();
    }

    public long permutations(String input, Integer[] values, int unfold) {
        long notFolded = resolve(input, values);
        if (unfold == 1) {
            return notFolded;
        }
        long foldedTwice = resolve(unfold(input, 2), unfold(values, 2));
        long multiplier = foldedTwice / notFolded;

        long result = notFolded;
        for (int i = 0; i < unfold - 1; i++) {
            result *= multiplier;
        }
        return result;
    }

    private long resolve(String input, Integer[] values) {
        long count = 0;
        List<char[]> permutations = permutations(0, input.toCharArray());
        for (char[] permutation : permutations) {
            if (Arrays.equals(verify(permutation), values)) {
                count++;
            }
        }
        return count;
    }


    public List<char[]> permutations(int start, char[] input) {
        List<char[]> list = new ArrayList<>();
        for (int i = start; i < start + 1; i++) {
            if (input[i] == '?') {
                char[] chars2 = Arrays.copyOf(input, input.length);
                chars2[i] = '#';
                if (i == input.length - 1) {
                    list.add(chars2);
                } else {
                    list.addAll(permutations(i + 1, chars2));
                }

                char[] chars1 = Arrays.copyOf(input, input.length);
                chars1[i] = '.';
                if (i == input.length - 1) {
                    list.add(chars1);
                } else {
                    list.addAll(permutations(i + 1, chars1));
                }
            } else if (i == input.length - 1) {
                list.add(input);
            } else {
                list.addAll(permutations(i + 1, input));
            }
        }
        return list;
    }


//    public List<String> permutations(String input, Integer[] elements) {
//
//        if (input.isEmpty()) {
//            return Collections.emptyList();
//        }
//        if (input.length() == 1) {
//            return List.of("#");
//        }
//
//        if (input.length() == elements[0]) {
//            return List.of(input.replaceAll("\\?", "#"));
//        }
//
//
//        int spaceUsed = 0;
//        int elementsFits = 0;
//        int sumOfElements = 0;
//        for (int i = 0; i < elements.length; i++) {
//            if (spaceUsed + elements[i] > input.length()) {
//                break;
//            }
//            spaceUsed += elements[i] + 1;
//            elementsFits++;
//            sumOfElements += elements[i];
//        }
//
//        Set<Integer> nonWildcardsLocation = new HashSet<>();
//        for (int i = 0; i < input.length(); i++) {
//            if (input.charAt(i) == '#') {
//                nonWildcardsLocation.add(i);
//            }
//        }
//
//        List<String> permutations = new ArrayList<>();
//        for (int j = 0; j < input.length(); j++) {
//            if ((input.length() - j) < sumOfElements) {
//                break;
//            }
//            StringBuilder builder = new StringBuilder(input.length());
////            if ((j > 0 && input.charAt(j - 1) == '#') || (j < input.length() - 1 && input.charAt(j + 1) == '#')) {
////                continue;
////            }
//
////            if ((j > 0 && input.charAt(j - 1) == '#') && (j < input.length() - 1 && input.charAt(j + 1) == '#')) {
////                continue;
////            }
//
//            for (int i = 0; i < j; i++) {
//                builder.append(input.charAt(i) == '?' ? '.' : '#');
//            }
//
//            for (int i = 0; i < elementsFits; i++) {
//                for (int k = 0; k < elements[i]; k++) {
//                    builder.append('#');
//                }
//                if (builder.length() < input.length()) {
//                    builder.append('.');
//                }
//            }
//
//            if (builder.length() < input.length()) {
//                for (int i = builder.length(); i < input.length(); i++) {
//                    builder.append('.');
//                }
//            }
//
//            String result = builder.toString();
//
//            permutations.add(result);
//
//            if (result.endsWith("#")) {
//                break;
//            }
//        }
//
//        return permutations;
//    }

    private Integer[] verify(String input) {
        return Stream.of(input.strip().split("\\.")).filter(it -> !it.isBlank()).map(String::strip).map(String::length).toArray(Integer[]::new);
    }

    private Integer[] verify(Character[] input) {
        return verify(new String(convert(input)));
    }

    private Integer[] verify(char[] input) {
        return verify(new String(input));
    }

}
