package com.stukans.advent._2024;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    public boolean isInside(int xMax, int yMax) {
        return x >= 0 && y >= 0 && x < xMax && y < yMax;
    }

    public <T> boolean isInside(T[][] array, Class<T> clazz) {
        int yMax = array.length;
        int xMax = array[0].length;
        return x >= 0 && y >= 0 && x < xMax && y < yMax;
    }

    public boolean isInside(char[][] array) {
        int yMax = array.length;
        int xMax = array[0].length;
        return x >= 0 && y >= 0 && x < xMax && y < yMax;
    }

    public <T> T getValue(T[][] array, Class<T> clazz) {
        return array[y][x];
    }

    public char getValue(char[][] arr) {
        return arr[y][x];
    }

    public boolean equals(char[][] characters, char c) {
        return characters[y][x] == c;
    }

    public Collection<Coordinates> neighbors() {
        Set<Coordinates> neighbours = new LinkedHashSet<>();
        neighbours.add(move(Direction.NORTH));
        neighbours.add(move(Direction.SOUTH));
        neighbours.add(move(Direction.EAST));
        neighbours.add(move(Direction.WEST));
        return neighbours;
    }

//    public Set<Coordinates> neighbours() {
//        Set<Coordinates> neighbours = new LinkedHashSet<>();
//
//        for (Direction value : Direction.values()) {
//            neighbours.add(move(value));
//        }
//
//        return neighbours;
//    }

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
