package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class LeftMovement implements MovementStrategy {

    @Override
    public Position move(Position position, int speed) {
        return position.getLeft(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
