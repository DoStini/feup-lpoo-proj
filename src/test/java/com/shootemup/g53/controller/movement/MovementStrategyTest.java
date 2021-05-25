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
    }


    @Test
    void diagonalBounce() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);

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
    }

    @Test
    void circular() {
        double speed = 3;
        Mockito.when(element.getSpeed()).thenReturn(speed);
        double angSpeed = 72;

        MovementStrategy circular = new CircularMovement(3, 0, angSpeed);

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
    }
}
