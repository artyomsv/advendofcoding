package com.stukans.advent.day5;

import java.util.Objects;

public class Pair {

    public final long left;
    public final long right;

    public Pair(long left, long right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return left == pair.left && right == pair.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "[" + left +
                "," + right +
                ']';
    }
}
