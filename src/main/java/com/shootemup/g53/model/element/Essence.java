package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public class Essence extends MovableElement {

    private int value;
    public Essence(Position position, int value) {
        super(position, "#00cc00", 2);
        this.value = value;
    }

    public Essence() {
        this(new Position(0,0), 0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
