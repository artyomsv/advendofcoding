package com.stukans.advent._2023.day5;

import java.util.Iterator;
import java.util.List;

public class SimpleSeedProvider implements SeedProvider {

    private final List<Long> list;
    private Iterator<Long> iterator = null;

    public SimpleSeedProvider(List<Long> list) {
        this.list = list;
    }

    @Override
    public Long next() {
        if (iterator == null) {
            iterator = list.iterator();
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (iterator == null) {
            iterator = list.iterator();
        }
        return iterator.hasNext();
    }

}
