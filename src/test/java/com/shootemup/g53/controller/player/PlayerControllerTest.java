package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.ColorOperation;
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
    private String color = "#aaaaaa";
    private MovingBulletStrategy firingController;
    private BulletPoolController bulletPoolController;
    MovementStrategy strategy;
    private double speed = 5;
    private PowerupController powerupController;
    private int fireRate = 10;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        strategy = Mockito.mock(MovementStrategy.class);
        gui = Mockito.mock(Gui.class);

        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(position);

        powerupController = Mockito.mock(PowerupController.class);

        firingController = Mockito.mock(MovingBulletStrategy.class);

        Mockito.when(player.getSpeed()).thenReturn(speed);
        Mockito.when(player.getPosition()).thenReturn(position);
        Mockito.when(player.getColor()).thenReturn(color);

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
        controller.setUpStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }
    @Test
    void handleMovementDown() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.setDownStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }
    @Test
    void handleMovementLeft() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.setLeftStrategy(strategy);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }
    @Test
    void handleMovementRight() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.setRightStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        assertEquals(position, controller.move(gui));
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }

    @Test
    void handleMultikeyMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
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

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(4)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }

    @Test
    void fireRateNotReached() {
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);
        long frame = 0;

        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);

        for (int i = 0; i < fireRate; i++) {
            frame++;
            controller.fire(gui, bulletPoolController,frame);
            Mockito.verify(firingController, Mockito.times(1))
                    .fire(player, player.getPosition(), bulletPoolController, ColorOperation.invertColor(color),
                            ColliderCategory.PLAYER_BULLET, frame);
        }

    }

    @Test
    void powerUps() {
        Mockito.when(gui.isActionActive(Action.POWER_1)).thenReturn(true);
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        long frame = 0;
        controller.handle(frame);

        Mockito.when(gui.isActionActive(Action.POWER_1)).thenReturn(false);
        controller.handle(frame);

        Mockito.verify(powerupController, Mockito.times(1)).spawnShield(player);

    }

}
