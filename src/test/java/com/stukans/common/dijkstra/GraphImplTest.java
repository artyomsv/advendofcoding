package com.stukans.common.dijkstra;

import com.stukans.common.djikstra.Coordinates;
import com.stukans.common.djikstra.Graph;
import com.stukans.common.djikstra.GraphImpl;
import com.stukans.common.djikstra.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class GraphImplTest {

    @Test
    void simpleUseCase() {
        Integer[][] matrix = new Integer[][]{
                {1, 2},
                {3, 4},
        };

        Graph graph = new GraphImpl(matrix, new Coordinates(1, 1));

        Node root = graph.build();
        root.printPath();

        Assertions.assertEquals(0, root.getWeight());
        Assertions.assertEquals(2, root.getChildren().size());
        Iterator<Node> iterator = root.getChildren().iterator();
        Node next = iterator.next();
        Assertions.assertEquals(2, next.getWeight());
        Assertions.assertEquals(new Coordinates(1, 0), next.getCoordinates());
        Assertions.assertEquals(1, next.getChildren().size());
        Node endNodePath1 = next.getChildren().iterator().next();

        next = iterator.next();
        Assertions.assertEquals(3, next.getWeight());
        Assertions.assertEquals(new Coordinates(0, 1), next.getCoordinates());
        Assertions.assertEquals(1, next.getChildren().size());
        Node endNodePath2 = next.getChildren().iterator().next();

        Assertions.assertSame(endNodePath1, endNodePath2);
    }

    @Test
    void simpleUseCase2() {
        Graph graph = new GraphImpl(
                new Integer[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                },
                new Coordinates(2, 1)
        );

        Node root = graph.build();
        root.printPath();

//        Assertions.assertEquals(0, root.getWeight());
//        Assertions.assertEquals(2, root.getChildren().size());
//        Iterator<Node> iterator = root.getChildren().iterator();
//        Node next = iterator.next();
//        Assertions.assertEquals(2, next.getWeight());
//        Assertions.assertEquals(new Coordinates(1, 0), next.getCoordinates());
//        Assertions.assertEquals(1, next.getChildren().size());
//        Node endNodePath1 = next.getChildren().iterator().next();
//
//        next = iterator.next();
//        Assertions.assertEquals(3, next.getWeight());
//        Assertions.assertEquals(new Coordinates(0, 1), next.getCoordinates());
//        Assertions.assertEquals(1, next.getChildren().size());
//        Node endNodePath2 = next.getChildren().iterator().next();
//
//        Assertions.assertSame(endNodePath1, endNodePath2);
    }

}