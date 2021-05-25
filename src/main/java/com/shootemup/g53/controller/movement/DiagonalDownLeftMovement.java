package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class DiagonalDownLeftMovement extends IncrementalMovement {
    @Override
    Position moveFrame(Position position, int speed) {
        Position newPosition = position.getDown(speed);
        newPosition = newPosition.getLeft(speed);

        return newPosition;
    }

    @Override
    public void handleFailedMovement() {

    }
}

