package com.stukans.advent._2024;

import java.util.LinkedList;
import java.util.List;

public class Permutations {


    public static <T> List<List<T>> permutation(List<T> possibleNodes, int depth, Class<T> clazz) {
        List<List<T>> initialState = new LinkedList<>();
        for (T possibleNode : possibleNodes) {
            initialState.add(List.of(possibleNode));
        }
        return permutation(initialState, possibleNodes, depth, clazz);
    }

    private static <T> List<List<T>> permutation(List<List<T>> permutations, List<T> possibleNodes, int depth, Class<T> clazz) {
        if (depth <= 1) {
            return permutations;
        }

        List<List<T>> newPermutations = new LinkedList<>();
        for (List<T> permutation : permutations) {
            for (T possibleNextNode : possibleNodes) {
                LinkedList<T> newLeaf = new LinkedList<>(permutation);
                newLeaf.add(possibleNextNode);
                newPermutations.add(newLeaf);
            }
        }
        return permutation(newPermutations, possibleNodes, depth - 1, clazz);
    }
}
