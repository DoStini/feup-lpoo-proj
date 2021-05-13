package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;

public class Coin extends MovableElement {

    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    public Coin() {
        this(new Position(0,0), 0, null);
    }

    public Coin(Position position, int radius, MovementStrategy movementStrategy) {
        super(position, "#cc9900", 5, movementStrategy);
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coin)) return false;
        Coin other = (Coin) o;
        return super.equals(o)
                && other.radius == this.radius;
    }
}
