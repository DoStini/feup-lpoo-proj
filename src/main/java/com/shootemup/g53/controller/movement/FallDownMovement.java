package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class FallDownMovement extends IncrementalMovement {
    @Override
    Position moveFrame(Position position, int speed) {
        return position.getDown(speed);
    }

    @Override
    public void handleFailedMovement() {

    }

    @Override
    public MovementStrategy cloneStrategy() {
        return new FallDownMovement();
    }
}
