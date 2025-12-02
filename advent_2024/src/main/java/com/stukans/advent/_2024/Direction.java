package com.stukans.advent._2024;

import java.util.List;

public enum Direction {
    NORTH(0, -1, '^'),
    //    NORTH_EAST(1, -1),
    EAST(1, 0, '>'),
    //    SOUTH_EAST(1, 1),
    SOUTH(0, 1, 'v'),
    //    SOUTH_WEST(-1, 1),
    WEST(-1, 0, '<');
    //    NORTH_WEST(-1, -1);

    private final int x;
    private final int y;
    private final char arrow;

    private static final List<Direction> WEST_FURTHER = List.of(WEST, SOUTH, NORTH);
    private static final List<Direction> EAST_FURTHER = List.of(NORTH, SOUTH, EAST);
    private static final List<Direction> SOUTH_FURTHER = List.of(WEST, EAST, SOUTH);
    private static final List<Direction> NORTH_FURTHER = List.of(EAST, WEST, NORTH);

    Direction(int x, int y, char arrow) {
        this.x = x;
        this.y = y;
        this.arrow = arrow;
    }

    public Coordinate nextCoordinates(Coordinate coordinate) {
        return Coordinate.of(coordinate.x() + x, coordinate.y() + y);
    }

    public Direction next() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }

    public List<Direction> further() {
        return switch (this) {
            case NORTH -> NORTH_FURTHER;
            case SOUTH -> SOUTH_FURTHER;
            case EAST -> EAST_FURTHER;
            case WEST -> WEST_FURTHER;
        };
    }

    public char getArrow() {
        return arrow;
    }

}