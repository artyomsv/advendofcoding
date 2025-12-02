package com.stukans.advent._2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SafeClockMechanismTest {

    @Test
    void name() {
        SafeClockMechanism mechanism = new SafeClockMechanism(99L, 50L);
        mechanism
                .backward(68)
                .backward(30)
                .forward(48)
                .getValue();

        Assertions.assertEquals(1, mechanism.getZeroCount());
    }
}