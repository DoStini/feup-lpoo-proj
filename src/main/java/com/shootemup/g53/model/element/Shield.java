package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shield shield = (Shield) o;
        return this.getColor().equals(shield.getColor()) && this.getPosition().equals(shield.getPosition())
                && width == shield.width && strength == shield.strength;
    }

}
