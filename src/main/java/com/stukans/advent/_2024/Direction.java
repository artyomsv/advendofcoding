package com.stukans.advent._2024;

public enum Direction {
    NORTH(0, -1),
    //    NORTH_EAST(1, -1),
    EAST(1, 0),
    //    SOUTH_EAST(1, 1),
    SOUTH(0, 1),
    //    SOUTH_WEST(-1, 1),
    WEST(-1, 0);
//    NORTH_WEST(-1, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates nextCoordinates(Coordinates coordinates) {
        return Coordinates.of(coordinates.x() + x, coordinates.y() + y);
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
}