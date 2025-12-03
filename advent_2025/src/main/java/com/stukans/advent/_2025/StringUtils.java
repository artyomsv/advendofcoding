package com.stukans.advent._2025;

public class StringUtils {

    public static String removeLeadingZeroes(String s) {
        return s.replaceAll("^0+", "");
    }

    public static String removeTrailingZeroes(String s) {
        return s.replaceAll("0+$", "");
    }

}
