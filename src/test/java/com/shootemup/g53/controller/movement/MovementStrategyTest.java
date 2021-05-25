package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MovementStrategyTest {
    private MovableElement element;

    @BeforeEach
    void setup() {
        element = Mockito.mock(MovableElement.class, Mockito.CALLS_REAL_METHODS);

        element.setPosition(new Position(1,1));
    }

    @Test
    void fallDown() {
        double speed = 2;

        Mockito.when(element.getSpeed()).thenReturn(speed);

        FallDownMovement fallDownMovement = new FallDownMovement();

        Position startPosition = element.getPosition();

        element.setPosition(fallDownMovement.move(startPosition, element.getSpeed()));
        Assertions.assertEquals(startPosition.getDown((int)speed), element.getPosition());

        element.setPosition(fallDownMovement.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(startPosition.getDown((int)speed * 2), element.getPosition());

        MovementStrategy clone = fallDownMovement.cloneStrategy();

        Assertions.assertTrue(clone instanceof FallDownMovement);
    }

    @Test
    void goUp() {
        double speed = 2;

        Mockito.when(element.getSpeed()).thenReturn(speed);

        MovementStrategy goUpMovement = new MoveUpwardsMovement();

        Position startPosition = element.getPosition();

        element.setPosition(goUpMovement.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(startPosition.getUp((int)speed), element.getPosition());

        element.setPosition(goUpMovement.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(startPosition.getUp((int)speed * 2), element.getPosition());

        MovementStrategy clone = goUpMovement.cloneStrategy();

        Assertions.assertTrue(clone instanceof MoveUpwardsMovement);
    }


    @Test
    void diagonalBounce() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);

        Position init = element.getPosition();
        MovementStrategy diagonalBounceMovement = new DiagonalBounceMovement(0, 10, Direction.DOWN_RIGHT, element.getPosition());

        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(4, 4), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(7, 7), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(10, 10), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(7, 13), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(4, 16), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(1, 19), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(2, 22), element.getPosition());
        element.setPosition(diagonalBounceMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(5, 25), element.getPosition());

        MovementStrategy clone = diagonalBounceMovement.cloneStrategy();

        Assertions.assertTrue(clone instanceof DiagonalBounceMovement);
        DiagonalBounceMovement bounceClone = (DiagonalBounceMovement) clone;

        Assertions.assertEquals(0, bounceClone.xLeftLimit);
        Assertions.assertEquals(10, bounceClone.xRightLimit);
        Assertions.assertEquals(Direction.DOWN_RIGHT, bounceClone.direction);
        Assertions.assertEquals(init, bounceClone.initalPosition);
    }

    @Test
    void diagonalLeft() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);

        MovementStrategy diagonalLeftMovement = new DiagonalDownLeftMovement();

        element.setPosition(diagonalLeftMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(-2, 4), element.getPosition());
        element.setPosition(diagonalLeftMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(-5, 7), element.getPosition());

        MovementStrategy clone = diagonalLeftMovement.cloneStrategy();

        Assertions.assertTrue(clone instanceof  DiagonalDownLeftMovement);
    }

    @Test
    void diagonalRight() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);

        MovementStrategy diagonalRightMovement = new DiagonalDownRightMovement();

        element.setPosition(diagonalRightMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(4, 4), element.getPosition());
        element.setPosition(diagonalRightMovement.move(element.getPosition(), speed));
        Assertions.assertEquals(new Position(7, 7), element.getPosition());

        MovementStrategy clone = diagonalRightMovement.cloneStrategy();

        Assertions.assertTrue(clone instanceof  DiagonalDownRightMovement);
    }

    @Test
    void circular() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);
        double angSpeed = 72;

        MovementStrategy circular = new CircularMovement(3, 0, angSpeed);

        MovementStrategy clone = circular.cloneStrategy();

        Assertions.assertTrue(clone instanceof CircularMovement);
        CircularMovement bounceClone = (CircularMovement) clone;

        Assertions.assertEquals(3, bounceClone.radius);
        Assertions.assertEquals(0, bounceClone.angle);
        Assertions.assertEquals(Math.toRadians(angSpeed), bounceClone.angularSpeed);

        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, 4), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-4, 3), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-4, -1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, -2), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(1, 1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, 4), element.getPosition());


    }

    @Test
    void circularReverse() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);
        double angSpeed = -72;

        MovementStrategy circular = new CircularMovement(3, 0, angSpeed);

        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, -2), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-4, -1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-4, 3), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, 4), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(1, 1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, -2), element.getPosition());
    }

    @Test
    void circularReverseAngle() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);
        double angSpeed = -72;

        MovementStrategy circular = new CircularMovement( 3, 90, angSpeed);

        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(4, -1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(3, -4), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, -4), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-2, -1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(1, 1), element.getPosition());
        element.setPosition(circular.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(4, -1), element.getPosition());
    }

    @Test
    void leftMovement() {
        MovementStrategy left = new LeftMovement();

        double speed = 2;

        Mockito.when(element.getSpeed()).thenReturn(speed);

        element.setPosition(left.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(-1, 1), element.getPosition());
        element.setPosition(left.move(element.getPosition(), element.getSpeed()));

        Assertions.assertEquals(new Position(-3, 1), element.getPosition());

        MovementStrategy clone = left.cloneStrategy();

        Assertions.assertTrue(clone instanceof LeftMovement);
    }

    @Test
    void rightMovement() {
        MovementStrategy right = new RightMovement();

        double speed = 2;

        Mockito.when(element.getSpeed()).thenReturn(speed);

        element.setPosition(right.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(3, 1), element.getPosition());
        element.setPosition(right.move(element.getPosition(), element.getSpeed()));
        Assertions.assertEquals(new Position(5, 1), element.getPosition());

        MovementStrategy clone = right.cloneStrategy();

        Assertions.assertTrue(clone instanceof  RightMovement);
    }
}
