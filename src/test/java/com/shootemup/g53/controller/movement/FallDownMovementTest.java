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
    void Setup() {
        element = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);

        element.setPosition(new Position(1,1));
    }


    @Test
    void FallDown() {
        MovementController fallDownMovement = new FallDownMovement();

        fallDownMovement.move(element);
        Assertions.assertEquals(element.getPosition(), new Position(1,2));

        fallDownMovement.move(element);
        Assertions.assertEquals(element.getPosition(), new Position(1,3));
    }
}