package com.shootemup.g53.model.util;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PositionTest {

    static Position pos;

    @BeforeAll
    static void setupPos() {
        pos = new Position(10, 10);
    }

    @Test
    void getRight() {
        Assertions.assertEquals(pos.getRight(1).getX(), 11);
        Assertions.assertEquals(pos.getRight(2).getX(), 12);
    }

    @Test
    void getLeft() {
        Assertions.assertEquals(pos.getLeft(1).getX(), 9);
        Assertions.assertEquals(pos.getLeft(2).getX(), 8);
    }

    @Test
    void getUp() {
        Assertions.assertEquals(pos.getUp(1).getY(), 9);
        Assertions.assertEquals(pos.getUp(2).getY(), 8);
    }

    @Test
    void getDown() {
        Assertions.assertEquals(pos.getDown(1).getY(), 11);
        Assertions.assertEquals(pos.getDown(2).getY(), 12);
    }

    @Test
    void getSquaredDistance() {
        Position pos2 = new Position(15, 10);

        Assertions.assertEquals(25, pos.getSquaredDistance(pos2), 0.001);
        Assertions.assertEquals(25, pos2.getSquaredDistance(pos), 0.001);
        Assertions.assertEquals(0, pos.getSquaredDistance(pos), 0.001);

        Position pos3 = new Position(10, 15);

        Assertions.assertEquals(25, pos.getSquaredDistance(pos3), 0.001);
        Assertions.assertEquals(25, pos3.getSquaredDistance(pos), 0.001);

        Assertions.assertEquals(50, pos2.getSquaredDistance(pos3),0.001);
        Assertions.assertEquals(50, pos3.getSquaredDistance(pos2),0.001);
    }
}
