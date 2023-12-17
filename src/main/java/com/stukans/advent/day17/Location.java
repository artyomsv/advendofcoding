package com.stukans.advent.day17;

import java.util.Objects;
import java.util.Set;

public final class Location {

    private Set<Location> visited;

    private final int x;
    private final int y;
    private final Direction direction;

    public static Location of(int x, int y, Direction direction) {
        return new Location(x, y, direction);
    }

    private Location(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Location toLeft() {
        return switch (direction) {
            case N -> west();
            case W -> south();
            case S -> east();
            case E -> north();
        };
    }

    public Location further() {
        return switch (direction) {
            case N -> north();
            case W -> west();
            case S -> south();
            case E -> east();
        };
    }

    public Location toRight() {
        return switch (direction) {
            case N -> east();
            case W -> north();
            case S -> west();
            case E -> south();
        };
    }

    public boolean endReached(int[][] array) {
        return x == array[0].length - 1 && y == array.length - 1;
    }

    public boolean inBounds(int[][] array) {
        return (x >= 0 && x < array[0].length) && (y >= 0 && y < array.length);
    }

    private Location north() {
        return new Location(x, y - 1, Direction.N);
    }

    private Location west() {
        return new Location(x - 1, y, Direction.W);
    }

    private Location south() {
        return new Location(x, y + 1, Direction.S);
    }

    private Location east() {
        return new Location(x + 1, y, Direction.E);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y && direction == location.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Direction direction() {
        return direction;
    }

    @Override
    public String toString() {
        return "Location[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "direction=" + direction + ']';
    }

}
