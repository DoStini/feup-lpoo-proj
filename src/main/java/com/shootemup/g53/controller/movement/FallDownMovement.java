package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class FallDownMovement implements MovementController {

    @Override
    public Position move(Position position, int speed) {
        return position.getDown(speed);
    }

    @Override
    public void handleFailedMovement() {

    }
}
