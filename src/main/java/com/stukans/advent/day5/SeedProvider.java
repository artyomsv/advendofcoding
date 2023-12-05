package com.stukans.advent.day5;

import java.util.stream.LongStream;

public interface SeedProvider {

    Long next();

    boolean hasNext();

    long size();

    LongStream stream();

}
