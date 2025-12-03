package com.stukans.advent._2025.day2;

import java.util.Arrays;

public class InvalidIdIdentifier {

    public static boolean isInvalid1(String number) {
        StringBuilder sb = new StringBuilder(number);

        if (number.length() % 2 == 1) {
            return false;
        }

        boolean same = true;
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) != sb.charAt(i - 1)) {
                same = false;
            }
        }
        if (same) {
            return true;
        }

        String left = number.substring(0, number.length() / 2);
        String right = number.substring(number.length() / 2);

        return left.equals(right);

    }

    public static boolean isInvalid2(String number) {
        if (number.length() == 1) {
            return false;
        }

        StringBuilder sb = new StringBuilder(number);

        boolean same = true;
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) != sb.charAt(i - 1)) {
                same = false;
            }
        }
        if (same) {
            System.out.printf("%s\n", number);
            return true;
        }

        int chunks = 2;
        do {
            String[] strings = splitIntoChunks(sb, chunks);
            if (strings.length == chunks) {


                boolean notValid = true;
                for (int i = 0; i < strings.length - 1; i++) {
                    if (!strings[i].equals(strings[i + 1])) {
                        notValid = false;
                        break;
                    }
                }

                if (notValid) {
                    System.out.println(Arrays.toString(strings));
                    return true;
                }

                if (strings[0].length() == 1) {
                    return false;
                } else {
                    chunks++;
                }
            }  else {
                chunks++;
            }
        } while (true);
    }

    public static String[] splitIntoChunks(StringBuilder sb, int chunks) {
        if (sb.length() % chunks == 0) {
            String[] result = new String[chunks];
            int start = 0;
            int end = sb.length() / chunks;
            int step = end;
            for (int i = 0; i < chunks; i++) {
                result[i] = sb.substring(start, end);
                start += step;
                end += step;

            }
            return result;
        }
        return new String[0];
    }


}
