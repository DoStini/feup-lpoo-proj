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

        MovementController fallDownMovement = new FallDownMovement(speed);

        Position startPosition = element.getPosition();

        fallDownMovement.move(element);
        Assertions.assertEquals(startPosition.getDown(speed), element.getPosition());

        fallDownMovement.move(element);
        Assertions.assertEquals(startPosition.getDown(speed * 2), element.getPosition());
    }

    @Test
    void diagonalBounce() {
        int speed = 3;

        MovementController diagonalBounceMovement = new DiagonalBounceMovement(
                speed, 0, 10, DiagonalBounceMovement.Direction.DOWN_RIGHT
        );

        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(4, 4), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(7, 7), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(10, 10), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(7, 13), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(4, 16), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(1, 19), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(2, 22), element.getPosition());
        diagonalBounceMovement.move(element);
        Assertions.assertEquals(new Position(5, 25), element.getPosition());
    }

    @Test
    void diagonalLeft() {
        int speed = 3;

        MovementController diagonalLeftMovement = new DiagonalDownLeftMovement(speed);

        diagonalLeftMovement.move(element);
        Assertions.assertEquals(new Position(-2, 4), element.getPosition());
        diagonalLeftMovement.move(element);
        Assertions.assertEquals(new Position(-5, 7), element.getPosition());
    }

    @Test
    void diagonalRight() {
        int speed = 3;

        MovementController diagonalRightMovement = new DiagonalDownRightMovement(speed);

        diagonalRightMovement.move(element);
        Assertions.assertEquals(new Position(4, 4), element.getPosition());
        diagonalRightMovement.move(element);
        Assertions.assertEquals(new Position(7, 7), element.getPosition());
    }
}
