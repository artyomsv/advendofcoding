package com.stukans.advent._2025.day2;

import com.stukans.advent._2025.Puzzle;

import java.io.File;

class GiftShop extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] load = load(file, ",");
        long result = 0;
        for (String values : load) {
            String[] split = values.split("-");
            Long from = Long.parseLong(split[0].trim());
            Long to = Long.parseLong(split[1].trim());
            for (long i = from; i <= to; i++) {
                boolean invalid = InvalidIdIdentifier.isInvalid1(String.valueOf(i));
                if (invalid) {
                    result += i;
                }
            }
        }
        return result;
    }

    @Override
    public long solution2(File file) {
        String[] load = load(file, ",");
        long result = 0;
        for (String values : load) {
            String[] split = values.split("-");
            Long from = Long.parseLong(split[0].trim());
            Long to = Long.parseLong(split[1].trim());
            for (long i = from; i <= to; i++) {
                boolean invalid = InvalidIdIdentifier.isInvalid2(String.valueOf(i));
                if (invalid) {
                    result += i;
                }
            }
        }
        return result;
    }

}
