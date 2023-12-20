package com.stukans.common.dijkstra;

import com.stukans.common.djikstra.Graph;
import com.stukans.common.djikstra.GraphImpl;
import com.stukans.common.djikstra.Node;
import org.junit.jupiter.api.Test;

class GraphImplTest {

    @Test
    void name() {
        Integer[][] matrix = new Integer[][]{
                {1, 2},
                {3, 4},
        };

        Graph graph = new GraphImpl(matrix);

        Node build = graph.build();
        System.out.println();
    }
}