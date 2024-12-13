package com.stukans.advent._2024.day13;

import com.stukans.advent._2024.Pair;
import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ClawContraption extends Puzzle {

    public static final Pair<Integer, Integer> EMPTY = Pair.of(-1, -1);
    public int counter = 0;

    public static void main(String[] args) {
        String[] input = List.of(
                "Button A: X+94, Y+34",
                "Button B: X+22, Y+67",
                "Prize: X=8400, Y=5401"
        ).toArray(new String[0]);

        //Solved with equation system:
        //
        //  A*94 + B*22 = 8400
        //  A*34 + B*67 = 5400
        //

        ClawContraption puzzle = new ClawContraption();
        List<Game> games = puzzle.parseGames(input, 0L);

        long solve = puzzle.solve(games);


        System.out.println(solve);
    }


    @Override
    public long solution1(File file) {

        String[] lines = load(file);

        List<Game> games = parseGames(lines, 0L);
        return solve(games);
    }

    private long solve(List<Game> games) {
        long solution = 0;
        for (Game game : games) {
            long lcm = lcm(game.a.x, game.a.y);

            long y = lcm / game.a.x;
            long z = lcm / game.a.y;
            Prize p = game.p();

            long top = p.y() * z - p.x() * y;
            long bottom = game.b.y() * z - game.b.x() * y;

            long b = top / bottom;
            long a = (p.x() - game.b.x() * b) / game.a.x();

            if (a * game.a.y() + b * game.b.y() == p.y && a * game.a.x() + b * game.b.x() == p.x) {
                solution += a * 3L + b;
            }
        }
        return solution;
    }

    @Override
    public long solution2(File file) {
        String[] lines = load(file);

        List<Game> games = parseGames(lines, 10000000000000L);
        return solve(games);
    }

    private List<Game> parseGames(String[] lines, Long prizeCorrection) {
        List<Game> games = new ArrayList<>();
        Button a = null, b = null;
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }
            if (line.startsWith("Button A")) {
                a = new Button(line);
            } else if (line.startsWith("Button B")) {
                b = new Button(line);
            } else if (line.startsWith("Prize")) {
                games.add(new Game(a, b, new Prize(line, prizeCorrection)));
            }


        }
        return games;
    }

    private static class Button {

        private final long x;
        private final long y;

        private Button(long x, long y) {
            this.x = x;
            this.y = y;
        }

        private Button(String input) {
            String[] split = input.split(":");
            List<Integer> list = Arrays.stream(split[1].split(",")).map(String::trim).map(it -> Integer.parseInt(it.split("\\+")[1])).toList();
            Iterator<Integer> iterator = list.iterator();
            this.x = iterator.next();
            this.y = iterator.next();
        }

        public long x() {
            return x;
        }

        public long y() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Button) obj;
            return this.x == that.x &&
                    this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Button[" +
                    "x=" + x + ", " +
                    "y=" + y + ']';
        }

    }

    private static final class Prize {
        private final long x;
        private final long y;

        private Prize(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Prize(String input, Long prizeCorrection) {
            String[] split = input.split(":");
            List<Long> list = Arrays.stream(split[1].split(",")).map(String::trim).map(it -> Long.parseLong(it.split("=")[1])).map(it -> it + prizeCorrection).toList();
            Iterator<Long> iterator = list.iterator();
            this.x = iterator.next();
            this.y = iterator.next();
        }

        public long x() {
            return x;
        }

        public long y() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Prize) obj;
            return this.x == that.x &&
                    this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Prize[" +
                    "x=" + x + ", " +
                    "y=" + y + ']';
        }

    }

    private record Game(Button a, Button b, Prize p) {
    }

}
