package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public abstract class MovableElement extends Element {

    private double speed;
    public MovableElement(Position position, String color, double speed) {
        super(position, color);
        this.speed = speed;

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
