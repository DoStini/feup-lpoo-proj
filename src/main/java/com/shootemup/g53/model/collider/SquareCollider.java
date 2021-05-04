package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.CollisionDetectorFactory;
import com.shootemup.g53.controller.collider.CollisionDetectorHashFactory;
import com.shootemup.g53.model.util.Position;

public class SquareCollider implements Collider{

    private final Position topLeft;
    private final int width;
    private final int height;
    private final CollisionDetectorFactory factory;

    public SquareCollider(Position topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.factory = new CollisionDetectorHashFactory();
    }

    public SquareCollider(Position topLeft, int width, int height, CollisionDetectorFactory factory) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.factory = factory;
    }

    @Override
    public boolean collide(Collider other) {
        return factory.createFromColliders(this, other).collide(this, other);
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
