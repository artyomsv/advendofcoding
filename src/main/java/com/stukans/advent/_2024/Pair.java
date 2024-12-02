package com.stukans.advent._2024;

public class Pair<LEFT, RIGHT> {

    private final LEFT left;
    private final RIGHT right;

    public static <LEFT, RIGHT> Pair<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        return new Pair<>(left, right);
    }

    private Pair(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    public LEFT getLeft() {
        return left;
    }

    public RIGHT getRight() {
        return right;
    }
}
