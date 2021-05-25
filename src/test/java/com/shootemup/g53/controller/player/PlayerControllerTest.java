package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerControllerTest {

    private Player player;
    private Position position;
    private MovingBulletStrategy firingController;
    private BulletPoolController bulletPoolController;
    private PowerupController powerupController;
    private int speed = 5;
    private int fireRate = 10;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        powerupController = Mockito.mock(PowerupController.class);

        firingController = Mockito.mock(MovingBulletStrategy.class);
        Mockito.when(player.getSpeed()).thenReturn(speed);
        Mockito.when(player.getPosition()).thenReturn(position);


        Mockito.when(position.getUp(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getDown(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getLeft(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getRight(Mockito.anyInt())).thenReturn(position);

        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handleMovementUp() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController, powerupController,
                firingController);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getUp(speed);
    }
    @Test
    void handleMovementDown() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getDown(speed);
    }
    @Test
    void handleMovementLeft() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getLeft(speed);
    }
    @Test
    void handleMovementRight() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getRight(speed);
    }

    @Test
    void handleMultikeyMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        // Since position.getUp and others return a new position, we need to return the same position to test calls

        assertEquals(position, controller.move(gui));

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

        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);

        for (int i = 0; i < fireRate; i++) {
            controller.fire(gui, bulletPoolController);
        }

        Mockito.verify(firingController, Mockito.times(fireRate))
                .fire(player, player.getPosition(), bulletPoolController, "#ff0000",  ColliderCategory.PLAYER_BULLET);

    }

    @Test
    void powerUps() {
        Mockito.when(gui.isActionActive(Action.POWER_1)).thenReturn(true);
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);

        controller.handle();

        Mockito.when(gui.isActionActive(Action.POWER_1)).thenReturn(false);
        controller.handle();

        Mockito.verify(powerupController, Mockito.times(1)).spawnShield(player);


    }

}
