package com.stukans.advent._2023.day18;

import java.util.Map;

enum Direction {
    U(0, -1, Constants.U_MAP),
    R(1, 0, Constants.R_MAP),
    D(0, 1, Constants.D_MAP),
    L(-1, 0, Constants.L_MAP);
    public final int x;
    public final int y;
    public final Map<String, Character> mapping;

    Direction(int x, int y, Map<String, Character> mapping) {
        this.x = x;
        this.y = y;
        this.mapping = mapping;
    }

    public char decide(Direction direction) {
        return mapping.get(direction.name());
    }

    private static class Constants {
        static final Map<String, Character> U_MAP = Map.of("L", '┐', "R", '┌');
        static final Map<String, Character> R_MAP = Map.of("U", '┘', "D", '┐');
        static final Map<String, Character> D_MAP = Map.of("L", '┘', "R", '└');
        static final Map<String, Character> L_MAP = Map.of("U", '└', "D", '┌');
    }

}
