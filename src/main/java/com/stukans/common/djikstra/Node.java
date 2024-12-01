package com.stukans.common.djikstra;

import java.util.Collection;
import java.util.Optional;

public interface Node extends Comparable<Node> {

    Integer getTotalWeight();

    Node setTotalWeight(Integer totalWeight);

    Node increaseWeight(Integer weight);

    Node addChild(Node node);

    Node addParent(Node node);

    Collection<Node> getChildren();

    Coordinates getCoordinates();

    Integer getWeight();

    Collection<Node> parents();

    void addTail(Node tail);

    Optional<Node> getTail();

    void printPath();
}
