package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncrementalMovementTest {

    @Test
    void move() {
        IncrementalMovement movement = new FallDownMovement();

        Assertions.assertEquals(0, movement.currentDistance);

        Assertions.assertEquals(new Position(0, 3), movement.move(new Position(0,0), 3));
        Assertions.assertEquals(0, movement.currentDistance, 0.001);

        Assertions.assertEquals(new Position(1,1), movement.move(new Position(1,1), 0.5));
        Assertions.assertEquals(0.5, movement.currentDistance, 0.001);

        MovementStrategy clone = movement.cloneStrategy();

        Assertions.assertTrue(clone instanceof  IncrementalMovement);
        IncrementalMovement cloneInc = (IncrementalMovement)clone;
        Assertions.assertEquals(0, cloneInc.currentDistance);

        Assertions.assertEquals(new Position(1,2), movement.move(new Position(1,1), 0.5));
        Assertions.assertEquals(0, movement.currentDistance, 0.001);

    }
}