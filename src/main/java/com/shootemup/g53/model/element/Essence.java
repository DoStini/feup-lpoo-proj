package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Essence extends MovableElement {

    private final int value;
    public Essence(Position position, int value) {
        super(position, "#00cc00", 2);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
