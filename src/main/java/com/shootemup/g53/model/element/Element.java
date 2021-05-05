package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

abstract public class Element {
    private Position position;
    private String color;

    public Element(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
