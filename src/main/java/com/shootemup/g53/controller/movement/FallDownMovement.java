package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class FallDownMovement extends FrameMovement {
    @Override
    Position moveFrame(Position position, int speed) {
        return position.getDown(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
