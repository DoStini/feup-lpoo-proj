package com.shootemup.g53.controller.collider;

import com.shootemup.g53.model.collider.Collider;
import com.shootemup.g53.model.collider.SquareCollider;
import com.shootemup.g53.model.util.UnorderedPair;

import java.util.HashMap;

public class CollisionDetectorHashFactory implements CollisionDetectorFactory{
    private final HashMap<UnorderedPair<Class<?>>, CollisionDetector> classToDetector =
            new HashMap<>();

    public CollisionDetectorHashFactory() {
        createMap();
    }

    private void createMap() {
        classToDetector.put(
                new UnorderedPair<>(SquareCollider.class, SquareCollider.class), new SquareCollisionDetector()
        );
    }

    public CollisionDetector createFromColliders(Collider first, Collider second) {
        return classToDetector.getOrDefault(
                new UnorderedPair<Class<?>>(first.getClass(), second.getClass()), new NullCollisionDetector()
        );
    }
}
