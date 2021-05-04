package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Spaceship extends MovableElement{
    private int height;
    public Spaceship(Position position, int height, String color) {
        super(position, color);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
