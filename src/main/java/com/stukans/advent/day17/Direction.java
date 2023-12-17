package com.stukans.advent.day17;

public enum Direction {
    N, W, S, E;

    public Direction toLeft() {
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }

    public Direction toRight() {
        return switch (this) {
            case N -> E;
            case W -> N;
            case S -> W;
            case E -> S;
        };
    }

}
