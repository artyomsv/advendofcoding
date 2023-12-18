package com.stukans.advent.day17;

import java.util.Objects;
import java.util.Optional;

public final class Location {

    private final int x;
    private final int y;
    private Direction direction;

    private boolean isFurtherStep = false;
    private int furtherStepsCount = 0;

    private Optional<Location> left = Optional.empty();
    private Optional<Location> further = Optional.empty();
    private Optional<Location> right = Optional.empty();

    private Location previous;

    public static Location of(int x, int y) {
        Location location = new Location(x, y, null);
        return location;
    }

    public static Location of(int x, int y, Direction direction) {
        Location location = new Location(x, y, direction);
        return location;
    }

    private Location(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Optional<Location> toLeft(int[][] array) {
        Optional<Location> left = switch (direction) {
            case N -> west(array);
            case W -> south(array);
            case S -> east(array);
            case E -> north(array);
        };
        left.ifPresent(it -> {
            this.left = Optional.of(it);
            it.previous = this;
        });
        return left;
    }

    public Optional<Location> further(int[][] array) {
        Optional<Location> further = switch (direction) {
            case N -> north(array);
            case W -> west(array);
            case S -> south(array);
            case E -> east(array);
        };
        further.ifPresent(it -> {
            this.further = Optional.of(it);
            it.previous = this;
            it.isFurtherStep = true;
            if (this.isFurtherStep) {
                it.furtherStepsCount = this.furtherStepsCount + 1;
            }
        });
        return further.flatMap(location -> location.furtherStepsCount >= 3 ? Optional.empty() : Optional.of(location));
    }

    public Optional<Location> toRight(int[][] array) {
        Optional<Location> right = switch (direction) {
            case N -> east(array);
            case W -> north(array);
            case S -> west(array);
            case E -> south(array);
        };
        right.ifPresent(it -> {
            this.right = Optional.of(it);
            it.previous = this;
        });
        return right;
    }

    public boolean endReached(int[][] array) {
        return x == array[0].length - 1 && y == array.length - 1;
    }

    public boolean inBounds(int[][] array) {
        return (x >= 0 && x < array[0].length) && (y >= 0 && y < array.length);
    }

    private boolean inBounds(int x, int y, int[][] array) {
        return (x >= 0 && x < array[0].length) && (y >= 0 && y < array.length);
    }

    private Optional<Location> north(int[][] array) {
        if (inBounds(x, y - 1, array)) {
            return Optional.of(new Location(x, y - 1, Direction.N));
        }
        return Optional.empty();

    }

    private Optional<Location> west(int[][] array) {
        if (inBounds(x - 1, y, array)) {
            return Optional.of(new Location(x - 1, y, Direction.W));
        }
        return Optional.empty();
    }

    private Optional<Location> south(int[][] array) {
        if (inBounds(x, y + 1, array)) {
            return Optional.of(new Location(x, y + 1, Direction.S));
        }
        return Optional.empty();
    }

    private Optional<Location> east(int[][] array) {
        if (inBounds(x + 1, y, array)) {
            return Optional.of(new Location(x + 1, y, Direction.E));
        }
        return Optional.empty();
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

    public int calcWeight(int[][] array) {
        return array[y][x];
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Node getNode(Node[][] nodes) {
        return nodes[y][x];
    }
}
