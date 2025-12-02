package com.stukans.advent._2023.day1;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Trebuchet {

    public int solve(List<String> list, Boolean replaceWords) {
        return list
                .stream()
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Trebuchet.this.apply(s, replaceWords);
                    }
                })
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("Failed to solve a line"));
    }

    public enum Day {
        zero, one, two, three, four, five, six, seven, eight, nine,
    }

    public Integer apply(final String input, Boolean replaceWords) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(charArray[i])) {
                builder.append(Character.digit(charArray[i], 10));
            } else {
                if (replaceWords) {
                    final String substring = input.substring(i);
                    Optional<Day> first = Stream.of(Day.values()).filter(day -> substring.startsWith(day.name())).findFirst();
                    first.ifPresent(it -> builder.append(it.ordinal()));
                }
            }
        }

        if (builder.isEmpty()) {
            return 0;
        }
        String result = Character.digit(builder.charAt(0), 10) + "" + Character.digit(builder.charAt(builder.length() - 1), 10);

        return Integer.parseInt(result);
    }

}
