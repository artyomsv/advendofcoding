package com.stukans.common.djikstra;

public enum Direction {

    NORTH,
    WEST,
    SOUTH,
    EAST;

    public Direction forward() {
        return this;
    }

    public Direction left() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    public Direction right() {
        return switch (this) {
            case NORTH -> EAST;
            case WEST -> NORTH;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
        };
    }

}
