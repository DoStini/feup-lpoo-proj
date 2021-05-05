package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MovementControllerTest {
    private Element element;

    @BeforeEach
    void setup() {
        element = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);

        element.setPosition(new Position(1,1));
    }


    @Test
    void fallDown() {
        int speed = 2;

        MovementController fallDownMovement = new FallDownMovement(element, speed);

        Position startPosition = element.getPosition();

        element.setPosition(fallDownMovement.move());
        Assertions.assertEquals(startPosition.getDown(speed), element.getPosition());

        element.setPosition(fallDownMovement.move());
        Assertions.assertEquals(startPosition.getDown(speed * 2), element.getPosition());
    }

    @Test
    void diagonalBounce() {
        int speed = 3;

        MovementController diagonalBounceMovement = new DiagonalBounceMovement(
                element, speed, 0, 10, DiagonalBounceMovement.Direction.DOWN_RIGHT
        );

        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(4, 4), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(7, 7), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(10, 10), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(7, 13), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(4, 16), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(1, 19), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(2, 22), element.getPosition());
        element.setPosition(diagonalBounceMovement.move());
        Assertions.assertEquals(new Position(5, 25), element.getPosition());
    }

    @Test
    void diagonalLeft() {
        int speed = 3;

        MovementController diagonalLeftMovement = new DiagonalDownLeftMovement(element, speed);

        element.setPosition(diagonalLeftMovement.move());
        Assertions.assertEquals(new Position(-2, 4), element.getPosition());
        element.setPosition(diagonalLeftMovement.move());
        Assertions.assertEquals(new Position(-5, 7), element.getPosition());
    }

    @Test
    void diagonalRight() {
        int speed = 3;

        MovementController diagonalRightMovement = new DiagonalDownRightMovement(element, speed);

        element.setPosition(diagonalRightMovement.move());
        Assertions.assertEquals(new Position(4, 4), element.getPosition());
        element.setPosition(diagonalRightMovement.move());
        Assertions.assertEquals(new Position(7, 7), element.getPosition());
    }

    @Test
    void circular() {
        double angSpeed = 72;

        MovementController circular = new CircularMovement(element, 3, 0, angSpeed);

        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, 4), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-4, 3), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-4, -1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, -2), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(1, 1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, 4), element.getPosition());
    }

    @Test
    void circularReverse() {
        double angSpeed = -72;

        MovementController circular = new CircularMovement(element, 3, 0, angSpeed);

        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, -2), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-4, -1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-4, 3), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, 4), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(1, 1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, -2), element.getPosition());
    }

    @Test
    void circularReverseAngle() {
        double angSpeed = -72;

        MovementController circular = new CircularMovement(element, 3, 90, angSpeed);

        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(4, -1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(3, -4), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-1, -4), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(-2, -1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(1, 1), element.getPosition());
        element.setPosition(circular.move());
        Assertions.assertEquals(new Position(4, -1), element.getPosition());
    }
}
