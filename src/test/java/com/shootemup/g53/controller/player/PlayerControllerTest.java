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
    private int speed = 5;
    private int fireRate = 10;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);

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
        PlayerController controller = new PlayerController(player,gui,bulletPoolController, firingController);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getUp(speed);
    }
    @Test
    void handleMovementDown() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getDown(speed);
    }
    @Test
    void handleMovementLeft() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getLeft(speed);
    }
    @Test
    void handleMovementRight() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        assertEquals(position, controller.move(gui));

        Mockito.verify(position, Mockito.times(1))
                .getRight(speed);
    }

    @Test
    void handleMultikeyMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
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
        long frame = 0;
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);

        for (int i = 0; i < fireRate; i++) {
            frame++;
            controller.fire(gui, bulletPoolController,frame);
        }

        Mockito.verify(firingController, Mockito.times(1))
                .fire(player, player.getPosition(), bulletPoolController, "#ff0000",  ColliderCategory.PLAYER_BULLET,frame);

    }

}
