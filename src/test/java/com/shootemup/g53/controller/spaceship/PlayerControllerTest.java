package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    private Spaceship spaceship;
    private Position position;

    @BeforeEach
    void setup() {
        position = new Position(0, 0);
        spaceship = Mockito.spy( new Spaceship(position));
        spaceship.setPosition(position);
        // Mockito.when(spaceship.getSpeed()).thenReturn(1); Example
    }

    @Test
    void handleMovementUp() {
        SpaceshipController controller = new PlayerController();
        controller.handle(spaceship, Action.W);
        Assertions.assertEquals(position.getUp(1), spaceship.getPosition());
    }
    @Test
    void handleMovementDown() {
        SpaceshipController controller = new PlayerController();
        controller.handle(spaceship, Action.S);
        Assertions.assertEquals(position.getDown(1), spaceship.getPosition());
    }
    @Test
    void handleMovementLeft() {
        SpaceshipController controller = new PlayerController();
        controller.handle(spaceship, Action.A);
        Assertions.assertEquals(position.getLeft(1), spaceship.getPosition());
    }
    @Test
    void handleMovementRight() {
        SpaceshipController controller = new PlayerController();
        controller.handle(spaceship, Action.D);
        Assertions.assertEquals(position.getRight(1), spaceship.getPosition());
    }

    @Test
    void handleFire() {
        SpaceshipController controller = new PlayerController();
        controller.handle(spaceship, Action.FIRE);
    }
}
