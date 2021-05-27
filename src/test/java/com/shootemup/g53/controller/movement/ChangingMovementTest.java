package com.shootemup.g53.controller.movement;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
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
    private double speed = 5;
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


    @Test
    void movement() {
        MovementStrategy controller = Mockito.spy(new ChangingMovement(15, movementStrategies));
        MovementStrategy movementController = movementStrategies.get(0);
        Mockito.when(movementController.move(spaceship.getPosition(), spaceship.getSpeed())).thenReturn(position);

        MovementStrategy clone = controller.cloneStrategy();

        Assertions.assertTrue(clone instanceof ChangingMovement);
        ChangingMovement movement = (ChangingMovement) clone;

        Assertions.assertEquals(15, ((ChangingMovement)controller).changeRate);

        Assertions.assertNotSame(movementController, movement.controllers.get(0));

        ChangingMovement movement1 = new ChangingMovement(16, Collections.singletonList(controller));
        MovementStrategy strategy = movement1.cloneStrategy();

        Assertions.assertTrue(strategy instanceof ChangingMovement);
        ChangingMovement changingMovement = (ChangingMovement) strategy;

        Assertions.assertTrue(changingMovement.controllers.get(0) instanceof ChangingMovement);
        Assertions.assertNotSame(controller, changingMovement.controllers.get(0));
        Mockito.verify(controller, Mockito.times(2)).cloneStrategy();

        Assertions.assertNotSame(movementController, ((ChangingMovement) changingMovement.controllers.get(0)).controllers.get(0));
        Mockito.verify(movementController, Mockito.times(2)).cloneStrategy();
    }


    @Test
    void handleRateNonReached() {
        MovementStrategy controller = new ChangingMovement(15, movementStrategies,random,0);
        MovementStrategy movementController = movementStrategies.get(0);
        Mockito.when(movementController.move(spaceship.getPosition(), spaceship.getSpeed())).thenReturn(position);

        for (int i = 0; i < 15; i++) {
            controller.move(spaceship.getPosition(), spaceship.getSpeed());
        }
        Mockito.verify(movementController, Mockito.times(15)).move(spaceship.getPosition(), spaceship.getSpeed());

        Assertions.assertEquals(position, movementController.move(spaceship.getPosition(), spaceship.getSpeed()));
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
