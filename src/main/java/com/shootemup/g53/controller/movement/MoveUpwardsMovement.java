package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class MoveUpwardsMovement implements MovementStrategy {
    @Override
    public Position move(Position position, int speed) {
        return position.getUp(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
