package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertEquals(element.getPosition(), startPosition.getDown(speed));

        fallDownMovement.move(element);
        Assertions.assertEquals(element.getPosition(), startPosition.getDown(speed*2));
    }
}