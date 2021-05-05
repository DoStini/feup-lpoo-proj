package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    private Spaceship spaceship;
    private Position position;
    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        spaceship = Mockito.spy( new Spaceship(position, 1, ""));
        spaceship.setPosition(position);
        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handleMovementUp() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getUp(1);
    }
    @Test
    void handleMovementDown() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getDown(1);
    }
    @Test
    void handleMovementLeft() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getLeft(1);
    }
    @Test
    void handleMovementRight() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getRight(1);
    }

    @Test
    void handleMultikeyMovement() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        // Since position.getUp and others return a new position, we need to return the same position to test calls
        Mockito.when(position.getUp(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getDown(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getLeft(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getRight(Mockito.anyInt())).thenReturn(position);


        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getUp(1);
        Mockito.verify(position, Mockito.times(1))
                .getRight(1);
        Mockito.verify(position, Mockito.times(1))
                .getDown(1);
        Mockito.verify(position, Mockito.times(1))
                .getLeft(1);


    }

    @Test
    void handleFire() {
    }
}
