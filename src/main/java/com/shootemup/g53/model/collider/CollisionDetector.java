package com.shootemup.g53.model.collider;

public interface CollisionDetector<T extends Collider, S extends Collider> {
    boolean collide(T first, S second);
}
