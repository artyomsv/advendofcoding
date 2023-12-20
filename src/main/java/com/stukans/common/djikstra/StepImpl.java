package com.stukans.common.djikstra;

import java.util.Optional;

public class StepImpl implements Step {

    private final Coordinates coordinates;
    private final Direction direction;

    private final Step previous;

    public static Step of(Coordinates coordinates, Direction direction) {
        return of(coordinates, direction, null);
    }

    public static Step of(Coordinates coordinates, Direction direction, Step previous) {
        return new StepImpl(coordinates, direction, previous);
    }

    private StepImpl(Coordinates coordinates, Direction direction, Step previous) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.previous = previous;
    }

    @Override
    public <T> Optional<Step> forward(T[][] matrix) {

        Coordinates forward = coordinates.forward(direction);
        if (forward.inBounds(matrix)) {
            Direction nextDirection = direction.forward();
            return Optional.of(StepImpl.of(forward, nextDirection, this));
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<Step> forward(Direction direction, T[][] matrix) {
        Coordinates forward = coordinates.forward(direction.forward());
        if (forward.inBounds(matrix)) {
            return Optional.of(StepImpl.of(forward, direction, this));
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<Step> left(T[][] matrix) {

        Coordinates left = coordinates.left(direction);
        if (left.inBounds(matrix)) {
            Direction nextDirection = direction.left();
            return Optional.of(StepImpl.of(left, nextDirection, this));
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<Step> left(Direction direction, T[][] matrix) {
        Coordinates left = coordinates.left(direction);
        if (left.inBounds(matrix)) {
            return Optional.of(StepImpl.of(left, direction, this));
        }
        return Optional.empty();
    }


    @Override
    public <T> Optional<Step> right(T[][] matrix) {
        Coordinates right = coordinates.right(direction);
        if (right.inBounds(matrix)) {
            Direction nextDirection = direction.right();
            return Optional.of(StepImpl.of(right, nextDirection, this));
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<Step> right(Direction direction, T[][] matrix) {
        Coordinates right = coordinates.right(direction);
        if (right.inBounds(matrix)) {
            return Optional.of(StepImpl.of(right, direction, this));
        }
        return Optional.empty();
    }


    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public Optional<Step> getPrevious() {
        return Optional.ofNullable(previous);
    }
}
