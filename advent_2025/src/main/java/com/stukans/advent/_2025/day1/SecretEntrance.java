package com.stukans.advent._2025.day1;

import com.stukans.advent._2025.Puzzle;

import java.io.File;

class SecretEntrance extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] values = load(file);
        SafeClockMechanism mechanism = new SafeClockMechanism(99L, 50L);
        execute(values, mechanism);
        return mechanism.getZeroCount();
    }

    @Override
    public long solution2(File file) {
        String[] values = load(file);
        SafeClockMechanism mechanism = new SafeClockMechanism(99L, 50L);
        execute(values, mechanism);
        return mechanism.getZeroPassed();
    }

    private void execute(String[] values, SafeClockMechanism mechanism) {
        for (String value : values) {
            if (value.startsWith("L")) {
                value = value.substring(1);
                int steps = Integer.parseInt(value);

                mechanism.forward(steps);
            } else {
                value = value.substring(1);
                int steps = Integer.parseInt(value);
                mechanism.backward(steps);
            }
        }
    }
}
