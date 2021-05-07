package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AIShootingControllerTest {

    Spaceship spaceship;
    MovementController movementController;
    Position position;
    Gui gui;
    private int speed = 5;
    private int fireRate = 5;


    @BeforeEach
    void setup() {
        position = new Position(0, 0);
        spaceship = Mockito.mock(Spaceship.class);
        Mockito.when(spaceship.getFireRate()).thenReturn(fireRate);
        Mockito.when(spaceship.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getPosition()).thenReturn(position);

        movementController = Mockito.mock(MovementController.class);
        Mockito.when(movementController.move()).thenReturn(new Position(5, 5));

        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handle() {
        AIShootingController controller = new AIShootingController(spaceship, movementController);
        assertEquals(new Position(5, 5), controller.handle(gui));
        Mockito.verify(movementController, Mockito.times(1)).move();
    }
}
