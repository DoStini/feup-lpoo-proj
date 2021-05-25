package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class CompositeMovementTest {
    @Test
    void compositeOneMovement() {
        MovementStrategy fallDown = new FallDownMovement();

        CompositeMovement strategy = new CompositeMovement();
        strategy.addMovement(fallDown);

        MovementStrategy clone = strategy.cloneStrategy();

        Assertions.assertTrue(clone instanceof CompositeMovement);
        CompositeMovement movement = (CompositeMovement) clone;

        Assertions.assertTrue(movement.controllers.get(0) instanceof FallDownMovement);
        Assertions.assertNotEquals(fallDown, movement.controllers.get(0));

        Assertions.assertEquals(1, strategy.controllers.size());

        Position position = new Position(0,0);

        Assertions.assertEquals(new Position(0, 4), strategy.move(position, 4));
    }

    @Test
    void compositeMovement() {
        MovementStrategy fallDown = Mockito.spy(new FallDownMovement());
        MovementStrategy diagonalLeft = Mockito.spy(new DiagonalDownLeftMovement());

        List<MovementStrategy> strategies = Arrays.asList(fallDown, diagonalLeft);

        MovementStrategy composite = new CompositeMovement(strategies);

        MovementStrategy clone = composite.cloneStrategy();

        Assertions.assertTrue(clone instanceof CompositeMovement);
        CompositeMovement movement = (CompositeMovement) clone;

        Assertions.assertTrue(movement.controllers.get(0) instanceof FallDownMovement);
        Assertions.assertTrue(movement.controllers.get(1) instanceof DiagonalDownLeftMovement);
        Assertions.assertNotEquals(fallDown, movement.controllers.get(0));
        Mockito.verify(fallDown, Mockito.times(1)).cloneStrategy();
        Assertions.assertNotEquals(diagonalLeft, movement.controllers.get(0));
        Mockito.verify(diagonalLeft, Mockito.times(1)).cloneStrategy();

        Position position = new Position(1,3);

        Assertions.assertEquals(new Position(-3+1,6+3), composite.move(position, 3));

        composite.handleFailedMovement();

        Mockito.verify(fallDown, Mockito.times(1)).handleFailedMovement();
        Mockito.verify(diagonalLeft, Mockito.times(1)).handleFailedMovement();
    }
}