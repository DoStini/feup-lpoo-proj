package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.*;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CollisionDetectorFactoryTest {
    @Test
    void hashFactory() {
        Collider collider = new SquareCollider(new Position(0,0),0,0);
        Collider collider1 = new SquareCollider(new Position(0,0 ),0,0);
        Collider circleCollider = new CircleCollider(new Position(0,0), 0);
        Collider circleCollider2 = new CircleCollider(new Position(0,0), 0);
        Collider fakeCollider = Mockito.mock(Collider.class);

        CollisionDetectorFactory detectorFactory = new CollisionDetectorHashFactory();

        Assertions.assertEquals(
                SquareCollisionDetector.class,
                detectorFactory.createFromColliders(collider, collider1).getClass()
        );

        Assertions.assertEquals(
                NullCollisionDetector.class,
                detectorFactory.createFromColliders(collider, fakeCollider).getClass()
        );

        Assertions.assertEquals(
                CircleCollisionDetector.class,
                detectorFactory.createFromColliders(circleCollider, circleCollider2).getClass()
        );

        Assertions.assertEquals(
                CircleSquareCollisionDetector.class,
                detectorFactory.createFromColliders(circleCollider, collider).getClass()
        );

        Assertions.assertEquals(
                CircleSquareCollisionDetector.class,
                detectorFactory.createFromColliders(collider, circleCollider).getClass()
        );
    }
}
