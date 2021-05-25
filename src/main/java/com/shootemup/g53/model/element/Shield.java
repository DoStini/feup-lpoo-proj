package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Shield extends Element {

    private final int width;
    private int strength;

    public Shield(Position position, String color, int strength, int width) {
        super(position, color);
        this.strength = strength;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
