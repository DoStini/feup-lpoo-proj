package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Asteroid extends MovableElement {

    int radius;

    public Asteroid() {
        this(new Position(0, 0), 0);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asteroid)) return false;
        Asteroid other = (Asteroid) o;
        return super.equals(o)
                && other.radius == this.radius;
    }
}
