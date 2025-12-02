package com.stukans.advent._2025;

import java.util.Objects;

public class
Tuple<F, S, T> {

    private final F first;
    private final S second;
    private final T third;

    public static <F, S, T> Tuple<F, S, T> of(F first, S second, T third) {
        return new Tuple<>(first, second, third);
    }

    private Tuple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    @Override
    public String toString() {
        return "[" + first + "|" + second + "|" + third + ']';
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) o;
        return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second) && Objects.equals(third, tuple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
