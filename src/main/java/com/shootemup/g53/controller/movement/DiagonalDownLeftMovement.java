package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class DiagonalDownLeftMovement implements MovementController{
    @Override
    public Position move(Position position, int speed) {
        Position newPosition = position.getDown(speed);
        newPosition = newPosition.getLeft(speed);

        return newPosition;
    }

    @Override
    public void handleFailedMovement() {

    }
}

