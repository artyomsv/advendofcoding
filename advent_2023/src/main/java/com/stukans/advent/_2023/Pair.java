package com.stukans.advent._2023;

public class Pair<T, V> {

    private final T t;
    private final V v;


    public Pair(T t, V v) {
        this.t = t;
        this.v = v;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }
}
