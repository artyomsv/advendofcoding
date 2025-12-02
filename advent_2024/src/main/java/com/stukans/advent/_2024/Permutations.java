package com.stukans.advent._2024;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class Permutations {

    public static <T> List<Set<T>> permutationUnique(List<T> possibleNodes, int depth, Class<T> clazz) {
        List<Set<T>> initialState = new LinkedList<>();
        for (T possibleNode : possibleNodes) {
            initialState.add(Set.of(possibleNode));
        }
        return permutation(initialState, possibleNodes, depth, clazz, HashSet::new)
                .stream().filter(ts -> ts.size() == depth).toList();
    }

    public static <T> List<List<T>> permutation(List<T> possibleNodes, int depth, Class<T> clazz) {
        List<List<T>> initialState = new LinkedList<>();
        for (T possibleNode : possibleNodes) {
            initialState.add(List.of(possibleNode));
        }
        return permutation(initialState, possibleNodes, depth, clazz, LinkedList::new);
    }

    private static <T, E extends Collection<T>> List<E> permutation(List<E> permutations, List<T> possibleNodes, int depth, Class<T> clazz, Supplier<E> supplier) {
        if (depth <= 1) {
            return permutations;
        }

        List<E> newPermutations = new LinkedList<>();
        for (E permutation : permutations) {
            for (T possibleNextNode : possibleNodes) {
                E newLeaf = supplier.get();
                newLeaf.addAll(permutation);
                newLeaf.add(possibleNextNode);
                newPermutations.add(newLeaf);
            }
        }
        return permutation(newPermutations, possibleNodes, depth - 1, clazz, supplier);
    }
}
