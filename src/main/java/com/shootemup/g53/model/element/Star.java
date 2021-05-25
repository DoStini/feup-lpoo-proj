package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Star extends MovableElement {
    int distance;

    @Override
    public PoolableObject clone() {
        return new Star(new Position(position.getX(), position.getY()), distance);
    }

    public int getDistance() { return distance; }

    public Star(Position position, int distance) {
        super(position, "#555555", 0);
        this.distance = Math.max(distance, 0);
    }
}
