package com.shootemup.g53.model.collider;

public class NullCollisionDetector implements CollisionDetector {
    @Override
    public boolean collide(Collider first, Collider second) {
        return false;
    }
}
