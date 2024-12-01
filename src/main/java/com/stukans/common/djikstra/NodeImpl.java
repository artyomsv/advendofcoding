package com.stukans.common.djikstra;

import java.util.*;

public class NodeImpl implements Node {

    private final Coordinates coordinates;
    private final Integer weight;
    private Integer totalWeight;
    private Set<Node> parents;
    private Set<Node> children;

    private Node tail;

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
        if (parent != null) {
            this.parents = new HashSet<>();
            parents.add(parent);
        }
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
            children = new HashSet<>();
        }
        children.add(node);
        return this;
    }

    @Override
    public Node addParent(Node node) {
        if (parents == null) {
            parents = new HashSet<>();
        }
        parents.add(node);
        return this;
    }

    @Override
    public Collection<Node> getChildren() {
        return children == null ? Collections.emptyList() : Collections.unmodifiableSet(children);
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
    public Collection<Node> parents() {
        return parents == null ? Collections.emptyList() : Collections.unmodifiableSet(parents);
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

    @Override
    public String toString() {
        return coordinates.toString();
    }

    @Override
    public void addTail(Node tail) {
        if (this.tail == null) {
            this.tail = tail;
        }
    }

    @Override
    public Optional<Node> getTail() {
        return Optional.ofNullable(tail);
    }

    @Override
    public void printPath() {
        if (getTail().isEmpty()) {
            System.out.println("Tail not found");
            return;
        }

        HashSet<Coordinates> set = new HashSet<>();
        Coordinates coordinates = tail.getCoordinates();
        set.add(coordinates);
        List<Set<Coordinates>> traverse = traverse(tail, set);
        for (Set<Coordinates> coordinatesSet : traverse) {
            for (int y = 0; y < coordinates.y() + 1; y++) {
                System.out.println();
                for (int x = 0; x < coordinates.x() + 1; x++) {
                    System.out.print(coordinatesSet.contains(new Coordinates(x, y)) ? '#' : '.');
                }
            }
            System.out.println();
        }
    }

    private List<Set<Coordinates>> traverse(Node node, Set<Coordinates> coordinates) {
        Collection<Node> parents = node.parents();
        if (parents.isEmpty()) {
            Set<Coordinates> set = new HashSet<>(coordinates);
            List<Set<Coordinates>> list = new ArrayList<>();
            list.add(set);
            return list;
        }

        Iterator<Node> iterator = parents.iterator();
        if (parents.size() == 1) {
            Set<Coordinates> set = new HashSet<>(coordinates);
            Node parent = iterator.next();
            set.add(parent.getCoordinates());
            return traverse(parent, set);
        } else {
            List<Set<Coordinates>> list = new ArrayList<>();
            while (iterator.hasNext()) {
                Node parent = iterator.next();
                HashSet<Coordinates> set = new HashSet<>(coordinates);
                set.add(parent.getCoordinates());
                List<Set<Coordinates>> traverse = traverse(parent, set);
                list.addAll(traverse);
            }
            return list;
        }
    }

}
