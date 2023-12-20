package com.stukans.common.djikstra;

import java.util.Optional;

public interface Node extends Comparable<Node> {

    Node withWeight(Integer weight);

    Integer getTotalWeight();

    Node setTotalWeight(Integer totalWeight);

    Node increaseWeight(Integer weight);

    Node addChild(Node node);

    java.util.List<Node> getChildren();

    Coordinates getCoordinates();

    Integer getWeight();

    Optional<Node> parent();

}
