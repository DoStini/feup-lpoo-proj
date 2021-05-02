package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class DiagonalDownLeftMovement implements MovementController{
    private int speed;

    public DiagonalDownLeftMovement(int speed) {
        this.speed = speed;
    }

    @Override
    public Element move(Element element) {
        element.setPosition(element.getPosition().getDown(speed));
        element.setPosition(element.getPosition().getLeft(speed));

        return element;
    }
}

