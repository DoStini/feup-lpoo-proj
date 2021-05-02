package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class FallDownMovement implements MovementController{
    @Override
    public void move(Element element) {
        element.setPosition(element.getPosition().getDown(1));
    }
}
