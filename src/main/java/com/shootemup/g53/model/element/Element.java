package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

abstract public class Element {
    private Position position;

    public Element(Position positio) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
