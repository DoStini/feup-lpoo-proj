package com.shootemup.g53.controller.collider;

import com.shootemup.g53.model.collider.Collider;

public interface CollisionDetector {
    boolean collide(Collider first, Collider second);
}
