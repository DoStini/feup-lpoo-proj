package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlayerControllerTest {

    private Spaceship spaceship;
    private Position position;
    private int speed = 5;
    private int fireRate = 10;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        spaceship = Mockito.mock(Spaceship.class);
        Mockito.when(spaceship.getFireRate()).thenReturn(fireRate);
        Mockito.when(spaceship.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getPosition()).thenReturn(position);
        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handleMovementUp() {
        PlayerController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getUp(speed);
    }
    @Test
    void handleMovementDown() {
        PlayerController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getDown(speed);
    }
    @Test
    void handleMovementLeft() {
        PlayerController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getLeft(speed);
    }
    @Test
    void handleMovementRight() {
        PlayerController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        controller.move(gui);
        Mockito.verify(position, Mockito.times(1))
                .getRight(speed);
    }

    @Test
    void handleMultikeyMovement() {
        PlayerController controller = new PlayerController(spaceship);
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
                .getUp(speed);
        Mockito.verify(position, Mockito.times(1))
                .getRight(speed);
        Mockito.verify(position, Mockito.times(1))
                .getDown(speed);
        Mockito.verify(position, Mockito.times(1))
                .getLeft(speed);
    }

    @Test
    void handleFire() {
    }
}
