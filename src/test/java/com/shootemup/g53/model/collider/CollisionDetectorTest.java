package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollisionDetectorTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void squareCollider() {
        Collider collider = new SquareCollider(new Position(0,0), 10, 10);
        Collider collider2 = new SquareCollider(new Position(10, 10), 1,1);
        Collider collider3 = new SquareCollider(new Position(11, 11), 1,1);
        Collider collider4 = new SquareCollider(new Position(-1, -1), 0,0);
        Collider collider5 = new SquareCollider(new Position(-1, -1), 1,0);
        Collider collider6 = new SquareCollider(new Position(-1, -1), 0,1);
        Collider collider7 = new SquareCollider(new Position(-1, -1), 1,1);
        Collider collider8 = new SquareCollider(new Position(11, 0), 1,1);


        CollisionDetector scd = new SquareCollisionDetector();

        Assertions.assertTrue(scd.collide(collider, collider2));
        Assertions.assertTrue(scd.collide(collider2, collider));

        Assertions.assertFalse(scd.collide(collider, collider3));
        Assertions.assertFalse(scd.collide(collider3, collider));

        Assertions.assertFalse(scd.collide(collider, collider4));
        Assertions.assertFalse(scd.collide(collider4, collider));

        Assertions.assertFalse(scd.collide(collider, collider5));
        Assertions.assertFalse(scd.collide(collider5, collider));

        Assertions.assertFalse(scd.collide(collider, collider6));
        Assertions.assertFalse(scd.collide(collider6, collider));

        Assertions.assertTrue(scd.collide(collider, collider7));
        Assertions.assertTrue(scd.collide(collider7, collider));

        Assertions.assertFalse(scd.collide(collider, collider8));
        Assertions.assertFalse(scd.collide(collider8, collider));
    }
}