package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.CollisionDetectorFactory;
import com.shootemup.g53.controller.collider.CollisionDetectorHashFactory;
import com.shootemup.g53.controller.collider.SquareCollisionDetector;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ColliderTest {
    @Test
    void collision() {
        CollisionDetectorFactory detectorFactory = Mockito.mock(CollisionDetectorFactory.class);
        Mockito.when(detectorFactory.createFromColliders(Mockito.any(), Mockito.any())).thenReturn(
                new SquareCollisionDetector()
        );

        Collider collider = new SquareCollider(new Position(0,0),0,0, detectorFactory);
        Collider collider1 = new SquareCollider(new Position(0,0 ),0,0, detectorFactory);

        Assertions.assertTrue(collider.collide(collider1));
        Assertions.assertTrue(collider1.collide(collider));

        Mockito.verify(detectorFactory, Mockito.times(2)).createFromColliders(Mockito.any(), Mockito.any());
    }
}
