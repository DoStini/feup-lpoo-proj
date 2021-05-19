package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

abstract public class Element implements PoolableObject {
    protected Position position;
    private String color;
    private boolean active = true;

    @Override
    public boolean isActive() { return active; }

    @Override
    public void activate() { active = true; }

    @Override
    public void deactivate() { active = false; }

    @Override
    public PoolableObject clone() {
        return null;
    }

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
