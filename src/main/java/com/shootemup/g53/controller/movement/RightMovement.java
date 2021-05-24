package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class RightMovement implements MovementStrategy {

    @Override
    public Position move(Position position, int speed) {
        return position.getRight(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
