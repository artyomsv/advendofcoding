package com.stukans.advent.day5;

import java.util.Iterator;
import java.util.List;
import java.util.stream.LongStream;

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

    @Override
    public long size() {
        return list.size();
    }

    @Override
    public LongStream stream() {
        long[] array = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);

        }
        return LongStream.of(array);
    }
}
