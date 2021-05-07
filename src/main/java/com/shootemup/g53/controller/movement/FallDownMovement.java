package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class FallDownMovement implements MovementController {
    private final MovableElement element;

    public FallDownMovement(MovableElement element) {
        this.element = element;
    }

    @Override
    public Position move() {
        return element.getPosition().getDown(element.getSpeed());
    }
}
