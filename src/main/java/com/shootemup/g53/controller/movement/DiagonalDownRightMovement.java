package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class DiagonalDownRightMovement implements MovementStrategy {
    @Override
    public Position move(Position position, int speed) {
        Position newPosition = position.getDown(speed);
        newPosition = newPosition.getRight(speed);
        return newPosition;
    }

    @Override
    public void handleFailedMovement() {

    }
}

