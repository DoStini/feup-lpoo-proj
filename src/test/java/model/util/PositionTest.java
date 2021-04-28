package model.util;

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
}