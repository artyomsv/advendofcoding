package com.stukans.advent._2023.day3;

import java.util.ArrayList;
import java.util.List;

public class GearRatios {


    public int solve(char[][] input, boolean part2) {
        List<Node> nodes = buildSpecialCharNodes(input);

        if (part2) {
            nodes = nodes.stream().filter(Node::isAsterixNode).toList();
        }

        return calculateResult(input, part2, nodes);

    }

    private static Integer calculateResult(char[][] input, boolean part2, List<Node> nodes) {
        return nodes.stream()
                .map(it -> it.find(input))
                .map(node -> {
                    if (part2) {
                        return node.pow();
                    }
                    return node.sum();
                })
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static List<Node> buildSpecialCharNodes(char[][] input) {
        List<Node> nodes = new ArrayList<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                char current = input[y][x];
                if (!Character.isDigit(current) && current != '.') {
                    nodes.add(new Node(current, new Location(x, y)));
                }

            }
        }
        return nodes;
    }


}
