package com.stukans.advent._2024.day4;

import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.List;

public class CeresSearch extends Puzzle {

    @Override
    public long solution1(File file) {
        long answer = 0;

        char[][] characters = asCharacters(file);

        answer += calculate(characters);
        answer += calculate(rotate90Clockwise(characters));
        answer += calculate(rotate45Right(characters));
        answer += calculate(rotate45Left(characters));

        return answer;
    }

    @Override
    public long solution2(File file) {
        long answer = 0;

        char[][] characters = asCharacters(file);

        printWithCharacterPredicates(characters, character -> character == 'A');

        for (int y = 1; y < characters.length - 1; y++) {
            for (int x = 1; x < characters[y].length - 1; x++) {
                char c = characters[y][x];
                if (c != 'A') {
                    continue;
                }

                String s1 = String.valueOf(characters[y - 1][x - 1]) + c + characters[y + 1][x + 1];
                String s2 = String.valueOf(characters[y - 1][x + 1]) + c + characters[y + 1][x - 1];

                if ((s1.equals("MAS") || s1.equals("SAM")) && (s2.equals("MAS") || s2.equals("SAM"))) {
                    System.out.println(x + " " + y + " " + c);
                    answer++;
                }


            }
        }
        return answer;
    }

    private long calculate(List<String> list) {
        long answer = 0;
        for (String s : list) {
            answer += calculateLine(s);
        }
        return answer;
    }

    private long calculate(char[][] characters) {
        long answer = 0;
        for (char[] character : characters) {
            String line = new String(character);
            answer += calculateLine(line);
        }
        return answer;
    }

    private static long calculateLine(String line) {
        if (line.length() < 4) {
            return 0;
        }

        int start = 0;
        long answer = 0;
        while ((start = line.indexOf("XMAS", start)) != -1) {
            answer++;
            start += 4;

        }
        start = 0;
        while ((start = line.indexOf("SAMX", start)) != -1) {
            answer++;
            start += 4;
        }
        return answer;
    }
}
