package com.stukans.advent._2024;

import java.util.Objects;

public class Coordinates {

    private final int x;
    private final int y;

    public static Coordinates of(int x, int y) {
        return new Coordinates(x, y);
    }

    private Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Coordinates move(Direction direction) {
        return direction.nextCoordinates(this);
    }

    @Override
    public String toString() {
        return "[" + x + ":" + y + "]";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
