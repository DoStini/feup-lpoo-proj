package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class DiagonalDownRightMovement implements MovementController{
    private final MovableElement element;

    public DiagonalDownRightMovement(MovableElement element) {
        this.element = element;
    }

    @Override
    public Position move() {
        int speed = element.getSpeed();
        Position newPosition = element.getPosition().getDown(speed);
        newPosition = newPosition.getRight(speed);

        return newPosition;
    }
}

