package com.stukans.common.djikstra;

import java.util.Optional;

public interface Step {

    <T> Optional<Step> forward(T[][] matrix);

    <T> Optional<Step> forward(Direction direction, T[][] matrix);

    <T> Optional<Step> left(T[][] matrix);

    <T> Optional<Step> left(Direction direction, T[][] matrix);

    <T> Optional<Step> right(T[][] matrix);

    <T> Optional<Step> right(Direction direction, T[][] matrix);

    Coordinates getCoordinates();

    Optional<Step> getPrevious();
}
