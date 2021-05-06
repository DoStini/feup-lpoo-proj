package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Bullet extends MovableElement {

    private int size;

    public Bullet(Position position, String color, int size) {
        super(position, color, 2);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
