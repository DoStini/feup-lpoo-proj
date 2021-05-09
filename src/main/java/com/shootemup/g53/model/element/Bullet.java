package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Bullet extends MovableElement {

    private int size;

    public Bullet(Position position, String color,int speed, int size) {
        super(position, color, speed);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
