package com.stukans.advent._2023.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    final int number;
    final List<Integer> winningNumbers;
    final Set<Integer> numbersYouHave;

    List<Game> copies = new ArrayList<>();

    public Game(int number, List<Integer> winningNumbers, Set<Integer> numbersYouHave) {
        this.number = number;
        this.winningNumbers = winningNumbers;
        this.numbersYouHave = numbersYouHave;
    }

    public void add(Game copy) {
        copies.add(copy);
    }

    public void add(List<Game> copies) {
        this.copies.addAll(copies);
    }

    public Game(int number, String input) {
        this.number = number;
        String[] s1 = input.strip().split("\\|");
        winningNumbers = Stream.of(s1[0].strip().split(" ")).map(String::strip).filter(it -> !it.isBlank()).map(Integer::parseInt).toList();
        numbersYouHave = Stream.of(s1[1].strip().split(" ")).map(String::strip).filter(it -> !it.isBlank()).map(Integer::parseInt).collect(Collectors.toSet());
    }

    public int scores() {
        int result = 0;

        for (Integer winningNumber : winningNumbers) {
            if (numbersYouHave.contains(winningNumber)) {
                if (result == 0) {
                    result = 1;
                } else {
                    result *= 2;
                }
            }
        }
        return result;
    }

    public int wins() {
        int result = 0;

        for (Integer winningNumber : winningNumbers) {
            if (numbersYouHave.contains(winningNumber)) {
                result += 1;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return number == game.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
