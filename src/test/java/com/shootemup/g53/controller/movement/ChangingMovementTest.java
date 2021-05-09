package com.shootemup.g53.controller.movement;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangingMovementTest {

    Spaceship spaceship;

    List<MovementStrategy> movementStrategies;
    private BulletPoolController bulletPoolController;
    private Random random;

    Position position;
    Gui gui;
    private int speed = 5;
    private int fireRate = 5;

    @BeforeEach
    void setup() {
        position = new Position(0, 0);
        spaceship = Mockito.mock(Spaceship.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);

        Mockito.when(spaceship.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getPosition()).thenReturn(position);

        random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        movementStrategies = Arrays.asList(Mockito.mock(MovementStrategy.class));

        gui = Mockito.mock(Gui.class);
    }

    /*

    @Test
    void movement() {

        MovementStrategy controller = new AIChangingController(spaceship, movementControllers, 15, random);
        MovementController movementController = movementControllers.get(0);
        Mockito.when(movementController.move()).thenReturn(position);

        Assertions.assertEquals(position, controller.handle(gui, bulletPoolController));

        Mockito.verify(random, Mockito.times(1)).nextInt(Mockito.anyInt());
    }
    */

    @Test
    void handleRateNonReached() {
        MovementStrategy controller = new ChangingMovement(15, movementStrategies,random,0);

        for (int i = 0; i < 15; i++) {
            controller.move(spaceship.getPosition(), spaceship.getSpeed());
        }
        
        Mockito.verify(random, Mockito.times(1)).nextInt(Mockito.anyInt());
    }

    @Test
    void handleRateReached() {
        MovementStrategy controller = new ChangingMovement(15, movementStrategies,  random,0);

        for (int i = 0; i < 16; i++) {
            controller.move(spaceship.getPosition(), spaceship.getSpeed());
        }

        Mockito.verify(random, Mockito.times(2)).nextInt(Mockito.anyInt());
    }


}
