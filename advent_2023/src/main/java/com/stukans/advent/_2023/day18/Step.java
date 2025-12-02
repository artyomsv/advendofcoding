package com.stukans.advent._2023.day18;

class Step {
    final Direction direction;
    final Integer length;

    final String rgb;

    public Step(String input) {
        String[] split = input.strip().split(" ");
        direction = Direction.valueOf(split[0].strip());
        length = Integer.parseInt(split[1].strip());
        rgb = split[2].strip();
    }

    public Step(Direction direction, Integer length, String rgb) {
        this.direction = direction;
        this.length = length;
        this.rgb = rgb;
    }



}
