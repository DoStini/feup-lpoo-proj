package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.UnorderedPair;

import java.util.HashMap;

public class CollisionDetectorFactory {
    private static final HashMap<UnorderedPair<Class<?>>, CollisionDetector> classToDetector =
            new HashMap<>();
    private static boolean mapCreated = false;

    static private void createMap() {
        mapCreated = true;
        classToDetector.put(
                new UnorderedPair<>(SquareCollider.class, SquareCollider.class), new SquareCollisionDetector()
        );
    }

    static CollisionDetector createFromColliders(Collider first, Collider second) {
        if(!mapCreated) createMap();

        return classToDetector.getOrDefault(
                new UnorderedPair<Class<?>>(first.getClass(), second.getClass()), new NullCollisionDetector()
        );
    }
}
