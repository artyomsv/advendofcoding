package com.stukans.advent;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {

    public static Character[] convert(char[] hands) {
        Character[] characterArray = new Character[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }

    public static char[] convert(Character[] hands) {
        char[] characterArray = new char[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }

    public static String unfold(String input, int unfold) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < unfold; i++) {
            builder.append(input);
            if (i < unfold - 1) {
                builder.append("?");
            }
        }
        return builder.toString();
    }

    public static Integer[] unfold(Integer[] values, int unfold) {
        return IntStream.range(0, unfold).boxed().map(it -> values).flatMap(Stream::of).toArray(Integer[]::new);
    }
}
