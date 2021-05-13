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

    public Asteroid() {
        this(new Position(0,0), 0, null);
    }

    public Asteroid(Position position, int radius, MovementStrategy movementStrategy) {
        super(position, "#555555", 5, movementStrategy);
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asteroid)) return false;
        Asteroid other = (Asteroid) o;
        return super.equals(o)
                && other.radius == this.radius;
    }
}
