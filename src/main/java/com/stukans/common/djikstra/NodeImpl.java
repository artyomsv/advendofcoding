package com.stukans.common.djikstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NodeImpl implements Node {

    private final Coordinates coordinates;
    private final Integer weight;
    private final Node parent;
    private Integer totalWeight;
    private List<Node> children;

    public static Node root(Coordinates coordinates) {
        return of(coordinates, 0, 0, null);
    }

    public static Node of(Coordinates coordinates, Integer weight, Node parent) {
        return new NodeImpl(coordinates, weight, Integer.MAX_VALUE, parent);

    }

    public static Node of(Coordinates coordinates, Integer weight, Integer totalWeight, Node parent) {
        return new NodeImpl(coordinates, weight, totalWeight, parent);

    }

    private NodeImpl(Coordinates coordinates, Integer weight, Integer totalWeight, Node parent) {
        this.coordinates = coordinates;
        this.weight = weight;
        this.totalWeight = totalWeight;
        this.parent = parent;
    }


    @Override
    public Node withWeight(Integer weight) {
        return new NodeImpl(coordinates, weight, totalWeight, parent);
    }

    @Override
    public Integer getTotalWeight() {
        return getTotalWeight();
    }

    @Override
    public Node setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    @Override
    public Node increaseWeight(Integer weight) {
        this.totalWeight += weight;
        return this;
    }

    @Override
    public Node addChild(Node node) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
        return this;
    }

    @Override
    public List<Node> getChildren() {
        return children == null ? Collections.emptyList() : Collections.unmodifiableList(children);
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public Optional<Node> parent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public int compareTo(Node o) {
        return totalWeight.compareTo(o.getTotalWeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeImpl node = (NodeImpl) o;
        return Objects.equals(coordinates, node.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}
