package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.controller.observer.EssenceController;
import com.shootemup.g53.controller.observer.LifeController;
import com.shootemup.g53.controller.observer.ScoreController;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.ColorOperation;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerControllerTest {

    private Player player;
    private Player player2;
    private Position position;
    private Position position2;
    private String color = "#aaaaaa";
    private MovingBulletStrategy firingController;
    private BulletPoolController bulletPoolController;
    private NormalState normalState;
    MovementStrategy strategy;
    MovementStrategy strategy2;
    private Bullet bullet;
    private double speed = 5;
    private PowerupController powerupController;
    private LifeController lifeController;
    private EssenceController essenceController;
    private ScoreController scoreController;
    private int fireRate = 10;
    private Essence essence;
    private Coin coin;

    Gui gui;

    @BeforeEach
    void setup() {
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        strategy = Mockito.mock(MovementStrategy.class);
        gui = Mockito.mock(Gui.class);
        player2 = Mockito.mock(Player.class);
        bullet = Mockito.mock(Bullet.class);
        coin = Mockito.mock(Coin.class);
        position2 = Mockito.mock(Position.class);
        strategy2 = Mockito.mock(MovementStrategy.class);
        Mockito.when(bullet.getDamage()).thenReturn(5);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(position);
        powerupController = Mockito.mock(PowerupController.class);
        lifeController = Mockito.mock(LifeController.class);
        firingController = Mockito.mock(MovingBulletStrategy.class);
        essenceController = Mockito.mock(EssenceController.class);
        scoreController = Mockito.mock(ScoreController.class);
        essence = Mockito.mock(Essence.class);
        Mockito.when(essence.getValue()).thenReturn(2);

        Mockito.when(player.getSpeed()).thenReturn(speed);
        Mockito.when(player.getPosition()).thenReturn(position);
        Mockito.when(player.getColor()).thenReturn(color);

        Mockito.when(player2.getSpeed()).thenReturn(speed);
        Mockito.when(player2.getPosition()).thenReturn(position2);
        Mockito.when(player2.getColor()).thenReturn(color);

        Mockito.when(position.getUp(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getDown(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getLeft(Mockito.anyInt())).thenReturn(position);
        Mockito.when(position.getRight(Mockito.anyInt())).thenReturn(position);

        Mockito.when(position2.getUp(Mockito.anyInt())).thenReturn(new Position(55,55));
        Mockito.when(position2.getDown(Mockito.anyInt())).thenReturn(new Position(55,55));
        Mockito.when(position2.getLeft(Mockito.anyInt())).thenReturn(new Position(55,55));
        Mockito.when(position2.getRight(Mockito.anyInt())).thenReturn(new Position(55,55));

        Mockito.when(strategy2.move(Mockito.any(), Mockito.anyDouble())).thenReturn(new Position(55,55));
        gui = Mockito.mock(Gui.class);
        Mockito.when(gui.getHeight()).thenReturn(50);
        Mockito.when(gui.getWidth()).thenReturn(50);
    }

    @Test
    void handleMovementUp() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController, powerupController,
                firingController);
        controller.setUpStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        assertEquals(position, controller.move());
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));

        PlayerController controller2 = new PlayerController(player2,gui,bulletPoolController, powerupController,
                firingController);
        controller2.setUpStrategy(strategy2);

        assertEquals(position2, controller2.move());

        positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy2, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));

    }
    @Test
    void handleMovementDown() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.setDownStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);

        assertEquals(position, controller.move());
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));

        PlayerController controller2 = new PlayerController(player2,gui,bulletPoolController, powerupController,
                firingController);
        controller2.setDownStrategy(strategy2);

        assertEquals(position2, controller2.move());

        positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy2, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }
    @Test
    void handleMovementLeft() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.setLeftStrategy(strategy);
        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);

        assertEquals(position, controller.move());
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));

        PlayerController controller2 = new PlayerController(player2,gui,bulletPoolController, powerupController,
                firingController);
        controller2.setLeftStrategy(strategy2);

        assertEquals(position2, controller2.move());

        positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy2, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
    }
    @Test
    void handleMovementRight() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.setRightStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);

        assertEquals(position, controller.move());
        double speed = player.getSpeed();

        Position positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));

        PlayerController controller2 = new PlayerController(player2,gui,bulletPoolController, powerupController,
                firingController);
        controller2.setRightStrategy(strategy2);

        assertEquals(position2, controller2.move());

        positionSent = new Position(player.getPosition().getX(), player.getPosition().getY());

        Mockito.verify(strategy2, Mockito.times(1)).move(Mockito.eq(positionSent), Mockito.eq(speed));
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

        assertEquals(position, controller.move());

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
            controller.getCurrState().fire(gui, bulletPoolController,frame);
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

        Mockito.verify(powerupController, Mockito.times(1)).spawnShield(player);

        Mockito.when(gui.isActionActive(Action.POWER_1)).thenReturn(false);
        controller.handle(frame);

        Mockito.verify(powerupController, Mockito.times(1)).spawnShield(player);

        Mockito.when(gui.isActionActive(Action.POWER_2)).thenReturn(true);
        controller.handle(frame);

        Mockito.verify(powerupController, Mockito.times(1)).healthBoost(player);

        Mockito.when(gui.isActionActive(Action.POWER_2)).thenReturn(false);
        controller.handle(frame);

        Mockito.verify(powerupController, Mockito.times(1)).healthBoost(player);

    }

    @Test
    void testHandleBullet() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        long frame = 0;
        controller.setLifeController(lifeController);
        controller.handleBullet(bullet);
        Mockito.verify(player, Mockito.times(2)).setHealth(player.getHealth() - bullet.getDamage());
        Mockito.verify(lifeController, Mockito.times(1)).setLife(player.getHealth());
        Mockito.verify(lifeController, Mockito.times(1)).notifyObservers();
    }

    @Test
    void testHandleEssence() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        long frame = 0;
        controller.setEssenceController(essenceController);
        controller.handleEssence(essence);
        Mockito.verify(player, Mockito.times(1)).addEssence(essence.getValue());
        Mockito.verify(essenceController, Mockito.times(1)).setAmount(essence.getValue());
        Mockito.verify(essenceController, Mockito.times(1)).notifyObservers();
    }

    @Test
    void testHandleCoin() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        long frame = 0;
        controller.setScoreController(scoreController);
        controller.handleCoin(coin);
        Mockito.verify(scoreController,Mockito.times(1)).notifyObservers();
    }

    @Test
    void testInsideBounds() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Position position = new Position(55,55);
        assertEquals(controller.insideBounds(position),false);
        position = new Position(5,5);
        assertEquals(controller.insideBounds(position),true);
        position = null;
        assertEquals(controller.insideBounds(position),false);
        position = new Position(-14,5);
        assertEquals(controller.insideBounds(position),false);
        position = new Position(14,-5);
        assertEquals(controller.insideBounds(position),false);
        position = new Position(0,0);
        assertEquals(controller.insideBounds(position),false);
        position = new Position(40,49);
        assertEquals(controller.insideBounds(position),false);
    }


    @Test
    void testLeftMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Position basePosition = new Position(5,5);
        Mockito.when(player.getPosition()).thenReturn(basePosition);
        controller.setLeftStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);
        Position failedPosition = new Position(0,25);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(basePosition, controller.move());

        Mockito.when(gui.isActionActive(Action.A)).thenReturn(true);
        failedPosition = new Position(1,25);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(failedPosition, controller.move());
    }

    @Test
    void testRightMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Position basePosition = new Position(5,5);
        Mockito.when(player.getPosition()).thenReturn(basePosition);
        controller.setRightStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);
        Position failedPosition = new Position(40,25);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        Mockito.when(gui.isActionActive(Action.D)).thenReturn(true);
        failedPosition = new Position(39,25);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(failedPosition, controller.move());
    }

    @Test
    void testDownMovement() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Position basePosition = new Position(5,5);
        Mockito.when(player.getPosition()).thenReturn(basePosition);
        controller.setDownStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);
        Position failedPosition = new Position(25,50);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(basePosition, controller.move());


        Mockito.when(gui.isActionActive(Action.S)).thenReturn(true);
        failedPosition = new Position(25,49);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(failedPosition, controller.move());
    }

    @Test
    void testFailedMovementsUp() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        Position basePosition = new Position(5,5);
        Mockito.when(player.getPosition()).thenReturn(basePosition);
        controller.setUpStrategy(strategy);

        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        Position failedPosition = new Position(25,-1);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(basePosition, controller.move());

        Mockito.when(gui.isActionActive(Action.W)).thenReturn(true);
        failedPosition = new Position(25,0);
        Mockito.when(strategy.move(Mockito.any(), Mockito.anyDouble())).thenReturn(failedPosition);

        assertEquals(failedPosition, controller.move());

    }

    @Test
    void testPlayerInteractions() {
        PlayerController controller = new PlayerController(player,gui,bulletPoolController,powerupController,firingController);
        controller.move();

    }

}
