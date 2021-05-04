package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.CollisionDetectorFactory;
import com.shootemup.g53.controller.collider.CollisionDetectorHashFactory;
import com.shootemup.g53.model.util.Position;

public class CircleCollider implements Collider{
    private final Position center;
    private final double radius;
    private final CollisionDetectorFactory factory;

    public CircleCollider(Position center , double radius, CollisionDetectorFactory factory) {
        this.center = center;
        this.radius = radius;
        this.factory = factory;
    }

    public CircleCollider(Position center , double radius) {
        this.center = center;
        this.radius = radius;
        this.factory = new CollisionDetectorHashFactory();
    }

    @Override
    public boolean collide(Collider other) {
        return factory.createFromColliders(this, other).collide(this, other);
    }

    public double getRadius() {
        return radius;
    }

    public Position getCenter() {
        return center;
    }
}
