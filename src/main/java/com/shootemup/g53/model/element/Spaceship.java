package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Spaceship extends MovableElement {
    private int height;
    private int fireRate;

    public Spaceship(Position position, int height, String color, int speed, int fireRate) {
        super(position, color, speed);
        this.height = height;
        this.fireRate = fireRate;
    }

    public int getFireRate() {
        return fireRate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
