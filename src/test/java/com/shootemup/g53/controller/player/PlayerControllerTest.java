package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerControllerTest {

    private Player player;
    private Position position;
    private MovingBulletStrategy firingController;
    private BulletPoolController bulletPoolController;
    MovementStrategy strategy;
    private double speed = 5;
    private int fireRate = 10;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        strategy = Mockito.mock(MovementStrategy.class);

        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(position);


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
        controller.setUpStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Mockito.verify(strategy, Mockito.times(1)).move(position, speed);
    }
    @Test
    void handleMovementDown() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        controller.setDownStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();


        Mockito.verify(strategy, Mockito.times(1)).move(position, speed);
    }
    @Test
    void handleMovementLeft() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        controller.setLeftStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Mockito.verify(strategy, Mockito.times(1)).move(position, speed);
    }
    @Test
    void handleMovementRight() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        controller.setRightStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Mockito.verify(strategy, Mockito.times(1)).move(position, speed);
    }

    @Test
    void handleMultikeyMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);
        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);
        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);
        controller.setRightStrategy(strategy);
        controller.setLeftStrategy(strategy);
        controller.setDownStrategy(strategy);
        controller.setUpStrategy(strategy);

        // Since position.getUp and others return a new position, we need to return the same position to test calls

        assertEquals(position, controller.move(gui));

        Mockito.verify(strategy, Mockito.times(4)).move(position, speed);
    }

    @Test
    void fireRateNotReached() {
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);

        PlayerController controller = new PlayerController(player,gui,bulletPoolController,firingController);

        for (int i = 0; i < fireRate; i++) {
            controller.fire(gui, bulletPoolController);
        }

        Mockito.verify(firingController, Mockito.times(fireRate))
                .fire(player, player.getPosition(), bulletPoolController, "#ff0000",  ColliderCategory.PLAYER_BULLET);

    }

}
