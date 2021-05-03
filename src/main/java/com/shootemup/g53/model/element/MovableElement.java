package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public abstract class MovableElement extends Element{

    public MovableElement(Position position, String color) {
        super(position, color);
    }

    public Position moveUp(int offset) {
        return this.getPosition().getUp(offset);

    }

    public Position moveDown(int offset) {
        return this.getPosition().getDown(offset);
    }

    public Position moveLeft(int offset) {
        return this.getPosition().getLeft(offset);
    }

    public Position moveRight(int offset) {
        return this.getPosition().getRight(offset);
    }

}
