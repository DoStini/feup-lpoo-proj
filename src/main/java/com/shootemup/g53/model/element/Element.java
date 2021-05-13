package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

abstract public class Element {
    protected Position position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Element other = (Element) o;
        return other.position.equals(this.position)
                && other.getColor().equals(this.getColor());
    }

    public void setColor(String color) {
        this.color = color;
    }
}
