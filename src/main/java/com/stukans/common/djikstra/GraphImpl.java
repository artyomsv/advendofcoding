package com.stukans.common.djikstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphImpl implements Graph {

    private final Integer[][] matrix;

    public GraphImpl(Integer[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public Node build() {
//        Node[][] nodes = new Node[matrix.length][];
//        for (int y = 0; y < matrix.length; y++) {
//            for (int x = 0; x < matrix[y].length; x++) {
//                nodes[y][x] = NodeImpl.of(new Coordinates(x, y), matrix[y][x]);
//            }
//
//        }

        Coordinates start = new Coordinates(0, 0);
        Node root = NodeImpl.root(start);
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

//            if (nodes.containsKey(coordinates)) {
//                continue;
//            }

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

            if (step.getCoordinates().y() == matrix.length - 1 && step.getCoordinates().x() == matrix[0].length - 1) {
                continue;
            }

            step.forward(matrix).ifPresent(queue::add);
            step.left(matrix).ifPresent(queue::add);
            step.right(matrix).ifPresent(queue::add);

        }

        return root;
    }
}
