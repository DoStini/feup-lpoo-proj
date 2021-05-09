package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;

public class Asteroid extends MovableElement {

    int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Asteroid(Position position, int radius, MovementStrategy movementStrategy) {
        super(position, "#555555", 5, movementStrategy);
        this.radius = radius;
    }
}