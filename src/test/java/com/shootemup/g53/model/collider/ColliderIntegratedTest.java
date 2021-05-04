package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ColliderIntegratedTest {
    @Test
    void squareCollision() {
        Collider collider = new SquareCollider(new Position(0,0 ), 0, 0);
        Collider collider1 = new SquareCollider(new Position(0, 0), 0, 0);
        Collider collider2 = new SquareCollider(new Position(1, 1), 0, 0);
        Collider fakeCollider = Mockito.mock(Collider.class, Mockito.CALLS_REAL_METHODS);

        Assertions.assertTrue(collider.collide(collider1));
        Assertions.assertTrue(collider1.collide(collider));
        Assertions.assertFalse(collider2.collide(collider));
        Assertions.assertFalse(collider.collide(collider2));
        Assertions.assertFalse(collider2.collide(collider1));
        Assertions.assertFalse(collider.collide(fakeCollider));
    }

    @Test
    void circleCollision() {
        Collider collider = new CircleCollider(new Position(0,0),1);
        Collider collider1 = new CircleCollider(new Position(0,0),1);
        Collider collider2 = new CircleCollider(new Position(2,2),1);

        Assertions.assertTrue(collider.collide(collider1));
        Assertions.assertTrue(collider1.collide(collider));
        Assertions.assertFalse(collider1.collide(collider2));
        Assertions.assertFalse(collider1.collide(collider2));
    }
}
