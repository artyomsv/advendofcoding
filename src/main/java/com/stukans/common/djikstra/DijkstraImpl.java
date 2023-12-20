package com.stukans.common.djikstra;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraImpl implements Dijkstra {

    private final Graph graph;
    private final Coordinates end;

    public DijkstraImpl(Graph graph, Coordinates end) {
        this.graph = graph;
        this.end = end;
    }

    public void solve() {
        Node root = graph.build();

        Set<Node> settled = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (settled.contains(current)) {
                continue;
            }

            List<Node> children = current.getChildren();
            for (Node child : children) {
                Integer combinedWeight = current.getTotalWeight() + child.getWeight();
                if (combinedWeight < child.getTotalWeight()) {
                    child.setTotalWeight(combinedWeight);
                }
                queue.add(child);
            }

            settled.add(current);
        }

    }

}
