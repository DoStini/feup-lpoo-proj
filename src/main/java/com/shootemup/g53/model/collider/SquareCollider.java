package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;

public class SquareCollider implements Collider{

    private final Position topLeft;
    private final int width;
    private final int height;

    public SquareCollider(Position topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean collide(Collider other) {
        return CollisionDetectorFactory.createFromColliders(this, other).collide(this, other);
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
