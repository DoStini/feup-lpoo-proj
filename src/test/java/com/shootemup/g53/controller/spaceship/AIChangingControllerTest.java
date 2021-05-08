package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class AIChangingControllerTest {

    Spaceship spaceship;

    List<MovementController> movementControllers;
    private Random random;

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

        random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        movementControllers = Arrays.asList(Mockito.mock(MovementController.class));

        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handleRateNonReached() {
        SpaceshipController controller = new AIChangingController(spaceship, movementControllers, 15, random);

        for (int i = 0; i < 15; i++) {
            controller.handle(gui);
        }
        
        Mockito.verify(random, Mockito.times(1)).nextInt(Mockito.anyInt());
    }

    @Test
    void handleRateReached() {
        SpaceshipController controller = new AIChangingController(spaceship, movementControllers, 15, random);

        for (int i = 0; i < 16; i++) {
            controller.handle(gui);
        }

        Mockito.verify(random, Mockito.times(2)).nextInt(Mockito.anyInt());
    }
}
