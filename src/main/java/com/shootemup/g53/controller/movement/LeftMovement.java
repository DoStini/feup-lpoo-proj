package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class LeftMovement extends IncrementalMovement {
    @Override
    Position moveFrame(Position position, int speed) {
        return position.getLeft(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
