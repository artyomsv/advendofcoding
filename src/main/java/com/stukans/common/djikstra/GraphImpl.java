package com.stukans.common.djikstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphImpl implements Graph {

    private final Integer[][] matrix;
    private final Coordinates end;

    public GraphImpl(Integer[][] matrix, Coordinates end) {
        this.matrix = matrix;
        this.end = end;
    }

    @Override
    public Node build() {
        Coordinates start = new Coordinates(0, 0);
        final Node root = NodeImpl.root(start);
        Step initial = StepImpl.of(start, Direction.EAST);

        Map<Coordinates, Node> nodes = new HashMap<>();
        LinkedList<Step> queue = new LinkedList<>();
        initial.forward(matrix).ifPresent(e -> {
            queue.add(e);
        });
        initial.right(matrix).ifPresent(queue::add);

        while (!queue.isEmpty()) {
            Step step = queue.poll();
            Coordinates coordinates = step.getCoordinates();

            Node parent = step.getPrevious()
                    .map(it -> {
                        if (it.getCoordinates().isInitial()) {
                            return root;
                        }
                        return nodes.get(it.getCoordinates());
                    }).get();

            Node child = nodes.getOrDefault(coordinates, NodeImpl.of(coordinates, matrix[coordinates.y()][coordinates.x()], parent));
            nodes.put(coordinates, child);
            parent.addChild(child);


            if (step.getCoordinates().equals(end)) {
                root.addTail(child);
                continue;
            }

            step.forward(matrix).ifPresent(e -> {
                Node node = nodes.getOrDefault(e.getCoordinates(), NodeImpl.of(e.getCoordinates(), matrix[e.getCoordinates().y()][e.getCoordinates().x()], child));
                nodes.put(e.getCoordinates(), node);
                if (!node.getCoordinates().equals(end) && node.getChildren().isEmpty()) {
                    queue.add(e);
                }
                child.addChild(node);
                node.addParent(child);

//                if (!(child.getChildren().contains(node)) && !node.parents().contains(child)) {
//                    queue.add(e);
//                }

//                if (!child.parents().contains(node) && !node.getChildren().contains(child)) {
//                    child.addChild(node);
//                    node.addParent(child);
//                }
            });
            step.left(matrix).ifPresent(e -> {
                Node node = nodes.getOrDefault(e.getCoordinates(), NodeImpl.of(e.getCoordinates(), matrix[e.getCoordinates().y()][e.getCoordinates().x()], child));
                nodes.put(e.getCoordinates(), node);
                if (!node.getCoordinates().equals(end) && node.getChildren().isEmpty()) {
                    queue.add(e);
                }
                child.addChild(node);
                node.addParent(child);

//                if (!(child.getChildren().contains(node)) && !node.parents().contains(child)) {
//                    queue.add(e);
//                }

//                if (!child.parents().contains(node) && !node.getChildren().contains(child)) {
//                    child.addChild(node);
//                    node.addParent(child);
//                }
            });
            step.right(matrix).ifPresent(e -> {
                Node node = nodes.getOrDefault(e.getCoordinates(), NodeImpl.of(e.getCoordinates(), matrix[e.getCoordinates().y()][e.getCoordinates().x()], child));
                nodes.put(e.getCoordinates(), node);
                if (!node.getCoordinates().equals(end) && node.getChildren().isEmpty()) {
                    queue.add(e);
                }
                child.addChild(node);
                node.addParent(child);
//                if (!(child.getChildren().contains(node)) && !node.parents().contains(child)) {
//                    queue.add(e);
//                }

//                if (!child.parents().contains(node) && !node.getChildren().contains(child)) {
//                    child.addChild(node);
//                    node.addParent(child);
//                }
            });

        }

        return root;
    }
}
