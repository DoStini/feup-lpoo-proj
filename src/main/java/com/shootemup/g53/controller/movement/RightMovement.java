package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class RightMovement extends IncrementalMovement {

    @Override
    Position moveFrame(Position position, int speed) {
        return position.getRight(speed);
    }

    @Override
    public void handleFailedMovement() {

    }

    @Override
    public MovementStrategy cloneStrategy() {
        return new RightMovement();
    }
}
