package com.stukans.advent._2024.day2;

import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RedNosedReports extends Puzzle {


    @Override
    public long solution1(File file) {
        Integer[][] numbers = asNumbers(file);

        long answer = 0;
        for (int i = 0; i < numbers.length; i++) {

            String collect = "[" + Arrays.stream(numbers[i]).map(String::valueOf).collect(Collectors.joining(",")) + "]";
            String distance = "[" + getDistance(numbers[i]) + "]";

            Result result = isCorrect(numbers[i]);
            if (result.isCorrect()) {
                answer++;
                System.out.println(i + " - " + collect + ": Correct - " + distance + " - dir:" + result.direction() + " - change: " + result.changeDirection());
            } else {
                System.out.println(i + " - " + collect + ": Not-Correct - " + distance + " - dir:" + result.direction() + " - change: " + result.changeDirection());
            }
        }

        return answer;
    }

    @Override
    public long solution2(File file) {
        Integer[][] numbers = asNumbers(file);

        long answer = 0;
        for (int i = 0; i < numbers.length; i++) {
            String collect = "[" + Arrays.stream(numbers[i]).map(String::valueOf).collect(Collectors.joining(",")) + "]";
            String distance = "[" + getDistance(numbers[i]) + "]";
            Result initialResult = isCorrect(numbers[i]);
            if (initialResult.isCorrect()) {
                answer++;
                System.out.println(i + " - " + collect + ": Correct - " + distance + " - dir:" + initialResult.direction() + " - change: " + initialResult.changeDirection());
            } else {
                boolean foundCorrect = false;
                for (int j = 0; j < numbers[i].length; j++) {
                    Integer buffer = numbers[i][j];
                    numbers[i][j] = -1;

                    String subCollect = "[" + Arrays.stream(numbers[i]).map(String::valueOf).collect(Collectors.joining(",")) + "]";
                    String subDistance = "[" + getDistance(numbers[i]) + "]";

                    Result subResult = isCorrect(numbers[i]);
                    if (subResult.isCorrect()) {
                        foundCorrect = true;
                        System.out.println("####### " + subCollect + ": Correct - " + subDistance + " - dir:" + subResult.direction() + " - change: " + subResult.changeDirection());
                        break;
                    } else {
                        System.out.println("####### " + subCollect + ": Not-Correct - " + subDistance + " - dir:" + subResult.direction() + " - change: " + subResult.changeDirection());
                    }

                    numbers[i][j] = buffer;
                }

                if (foundCorrect) {
                    answer++;
                    System.out.println(i + " - " + collect + ": Correct - " + distance + " - dir:" + initialResult.direction() + " - change: " + initialResult.changeDirection());
                } else {
                    System.out.println(i + " - " + collect + ": Not-Correct - " + distance + " - dir:" + initialResult.direction() + " - change: " + initialResult.changeDirection());
                }
            }
        }

        return answer;
    }

    private String getDistance(Integer[] numbers) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbers.length - 1; i++) {
            if ((i == 0 || i == numbers.length - 1) && numbers[i] == -1) {
                continue;
            }

            if (i == numbers.length - 2 && numbers[i + 1] == -1) {
                break;
            }

            if (numbers[i] == -1) {
                continue;
            }

            Integer i1 = numbers[i] == -1 ? numbers[i - 1] : numbers[i];
            Integer i2 = numbers[i + 1] == -1 ? numbers[i + 2] : numbers[i + 1];

            result.append(Math.abs(i1 - i2)).append(",");
        }
        return result.toString();
    }

    Result isCorrect(Integer[] numbers) {
        Direction direction = Direction.NA;
        boolean directionIdentified = false;
        boolean changeDirection = false;
        boolean correct = true;
        boolean notCorrectBecauseOfAbs = false;
        for (int i = 0; i < numbers.length - 1; i++) {
            if ((i == 0 || i == numbers.length - 1) && numbers[i] == -1) {
                continue;
            }

            if (i == numbers.length - 2 && numbers[i + 1] == -1) {
                break;
            }

            Integer i1 = numbers[i] == -1 ? numbers[i - 1] : numbers[i];
            Integer i2 = numbers[i + 1] == -1 ? numbers[i + 2] : numbers[i + 1];

            if (i1.equals(i2)) {
                correct = false;
                break;
            }

            if (!directionIdentified) {
                direction = i1 > i2 ? Direction.DECREASING : Direction.INCREASING;
                directionIdentified = true;
            }

            int abs = Math.abs(i1 - i2);
            if (abs > 3) {
                correct = false;
                notCorrectBecauseOfAbs = true;
                break;
            }

            if ((i1 > i2 && direction == Direction.INCREASING) || (i1 < i2 && direction == Direction.DECREASING)) {
                correct = false;
                changeDirection = true;
                break;
            }
        }
        return new Result(correct, notCorrectBecauseOfAbs, direction, changeDirection);
    }

    record Result(Boolean isCorrect, Boolean becauseOfAbs, Direction direction, Boolean changeDirection) {
    }

    enum Direction {
        INCREASING, DECREASING, NA
    }

}
