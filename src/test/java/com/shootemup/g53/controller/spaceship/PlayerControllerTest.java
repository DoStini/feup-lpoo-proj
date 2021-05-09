package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerControllerTest {

    private Spaceship spaceship;
    private Position position;
    private BulletPoolController bulletPoolController;
    private int speed = 5;
    private int fireRate = 10;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        spaceship = Mockito.mock(Spaceship.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);

        Mockito.when(spaceship.getFireRate()).thenReturn(fireRate);
        Mockito.when(spaceship.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getPosition()).thenReturn(position);

        Mockito.when(position.getUp(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getDown(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getLeft(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getRight(Mockito.anyInt())).thenReturn(position);

        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handleMovementUp() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        assertEquals(position, controller.handle(gui, bulletPoolController));

        Mockito.verify(position, Mockito.times(1))
                .getUp(speed);
    }
    @Test
    void handleMovementDown() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        assertEquals(position, controller.handle(gui, bulletPoolController));

        Mockito.verify(position, Mockito.times(1))
                .getDown(speed);
    }
    @Test
    void handleMovementLeft() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        assertEquals(position, controller.handle(gui, bulletPoolController));

        Mockito.verify(position, Mockito.times(1))
                .getLeft(speed);
    }
    @Test
    void handleMovementRight() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        assertEquals(position, controller.handle(gui, bulletPoolController));

        Mockito.verify(position, Mockito.times(1))
                .getRight(speed);
    }

    @Test
    void handleMultikeyMovement() {
        SpaceshipController controller = new PlayerController(spaceship);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        // Since position.getUp and others return a new position, we need to return the same position to test calls

        assertEquals(position, controller.handle(gui, bulletPoolController));

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
    void fireRateNotReached() {
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);

        SpaceshipController controller = new PlayerController(spaceship);

        for (int i = 0; i < fireRate; i++) {
            controller.handle(gui, bulletPoolController);
        }

        Mockito.verify(bulletPoolController, Mockito.times(1))
                .addPlayerBullet(position.getX(), position.getY(), "#ff0000", 3);

    }
    @Test
    void fireRateReached() {
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);

        SpaceshipController controller = new PlayerController(spaceship);

        for (int i = 0; i < fireRate + 1; i++) {
            controller.handle(gui, bulletPoolController);
        }

        Mockito.verify(bulletPoolController, Mockito.times(2))
                .addPlayerBullet(position.getX(), position.getY(), "#ff0000", 3);

    }
}
