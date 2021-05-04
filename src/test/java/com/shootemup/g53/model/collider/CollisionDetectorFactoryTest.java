package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectorFactoryTest {
    @Test
    void squareColliders() {
        Collider collider = new SquareCollider(new Position(0,0),0,0);
        Collider collider1 = new SquareCollider(new Position(0,0 ),0,0);
        Collider fakeCollider = Mockito.mock(Collider.class);

        Assertions.assertEquals(
                SquareCollisionDetector.class,
                CollisionDetectorFactory.createFromColliders(collider, collider1).getClass()
        );

        Assertions.assertEquals(
                NullCollisionDetector.class,
                CollisionDetectorFactory.createFromColliders(collider, fakeCollider).getClass()
        );
    }
}