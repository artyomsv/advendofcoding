package com.stukans.advent._2023.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Scratchcards {

    public int part1(List<String> input) {
        return input.stream().map(it -> {
                    String[] s1 = it.split(":");
                    String[] s2 = s1[0].strip().split(" ");
                    String[] s3 = Stream.of(s2).map(String::strip).filter(s -> !s.isBlank()).toArray(String[]::new);
                    return new Game(Integer.parseInt(s3[1].strip()), s1[1].strip());
                })
                .map(Game::scores)
                .reduce(Integer::sum)
                .orElse(0);

    }

    public int part2(List<String> input) {
        List<Game> list = input.stream().map(it -> {
            String[] s1 = it.split(":");
            String[] s2 = s1[0].strip().split(" ");
            String[] s3 = Stream.of(s2).map(String::strip).filter(s -> !s.isBlank()).toArray(String[]::new);
            return new Game(Integer.parseInt(s3[1].strip()), s1[1].strip());
        }).toList();

        Map<Game, Integer> map = new HashMap<>();

        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            Game game = list.get(i);
            updateMap(map, game);
            result += recursion(game, list, i, map);
        }
        return result + list.size();
    }

    private static void updateMap(Map<Game, Integer> map, Game game) {
        if (map.containsKey(game)) {
            map.put(game, map.get(game) + 1);
        } else {
            map.put(game, 1);
        }
    }

    private int recursion(Game game, List<Game> allGames, int currentIndex, Map<Game, Integer> map) {
        int result = 0;
        int wins = game.wins();
        List<Game> copies = allGames.subList(currentIndex + 1, Math.min(currentIndex + 1 + wins, allGames.size()));
        game.add(new ArrayList<>(copies));
        result += copies.size();
        for (int i = 0; i < copies.size(); i++) {
            Game nextGame = copies.get(i);
            updateMap(map, nextGame);
            result += recursion(nextGame, allGames, currentIndex + 1 + i, map);
        }
        return result;
    }


}
