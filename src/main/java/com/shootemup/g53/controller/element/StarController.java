package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;

public class StarController extends MovableElementController implements ElementInterface{
    private Star star;

    public StarController(Star star, MovementStrategy movementStrategy) {
        super(star, movementStrategy);
        this.star = star;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    @Override
    public void handle(long frame) {
        Position newPosition = move();
        star.setPosition(newPosition);
    }
}
