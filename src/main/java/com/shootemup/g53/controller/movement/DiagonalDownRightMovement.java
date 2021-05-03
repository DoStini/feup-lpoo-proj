package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class DiagonalDownRightMovement implements MovementController{
    private final Element element;
    private final int increment;

    public DiagonalDownRightMovement(Element element, int increment) {
        this.element = element;
        this.increment = increment;
    }

    @Override
    public Position move() {
        Position newPosition = element.getPosition().getDown(increment);
        newPosition = newPosition.getRight(increment);

        return newPosition;
    }
}

