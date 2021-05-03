package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class DiagonalDownRightMovement implements MovementController{
    private final Element element;
    private final int increment;

    public DiagonalDownRightMovement(Element element, int increment) {
        this.element = element;
        this.increment = increment;
    }

    @Override
    public Element move() {
        element.setPosition(element.getPosition().getDown(increment));
        element.setPosition(element.getPosition().getRight(increment));

        return element;
    }
}

