package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public abstract class MovableElement extends Element {

    private int speed;

    public MovableElement(Position position, String color, int speed) {
        super(position, color);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
