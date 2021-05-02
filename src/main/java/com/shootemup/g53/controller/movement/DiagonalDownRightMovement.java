package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class DiagonalDownRightMovement implements MovementController{
    private int speed;

    public DiagonalDownRightMovement(int speed) {
        this.speed = speed;
    }

    @Override
    public Element move(Element element) {
        element.setPosition(element.getPosition().getDown(speed));
        element.setPosition(element.getPosition().getRight(speed));

        return element;
    }
}

