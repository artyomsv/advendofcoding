package com.stukans.advent._2023.day2;

import java.util.ArrayList;
import java.util.List;

public class CubeConundrum {

    public Integer findPossibleGamesSum(List<String> input) {
        List<Game> games = new ArrayList<>();
        for (String text : input) {
            games.add(new Game(text));
        }

        int result = 0;
        for (Game game : games) {
            if (game.isValid()) {
                result += game.getNumber();
            }
        }
        return result;
    }

    public Integer totalPower(List<String> input) {
        List<Game> games = new ArrayList<>();
        for (String text : input) {
            games.add(new Game(text));
        }

        int result = 0;
        for (Game game : games) {
            result += game.power();
        }
        return result;
    }
}
