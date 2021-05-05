package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class DiagonalDownLeftMovement implements MovementController{
    private final Element element;
    private final int increment;

    public DiagonalDownLeftMovement(Element element, int increment) {
        this.element = element;
        this.increment = increment;
    }

    @Override
    public Position move() {
        Position newPosition = element.getPosition().getDown(increment);
        newPosition = newPosition.getLeft(increment);

        return newPosition;
    }
}

