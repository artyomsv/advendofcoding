package com.stukans.advent._2024.day3;

import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MullItOver extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] load = load(file);

        Pattern pattern = Pattern.compile("(mul\\(\\d*,\\d*\\))");

        long count = Arrays.stream(load)
                .flatMap(it -> {
                    Matcher matcher = pattern.matcher(it);
                    List<String> result = new LinkedList<>();
                    while (matcher.find()) {
                        String group = matcher.group();
                        result.add(group);
                    }
                    return result.stream();
                })
                .map(it -> {
                    String s = it.replace("mul(", "").replaceAll("\\)", "");
                    System.out.println(s);
                    String[] split = s.split(",");
                    return Long.parseLong(split[0]) * Long.parseLong(split[1]);
                })
                .mapToLong(value -> value)
                .sum();

        return count;
    }

    @Override
    public long solution2(File file) {
        String[] load = load(file);

        Pattern pattern = Pattern.compile("(mul\\(\\d*,\\d*\\))|(do\\(\\))|(don't\\(\\))");

        List<String> result = processInputData(load, pattern);

        long sum = result.stream()
                .map(it -> {
                    String s = it.replace("mul(", "").replaceAll("\\)", "");
                    System.out.println(s);
                    String[] split = s.split(",");
                    return Long.parseLong(split[0]) * Long.parseLong(split[1]);
                })
                .mapToLong(value -> value)
                .sum();


        return sum;
    }

    private static List<String> processInputData(String[] load, Pattern pattern) {
        String collect = Arrays.stream(load).collect(Collectors.joining());
        Matcher matcher = pattern.matcher(collect);
        List<String> result = new LinkedList<>();

        boolean isFirstMuls = true;
        boolean followingDo = false;
        int countMuls = 0;
        int countDos = 0;
        int countDonts = 0;
        int countFiltered = 0;

        while (matcher.find()) {
            String group = matcher.group();
            if (group.startsWith("mul")) {
                boolean mulAdded = false;

                if (isFirstMuls) {
                    System.out.printf(" %s%s%s", ANSI_PURPLE, group, ANSI_RESET);
                    result.add(group);
                    countMuls++;
                    mulAdded = true;
                }

                if (followingDo) {
                    System.out.printf(" %s%s%s", ANSI_PURPLE, group, ANSI_RESET);
                    countMuls++;
                    result.add(group);
                    mulAdded = true;
                }

                if (!mulAdded) {
                    System.out.printf(" %s%s%s", ANSI_RESET, group, ANSI_RESET);
                    countFiltered++;
                }
            }

            if (group.equals("do()")) {
                System.out.printf(" %s%s%s", ANSI_GREEN, group, ANSI_RESET);
                followingDo = true;
                isFirstMuls = false;
                countDos++;
            }

            if (group.startsWith("don")) {
                followingDo = false;
                isFirstMuls = false;
                System.out.printf(" %s%s%s", ANSI_RED, group, ANSI_RESET);
                countDonts++;
            }
        }
        System.out.println();
        System.out.println(" muls:" + countMuls);
        System.out.println("  dos:" + countDos);
        System.out.println("donts:" + countDonts);
        System.out.println(" filt:" + countFiltered);
        System.out.println("total:" + (countMuls + countDos + countDonts + countFiltered));
        return result;
    }
}
