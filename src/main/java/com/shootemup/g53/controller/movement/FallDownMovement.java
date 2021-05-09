package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class FallDownMovement implements MovementStrategy {

    @Override
    public Position move(Position position, int speed) {
        return position.getDown(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
