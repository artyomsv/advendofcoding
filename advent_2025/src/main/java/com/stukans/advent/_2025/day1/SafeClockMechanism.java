package com.stukans.advent._2025.day1;

import com.stukans.advent._2025.NavigatableNode;
import com.stukans.advent._2025.Node;

class SafeClockMechanism {

    private Node<Long> value;
    private int zeroCount = 0;
    private int zeroPassed = 0;

    public SafeClockMechanism(Long total, Long start) {
        NavigatableNode<Long> current = new NavigatableNode<>(0L);
        NavigatableNode<Long> previous = current;
        for (long i = 1; i <= total; i++) {
            NavigatableNode<Long> next = new NavigatableNode<>(i);
            previous.setNext(next);
            next.setPrevious(previous);
            previous = next;
            if (i == start) {
                value = next;
            }
        }
        previous.setNext(current);
        current.setPrevious(previous);

    }

    public Node<Long> getCurrent() {
        return value;
    }

    public Long getValue() {
        return getCurrent().getValue();
    }

    public SafeClockMechanism forward(int i) {
        for (int j = 0; j < i; j++) {
            value = value.getNext();
            if (getValue() == 0L) {
                zeroPassed++;
                if (j == i - 1) {
                    zeroCount++;
                }
                System.out.printf("P:%d C:%d i:%d j:%d\n", zeroPassed, zeroCount, i, j);
            }
        }
        return this;
    }

    public SafeClockMechanism backward(int i) {
        for (int j = 0; j < i; j++) {
            value = value.getPrev();
            if (getValue() == 0L) {
                zeroPassed++;
                if (j == i - 1) {
                    zeroCount++;
                }
                System.out.printf("P:%d C:%d i:%d j:%d\n", zeroPassed, zeroCount, i, j);
            }
        }
        return this;
    }

    public int getZeroCount() {
        return zeroCount;
    }

    public int getZeroPassed() {
        return zeroPassed;
    }

    private Node<Long> getNext() {
        return getCurrent().getNext();
    }

    private Node<Long> getPrev() {
        return getCurrent().getPrev();
    }
}
