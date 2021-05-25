package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulletTest {

    private Bullet bullet;

    @BeforeEach
    void setup() {
        bullet = new Bullet(new Position(0, 0), "", 0,0, 4);
    }

    @Test
    void activate() {
        bullet.activate();
        Assertions.assertTrue(bullet.isActive());
    }

    @Test
    void deactivate() {
        bullet.deactivate();
        Assertions.assertFalse(bullet.isActive());
    }

    @Test
    void testClone() {
        Bullet clone = (Bullet) bullet.clone();
        Assertions.assertEquals(bullet.position, clone.position);
        Assertions.assertEquals(bullet.getSize(), clone.getSize());
        Assertions.assertEquals(bullet.getColor(), clone.getColor());
    }

    @Test
    void init() {
        bullet.init(5,5,"color", 5, 0);
        Assertions.assertEquals(bullet.getPosition(), new Position(5, 5));
        Assertions.assertEquals(bullet.getColor(), "color");
        Assertions.assertEquals(bullet.getSize(), 5);
    }
}