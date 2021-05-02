package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class FallDownMovement implements MovementController{
    private int speed;
    public FallDownMovement(int speed) {
        this.speed = speed;
    }

    @Override
    public void move(Element element) {
        element.setPosition(element.getPosition().getDown(speed));
    }
}
