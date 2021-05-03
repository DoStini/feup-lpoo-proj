package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class DiagonalDownLeftMovement implements MovementController{
    private final Element element;
    private int speed;

    public DiagonalDownLeftMovement(Element element, int speed) {
        this.element = element;
        this.speed = speed;
    }

    @Override
    public Element move() {
        element.setPosition(element.getPosition().getDown(speed));
        element.setPosition(element.getPosition().getLeft(speed));

        return element;
    }
}

