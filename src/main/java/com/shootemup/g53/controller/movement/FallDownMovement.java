package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class FallDownMovement implements MovementController {
    private final Element element;
    private final int speed;

    public FallDownMovement(Element element, int speed) {
        this.element = element;
        this.speed = speed;
    }

    @Override
    public Element move() {
        element.setPosition(element.getPosition().getDown(speed));
        return element;
    }
}
