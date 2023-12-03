package com.stukans.advent.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private final char root;
    private final Location location;

    private final List<Integer> numbers;

    public Node(char root, Location location) {
        this(root, location, Collections.emptyList());
    }

    public Node(char root, Location location, List<Integer> numbers) {
        this.root = root;
        this.location = location;
        this.numbers = numbers;
    }

    public boolean isAsterixNode() {
        return root == '*';
    }

    public Integer sum() {
        return numbers.stream().reduce(Integer::sum).orElse(0);
    }

    public Integer pow() {
        if (numbers.size() == 2) {
            return numbers.get(0) * numbers.get(1);
        }
        return 0;
    }

    public Node find(char[][] input) {
        List<Integer> foundNumbers = new ArrayList<>();
        for (int y = location.y - 1; y <= location.y + 1; y++) {
            for (int x = location.x - 1; x <= location.x + 1; x++) {
                try {
                    char ch = input[y][x];
                    if (Character.isDigit(ch)) {
                        if (y == location.y) {
                            foundNumbers.add(evaluateFoundedDigitInCenterRow(ch, input, x, y));
                        } else {
                            String traverseLeft = traverse(input, x, y, true);
                            String traverseRight = traverse(input, x, y, false);
                            if (traverseRight.equals(traverseLeft) && traverseLeft.length() == 1 && traverseRight.length() == 1) {
                                foundNumbers.add(Character.digit(ch, 10));
                            } else {
                                if (traverseRight.length() == 1 && traverseLeft.length() > 1) {
                                    foundNumbers.add(Integer.parseInt(traverseLeft));
                                }

                                if (traverseLeft.length() == 1 && traverseRight.length() > 1) {
                                    x = x + traverseRight.length();
                                    foundNumbers.add(Integer.parseInt(traverseRight));
                                }

                                if (traverseLeft.length() > 1 && traverseRight.length() > 1) {
                                    x = x + traverseRight.length();
                                    String compose = traverseLeft + traverseRight.substring(1);
                                    foundNumbers.add(Integer.parseInt(compose));
                                }
                            }
                        }

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //done with purpose to avoid array borders handling
                }
            }
        }
        return new Node(root, location, foundNumbers);
    }

    public Integer evaluateFoundedDigitInCenterRow(char ch, char[][] input, int x, int y) {
        if (Character.isDigit(input[y][x - 1])) {
            return Integer.parseInt(traverse(input, x, y, true));
        } else if (Character.isDigit(input[y][x + 1])) {
            return Integer.parseInt(traverse(input, x, y, false));
        } else {
            return Character.digit(ch, 10);
        }
    }

    public String traverse(char[][] input, int x, int y, boolean toLeft) {
        StringBuilder builder = new StringBuilder();
        builder.append(input[y][x]);
        boolean hasMoreDigits = true;
        while (hasMoreDigits) {
            try {
                if (toLeft) {
                    x--;
                } else {
                    x++;
                }
                char caret = input[y][x];
                if (Character.isDigit(caret)) {
                    builder.append(caret);
                } else {
                    hasMoreDigits = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                hasMoreDigits = false;
            }
        }

        if (toLeft) {
            return builder.reverse().toString();
        }
        return builder.toString();
    }

}
