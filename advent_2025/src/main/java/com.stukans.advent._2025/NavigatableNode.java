package com.stukans.advent._2025;

public class NavigatableNode<T> implements Node<T> {

    private final T value;
    private NavigatableNode<T> next;
    private NavigatableNode<T> previous;

    public NavigatableNode(T value) {
        this.value = value;
    }

    public NavigatableNode(T value, NavigatableNode<T> next, NavigatableNode<T> previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public void setNext(NavigatableNode<T> next) {
        this.next = next;
    }

    public void setPrevious(NavigatableNode<T> previous) {
        this.previous = previous;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Node<T> getNext() {
        return next;
    }

    @Override
    public Node<T> getPrev() {
        return previous;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
