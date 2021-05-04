package com.shootemup.g53.controller.collider;

import com.shootemup.g53.model.collider.Collider;

public interface CollisionDetectorFactory {
    CollisionDetector createFromColliders(Collider first, Collider second);
}
