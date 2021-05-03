package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class FallDownMovement implements MovementController {
    private final Element element;
    private final int increment;

    public FallDownMovement(Element element, int increment) {
        this.element = element;
        this.increment = increment;
    }

    @Override
    public Element move() {
        element.setPosition(element.getPosition().getDown(increment));
        return element;
    }
}
