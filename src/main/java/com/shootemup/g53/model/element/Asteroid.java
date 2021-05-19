package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Asteroid extends MovableElement {

    int radius;

    @Override
    public PoolableObject clone() {
        return new Asteroid(getPosition(), radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Asteroid(Position position, int radius) {
        super(position, "#555555", 5);
        this.radius = radius;
    }
}
