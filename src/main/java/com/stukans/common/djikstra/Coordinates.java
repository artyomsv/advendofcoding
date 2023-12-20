package com.stukans.common.djikstra;

import java.util.Objects;

public record Coordinates(int x, int y) {

    public Coordinates forward(Direction direction) {
        return switch (direction) {
            case NORTH -> new Coordinates(x, y - 1);
            case SOUTH -> new Coordinates(x, y + 1);
            case WEST -> new Coordinates(x - 1, y);
            case EAST -> new Coordinates(x + 1, y);
        };
    }

    public Coordinates left(Direction direction) {
        return switch (direction) {
            case NORTH -> new Coordinates(x - 1, y);
            case SOUTH -> new Coordinates(x + 1, y);
            case WEST -> new Coordinates(x, y + 1);
            case EAST -> new Coordinates(x, y - 1);
        };
    }

    public Coordinates right(Direction direction) {
        return switch (direction) {
            case NORTH -> new Coordinates(x + 1, y);
            case SOUTH -> new Coordinates(x - 1, y);
            case WEST -> new Coordinates(x, y - 1);
            case EAST -> new Coordinates(x, y + 1);
        };
    }

    public <T> boolean inBounds(T[][] array) {
        return (x >= 0 && x < array[0].length) && (y >= 0 && y < array.length);
    }

    public boolean isInitial() {
        return x == 0 && y == 0;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ']';
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
