package com.stukans.advent._2023.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MirageMaintenance {

    public long solve(List<String> input, boolean partOne) {

        long rightResult = 0;
        long leftResult = 0;

        for (String s : input) {
            List<Long[]> listValues = new ArrayList<>();
            listValues.add(
                    Stream.of(s.split(" "))
                            .map(String::strip)
                            .filter(it -> !it.isBlank())
                            .map(Long::parseLong)
                            .toArray(Long[]::new)
            );

            boolean allzeros = false;
            Long[] current = listValues.get(0);

            List<Long> lefts = new ArrayList<>();
            lefts.add(current[0]);
            List<Long> rights = new ArrayList<>();
            rights.add(current[current.length - 1]);

            while (!allzeros) {
                Long[] newArr = new Long[current.length - 1];
                for (int i = 0; i < current.length - 1; i++) {
                    newArr[i] = current[i + 1] - current[i];

                    if (i == 0) {
                        lefts.add(newArr[i]);
                    }

                    if (i == current.length - 2) {
                        rights.add(newArr[i]);
                    }
                }
                listValues.add(newArr);
                current = newArr;
                if (Stream.of(newArr).allMatch(it -> it == 0L)) {
                    allzeros = true;
                }
            }

            if (partOne) {
                long rightExtrapolatedValue = 0;
                for (int j = rights.size() - 1; j > 0; j--) {
                    Long l = rights.get(j - 1);
                    rightExtrapolatedValue = rightExtrapolatedValue + l;
                }
                rightResult += rightExtrapolatedValue;
            }

            if (!partOne) {
                long leftExtrapolatedValue = 0;
                for (int i = lefts.size() - 1; i >= 0; i--) {
                    Long longs = lefts.get(i);
                    leftExtrapolatedValue = longs - leftExtrapolatedValue;
                }
                leftResult += leftExtrapolatedValue;
            }


        }


        return partOne ? rightResult : leftResult;
    }

}
