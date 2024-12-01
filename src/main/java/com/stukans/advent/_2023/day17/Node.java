package com.stukans.advent._2023.day17;

import java.util.Objects;

class Node {

    private final Location location;
    private int weight;

    public Node(Location location, int weight) {
        this.location = location;
        this.weight = weight;
    }

    public Node(Location location) {
        this.location = location;
        this.weight = Integer.MAX_VALUE;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(location, node.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", weight=" + weight +
                '}';
    }
}
