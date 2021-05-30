package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.controller.observer.LifeController;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerStatesTest {
    private Player player;
    private Spaceship spaceship;
    private Position position;
    private String color = "#aaaaaa";
    private MovingBulletStrategy firingController;
    private BulletPoolController bulletPoolController;
    private NormalState normalState;
    private InvulnerableState invulnerableState;
    MovementStrategy strategy;
    private double speed = 5;
    private PowerupController powerupController;
    private LifeController lifeController;
    private int fireRate = 10;
    private PlayerController playerController;


    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        strategy = Mockito.mock(MovementStrategy.class);
        gui = Mockito.mock(Gui.class);
        spaceship = Mockito.mock(Spaceship.class);
        playerController = Mockito.mock(PlayerController.class);
        lifeController = Mockito.mock(LifeController.class);

        Mockito.when(playerController.getBulletPoolController()).thenReturn(bulletPoolController);
        Mockito.when(playerController.getPlayer()).thenReturn(player);
        Mockito.when(playerController.getNormalPlayerColor()).thenReturn(color);
        Mockito.when(playerController.getLifeController()).thenReturn(lifeController);

        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(position);
        powerupController = Mockito.mock(PowerupController.class);

        firingController = Mockito.mock(MovingBulletStrategy.class);
        Mockito.when(playerController.getFiringStrategy()).thenReturn(firingController);

        Mockito.when(player.getSpeed()).thenReturn(speed);
        Mockito.when(player.getHealth()).thenReturn(10);
        Mockito.when(player.getPosition()).thenReturn(position);
        Mockito.when(player.getColor()).thenReturn(color);

        Mockito.when(position.getUp(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getDown(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getLeft(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getRight(Mockito.anyInt())).thenReturn(position);

        Mockito.when(playerController.move()).thenReturn(position);
        gui = Mockito.mock(Gui.class);
        Mockito.when(playerController.getGui()).thenReturn(gui);
    }

    @Test
    void handleFireNormalState() {
        long frame = 0;
        NormalState normalState = new NormalState(playerController);
        Mockito.when(playerController.getCurrState()).thenReturn(normalState);
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);

        playerController.getCurrState().fire(gui,bulletPoolController,frame);

        Mockito.verify(firingController, Mockito.times(1))
                .fire(player, player.getPosition(), bulletPoolController, ColorOperation.invertColor(color),
                        ColliderCategory.PLAYER_BULLET, frame);
    }

    @Test
    void handleNormalState() {
        long frame = 0;
        NormalState normalState = new NormalState(playerController);
        Mockito.verify(playerController, Mockito.times(1)).setColor(color);
    }

    @Test
    void handleNormalStateSpaceshipInteraction() {
        long frame = 0;
        NormalState normalState = new NormalState(playerController);
        Mockito.when(playerController.getCurrState()).thenReturn(normalState);
        normalState.handleSpaceship(spaceship);
        Mockito.verify(lifeController, Mockito.times(1)).setLife(Mockito.anyInt());
        Mockito.verify(lifeController, Mockito.times(1)).notifyObservers();
        Mockito.verify(playerController, Mockito.times(1)).changeState(Mockito.any(InvulnerableState.class));
    }

    @Test
    void handleNormalStateHandler() {
        long frame = 0;
        NormalState normalState = new NormalState(playerController);
        Mockito.when(playerController.getCurrState()).thenReturn(normalState);
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);
        normalState.handle(frame);
        Mockito.verify(playerController, Mockito.times(1)).setPosition(position);
        Mockito.verify(firingController, Mockito.times(1)).fire(player,player.getPosition().getUp(player.getHeight()),bulletPoolController,ColorOperation.invertColor(color),ColliderCategory.PLAYER_BULLET, frame);
        Mockito.verify(playerController, Mockito.times(1)).usePowerups();
    }

    @Test
    void handleInvulnerableState() {
        long frame = 0;
        InvulnerableState invulnerableState = new InvulnerableState(playerController, 50);
        Mockito.verify(playerController, Mockito.times(1)).setColor("#FFFFFF");

        for(int i = 0; i < 50; i++){
            invulnerableState.handle(frame);
        }
        Mockito.verify(playerController,Mockito.times(1)).changeState(Mockito.any(NormalState.class));
        Mockito.verify(playerController,Mockito.times(50)).setPosition(position);
        Mockito.verify(playerController,Mockito.times(50)).move();
    }
}
