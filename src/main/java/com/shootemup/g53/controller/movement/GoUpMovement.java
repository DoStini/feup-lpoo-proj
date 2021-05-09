package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class GoUpMovement implements MovementController {
    private final MovableElement element;

    public GoUpMovement(MovableElement element) {
        this.element = element;
    }

    @Override
    public Position move() {
        return element.getPosition().getUp(element.getSpeed());
    }
}
