package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BoundingBoxTest {
    @Test
    void squareCollider() {
        BoundingBox collider = new BoundingBox(new Position(0,0), 10, 10);
        BoundingBox  collider2 = new BoundingBox(new Position(10, 10), 1,1);
        BoundingBox  collider3 = new BoundingBox(new Position(11, 11), 1,1);
        BoundingBox  collider4 = new BoundingBox(new Position(-1, -1), 0,0);
        BoundingBox  collider5 = new BoundingBox(new Position(-1, -1), 1,0);
        BoundingBox  collider6 = new BoundingBox(new Position(-1, -1), 0,1);
        BoundingBox  collider7 = new BoundingBox(new Position(-1, -1), 1,1);
        BoundingBox  collider8 = new BoundingBox(new Position(11, 0), 1,1);
        BoundingBox  collider9 = new BoundingBox(new Position(0,0),0,0);
        BoundingBox  collider10 = new BoundingBox(new Position(0,0),0,0);

        Assertions.assertTrue(collider.collides(collider));

        Assertions.assertTrue(collider.collides(collider2));
        Assertions.assertTrue(collider2.collides(collider));

        Assertions.assertFalse(collider.collides(collider3));
        Assertions.assertFalse(collider3.collides(collider));

        Assertions.assertFalse(collider.collides(collider4));
        Assertions.assertFalse(collider4.collides(collider));

        Assertions.assertFalse(collider.collides(collider5));
        Assertions.assertFalse(collider5.collides(collider));

        Assertions.assertFalse(collider.collides(collider6));
        Assertions.assertFalse(collider6.collides(collider));

        Assertions.assertTrue(collider.collides(collider7));
        Assertions.assertTrue(collider7.collides(collider));

        Assertions.assertFalse(collider.collides(collider8));
        Assertions.assertFalse(collider8.collides(collider));

        Assertions.assertTrue(collider9.collides(collider10));
        Assertions.assertTrue(collider10.collides(collider9));
    }

    @Test
    void squareColliderWithOffset() {
        BoundingBox collider = new BoundingBox(new Position(0,0), 10, 10);
        BoundingBox  collider2 = new BoundingBox(new Position(10, 10), 1,1);
        BoundingBox  collider3 = new BoundingBox(new Position(11, 11), 1,1);
        BoundingBox  collider4 = new BoundingBox(new Position(-1, -1), 0,0);

        Assertions.assertTrue(collider.collides(collider, new Position(0,0), new Position(0,0)));

        Assertions.assertTrue(collider.collides(collider2, new Position(2,2), new Position(2,2)));
        Assertions.assertTrue(collider2.collides(collider, new Position(2,2), new Position(2,2)));

        Assertions.assertFalse(collider.collides(collider3, new Position(2,0), new Position(2,0)));
        Assertions.assertFalse(collider3.collides(collider, new Position(0,2), new Position(0,2)));

        Assertions.assertTrue(collider.collides(collider3, new Position(1,1), new Position(0,0)));
        Assertions.assertTrue(collider3.collides(collider, new Position(0,0), new Position(1,1)));

        Assertions.assertTrue(collider.collides(collider3, new Position(0,0), new Position(-1,-1)));
        Assertions.assertTrue(collider3.collides(collider, new Position(-1,-1), new Position(0,0)));

        Assertions.assertFalse(collider.collides(collider4, new Position(0,0), new Position(0,0)));
        Assertions.assertFalse(collider4.collides(collider, new Position(0,0), new Position(0,0)));

        Assertions.assertTrue(collider.collides(collider4, new Position(0,0), new Position(1,1)));
        Assertions.assertTrue(collider4.collides(collider, new Position(1,1), new Position(0,0)));
    }
}
