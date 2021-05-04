package com.shootemup.g53.model.collider;

import com.shootemup.g53.controller.collider.CircleCollisionDetector;
import com.shootemup.g53.controller.collider.CollisionDetector;
import com.shootemup.g53.controller.collider.NullCollisionDetector;
import com.shootemup.g53.controller.collider.SquareCollisionDetector;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class CollisionDetectorTest {
    private Collider collider;

    @BeforeEach
    void setUp() {
        collider = new SquareCollider(new Position(0,0), 10, 10);
    }

    @Test
    void nullCollider() {
        Collider fakeCollider = Mockito.mock(Collider.class, Mockito.CALLS_REAL_METHODS);
        Collider realCollider = new SquareCollider(new Position(0,0),0,0);

        CollisionDetector ncd = new NullCollisionDetector();

        Assertions.assertFalse(ncd.collide(collider, fakeCollider));
        Assertions.assertFalse(ncd.collide(fakeCollider, collider));
        Assertions.assertFalse(ncd.collide(collider, realCollider));
        Assertions.assertFalse(ncd.collide(realCollider, collider));
    }

    @Test
    void squareCollider() {
        Collider collider2 = new SquareCollider(new Position(10, 10), 1,1);
        Collider collider3 = new SquareCollider(new Position(11, 11), 1,1);
        Collider collider4 = new SquareCollider(new Position(-1, -1), 0,0);
        Collider collider5 = new SquareCollider(new Position(-1, -1), 1,0);
        Collider collider6 = new SquareCollider(new Position(-1, -1), 0,1);
        Collider collider7 = new SquareCollider(new Position(-1, -1), 1,1);
        Collider collider8 = new SquareCollider(new Position(11, 0), 1,1);
        Collider collider9 = new SquareCollider(new Position(0,0),0,0);
        Collider collider10 = new SquareCollider(new Position(0,0),0,0);
        Collider collider11 = Mockito.mock(Collider.class, Mockito.CALLS_REAL_METHODS);


        CollisionDetector scd = new SquareCollisionDetector();

        Assertions.assertTrue(scd.collide(collider, collider));

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

        Assertions.assertTrue(scd.collide(collider9, collider10));
        Assertions.assertTrue(scd.collide(collider10, collider9));

        Assertions.assertFalse(scd.collide(collider, collider11));
        Assertions.assertFalse(scd.collide(collider11, collider));
    }

    @Test
    void circleCollision() {
        Collider collider = new CircleCollider(new Position(0,0), 0.95);
        Collider collider1 = new CircleCollider(new Position(0, 0),0.5);
        Collider collider2 = new CircleCollider(new Position(2,2),0.95);
        Collider collider3 = new CircleCollider(new Position(2,2),2.2);
        Collider fakeCollider = new SquareCollider(new Position(0,0), 0, 0);

        CollisionDetector ccd = new CircleCollisionDetector();

        Assertions.assertTrue(ccd.collide(collider, collider));

        Assertions.assertTrue(ccd.collide(collider1, collider));
        Assertions.assertTrue(ccd.collide(collider, collider1));

        Assertions.assertFalse(ccd.collide(collider, collider2));
        Assertions.assertFalse(ccd.collide(collider2, collider));

        Assertions.assertTrue(ccd.collide(collider, collider3));
        Assertions.assertTrue(ccd.collide(collider3, collider));

        Assertions.assertFalse(ccd.collide(collider, fakeCollider));
        Assertions.assertFalse(ccd.collide(fakeCollider, collider));
    }
}
