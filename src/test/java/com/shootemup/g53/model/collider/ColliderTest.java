package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.CollisionDetectorHashFactory;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

class ColliderTest {
    @Spy
    CollisionDetectorHashFactory factory;

    @Test
    void collision() {
        Collider collider = new SquareCollider(new Position(0,0),0,0);
        Collider collider1 = new SquareCollider(new Position(0,0 ),0,0);

        Assertions.assertTrue(collider.collide(collider1));
        Assertions.assertTrue(collider1.collide(collider));

        // i don't know how to test this at all

    }
}