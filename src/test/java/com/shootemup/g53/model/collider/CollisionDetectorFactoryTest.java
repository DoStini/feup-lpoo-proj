package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.CollisionDetectorFactory;
import com.shootemup.g53.controller.collider.CollisionDetectorHashFactory;
import com.shootemup.g53.controller.collider.NullCollisionDetector;
import com.shootemup.g53.controller.collider.SquareCollisionDetector;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CollisionDetectorFactoryTest {
    @Test
    void hashFactory() {
        Collider collider = new SquareCollider(new Position(0,0),0,0);
        Collider collider1 = new SquareCollider(new Position(0,0 ),0,0);
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
    }
}
