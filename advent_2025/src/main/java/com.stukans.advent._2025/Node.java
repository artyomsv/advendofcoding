package com.stukans.advent._2025;

public interface Node<T> {

    T getValue();

    Node<T> getNext();

    Node<T> getPrev();


}
