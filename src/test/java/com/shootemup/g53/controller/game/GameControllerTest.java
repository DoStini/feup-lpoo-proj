package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.element.*;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.observer.ScoreController;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;
    private PlayerController playerController;
    private BulletController bulletController;
    private CoinController coinController;
    private AsteroidController asteroidController;
    private SpaceshipController spaceshipController;
    private BackgroundController backgroundController;
    private Position position;
    private Position position2;
    private Bullet bullet;
    private Bullet bullet2;
    private Coin coin;
    private Asteroid asteroid;
    private Spaceship spaceship;
    private Player player;
    private Shield shield;
    List<Bullet> bullets;
    List<Asteroid> asteroids;
    List<Spaceship> spaceships;
    List<Shield> shields;
    List<Coin> coins;
    long frame = 0;
    Gui gui;

    @BeforeEach
    void setup() {
        gameModel = Mockito.mock(GameModel.class);

        position = Mockito.mock(Position.class);
        player = Mockito.mock(Player.class);
        bulletController = Mockito.mock(BulletController.class);
        position2 = Mockito.mock(Position.class);
        coin = Mockito.mock(Coin.class);
        playerController = Mockito.mock(PlayerController.class);
        coinController = Mockito.mock(CoinController.class);
        asteroidController = Mockito.mock(AsteroidController.class);
        spaceshipController = Mockito.mock(SpaceshipController.class);
        asteroid = Mockito.mock(Asteroid.class);
        spaceship = Mockito.mock(Spaceship.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        Mockito.when(bulletController.move()).thenReturn(position);
        Mockito.doNothing().when(bulletPoolController).setGameController(Mockito.any(GameController.class));
        bullet = Mockito.mock(Bullet.class);
        bullet2 = Mockito.mock(Bullet.class);
        backgroundController = Mockito.mock(BackgroundController.class);
        shield = Mockito.mock(Shield.class);

        Mockito.when(gameModel.getWidth()).thenReturn(50);
        Mockito.when(gameModel.getHeight()).thenReturn(50);

        bullets = new ArrayList<>(); bullets.add(bullet); bullets.add(bullet2);
        Mockito.when(gameModel.getBulletList()).thenReturn(bullets);

        asteroids = new ArrayList<>(); asteroids.add(asteroid);
        Mockito.when(gameModel.getAsteroids()).thenReturn(asteroids);

        spaceships = new ArrayList<>(); spaceships.add(spaceship);
        Mockito.when(gameModel.getEnemySpaceships()).thenReturn(spaceships);

        shields = new ArrayList<>(); shields.add(shield);
        Mockito.when(gameModel.getShieldList()).thenReturn(shields);

        Mockito.when(gameModel.getPlayer()).thenReturn(player);

        coins = new ArrayList<>(); coins.add(coin);
        Mockito.when(gameModel.getCoins()).thenReturn(coins);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(5);
        Mockito.when(position2.getX()).thenReturn(55);
        Mockito.when(position2.getY()).thenReturn(55);

        Mockito.when(bullet.getPosition()).thenReturn(position2);

        Mockito.when(bullet.isActive()).thenReturn(true);
        Mockito.when(bullet2.getPosition()).thenReturn(position);

        Mockito.when(bullet2.isActive()).thenReturn(true);
        gui = Mockito.mock(Gui.class);
    }

    @Test
    void constructors() {
        GameController controller = new GameController(gameModel);

        Assertions.assertEquals(controller, controller.getBulletPoolController().getGameController());
        Assertions.assertEquals(30, controller.getBulletPoolController().bulletPool.getPoolSize());
        Assertions.assertFalse(controller.isGameFinished());

        Assertions.assertEquals(0, controller.controllerHashMap.size());

        controller.addToControllerMap(bullet, bulletController);

        Assertions.assertEquals(1, controller.controllerHashMap.size());
        Assertions.assertEquals(bulletController, controller.getElementController(bullet));

        controller.removeFromControllerMap(bullet);

        Assertions.assertEquals(0, controller.controllerHashMap.size());
        Assertions.assertEquals(null, controller.getElementController(bullet));

        controller.finishGame();

        Mockito.verify(gameModel, Mockito.times(1)).setGameFinished(true);
        Mockito.when(gameModel.isGameFinished()).thenReturn(true);

        Assertions.assertTrue(controller.isGameFinished());
    }

    @Test
    void handleEscKeyPress() {
        Mockito.when(gui.isActionActive(Action.ESC)).thenReturn(true);
        GameController gameController = new GameController(gameModel,null);
        gameController.handleKeyPress(gui);
        Mockito.verify(gameModel, Mockito.times(1))
                .setGameFinished(true);
    }

    @Test
    void handleQKeyPress() {
        Mockito.when(gui.isActionActive(Action.Q)).thenReturn(true);
        GameController gameController = new GameController(gameModel,null);
        gameController.handleKeyPress(gui);
        Mockito.verify(gameModel, Mockito.times(1))
                .setPaused(true);
    }

    @Test
    void checkInsideBounds() {
        GameController gameController = new GameController(gameModel, null);
        assertEquals(gameController.insideBounds(position, 0, 0), true);
        assertEquals(gameController.insideBounds(position2, 0, 0), false);
        Mockito.verify(gameModel, Mockito.times(2))
                .getWidth();
        Mockito.verify(gameModel, Mockito.times(1))
                .getHeight();
    }

    @Test
    void bulletHandleTest() {
        GameController gameController = new GameController(gameModel, bulletPoolController);
        gameController.addToControllerMap(bullet,bulletController);
        gameController.addToControllerMap(bullet2,bulletController);

        bulletPoolController.setGameController(gameController);
        gameController.handle(frame);
        Mockito.verify(bulletPoolController, Mockito.times(1)).removeInactiveBullets();

        Mockito.verify(bulletController, Mockito.times(2))
                .handle(frame);

    }

    @Test
    void handleElementRemovalTest(){
        GameController gameController = new GameController(gameModel, bulletPoolController);
        gameController.addToControllerMap(bullet,bulletController);
        gameController.addToControllerMap(bullet2,bulletController);
        Mockito.when(bullet.isActive()).thenReturn(false);
        Mockito.when(bullet2.isActive()).thenReturn(true);
        gameController.removeInactiveElements();
        assertEquals(gameController.numOfControllers(), 1);

    }

    @Test
    void GameEndTest(){
        GameController gameController = new GameController(gameModel, bulletPoolController);
        Mockito.when(gameModel.isGameFinished()).thenReturn(true);
        assertEquals(gameController.isGameFinished(), true);

    }

    @Test
    void HandleElementsTest(){
        GameController gameController = Mockito.spy(new GameController(gameModel, bulletPoolController));
        gameController.addToControllerMap(spaceship,spaceshipController);
        gameController.addToControllerMap(coin,coinController);
        gameController.addToControllerMap(asteroid,asteroidController);
        gameController.addToControllerMap(player,playerController);
        gameController.addToControllerMap(bullet, bulletController);
        gameController.addToControllerMap(bullet2, bulletController);
        gameController.setBackgroundController(backgroundController);

        gameController.handle(frame);
        Mockito.verify(gameController, Mockito.times(1)).handleCollision();
        Mockito.verify(gameController, Mockito.times(1)).checkOutsideBounds();
        Mockito.verify(gameController, Mockito.times(1)).deactivateDead();

        Mockito.verify(playerController,Mockito.times(1)).handle(frame);

        Mockito.verify(coinController,Mockito.times(1)).handle(frame);

        Mockito.verify(asteroidController,Mockito.times(1)).handle(frame);

        Mockito.verify(spaceshipController,Mockito.times(1)).handle(frame);

        gameController.handle(frame);
        Mockito.verify(backgroundController,Mockito.times(2)).handle(frame);
    }

    @Test
    void addRemoveGetCollisionHandler() {
        GameController gameController = new GameController(gameModel, bulletPoolController);

        Assertions.assertNull(gameController.getCollisionHandler(spaceship));

        gameController.addToCollisionMap(spaceship, spaceshipController);

        Assertions.assertEquals(spaceshipController, gameController.getCollisionHandler(spaceship));

        gameController.addToCollisionMap(coin, coinController);

        Assertions.assertEquals(spaceshipController, gameController.getCollisionHandler(spaceship));
        Assertions.assertEquals(coinController, gameController.getCollisionHandler(coin));


        gameController.removeFromCollisionMap(coin);

        Assertions.assertEquals(spaceshipController, gameController.getCollisionHandler(spaceship));
        Assertions.assertNull(gameController.getCollisionHandler(coin));
    }

    @Test
    void settersGetters(){
        GameController gameController = new GameController(gameModel, bulletPoolController);

        gameController.setBackgroundController(backgroundController);

        Assertions.assertEquals(gameController.getBackgroundController(), gameController.getBackgroundController());
        Assertions.assertEquals(backgroundController, gameController.getBackgroundController());
    }

    @Test
    void removeDead() {
        GameController gameController = new GameController(gameModel, bulletPoolController);
        ScoreController scoreController = Mockito.mock(ScoreController.class);

        gameController.setScoreController(scoreController);

        Mockito.when(spaceship.getHealth()).thenReturn(10);
        Mockito.when(shield.getStrength()).thenReturn(5);

        gameController.deactivateDead();

        Mockito.verify(spaceship,Mockito.times(0)).deactivate();
        Mockito.verify(shield,Mockito.times(0)).deactivate();

        Mockito.when(spaceship.getHealth()).thenReturn(0);
        Mockito.when(shield.getStrength()).thenReturn(0);

        gameController.deactivateDead();

        Mockito.verify(spaceship,Mockito.times(1)).deactivate();
        Mockito.verify(shield,Mockito.times(1)).deactivate();
        Mockito.verify(scoreController, Mockito.times(1)).notifyObservers();

        Mockito.when(player.getHealth()).thenReturn(5);

        gameController.handle(frame);

        Mockito.verify(gameModel,Mockito.times(0)).setGameFinished(true);
        Mockito.verify(scoreController, Mockito.times(2)).notifyObservers();

        Mockito.when(player.getHealth()).thenReturn(0);

        gameController.handle(frame);

        Mockito.verify(gameModel,Mockito.times(1)).setGameFinished(true);
        Mockito.verify(scoreController, Mockito.times(3)).notifyObservers();
    }

    @Test
    void checkOutsideBounds() {
        GameController gameController = Mockito.spy(new GameController(gameModel, bulletPoolController));

        Mockito.when(gameModel.getWidth()).thenReturn(10);
        Mockito.when(gameModel.getHeight()).thenReturn(10);

        Mockito.when(bullet.getPosition()).thenReturn(new Position(5,5));
        Mockito.when(spaceship.getPosition()).thenReturn(new Position(5,5));
        Mockito.when(coin.getPosition()).thenReturn(new Position(5,5));
        Mockito.when(asteroid.getPosition()).thenReturn(new Position(5,5));
        Mockito.when(bullet2.getPosition()).thenReturn(new Position(5,5));

        gameController.checkOutsideBounds();

        Mockito.verify(gameController, Mockito.times(5)).insideBounds(Mockito.any(),
                Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(gameController, Mockito.times(5)).insideBounds(Mockito.eq(new Position(5,5)),
                Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(bullet, Mockito.times(0)).deactivate();
        Mockito.verify(spaceship, Mockito.times(0)).deactivate();
        Mockito.verify(coin, Mockito.times(0)).deactivate();
        Mockito.verify(asteroid, Mockito.times(0)).deactivate();

        Mockito.when(bullet.getPosition()).thenReturn(new Position(0,0));
        Mockito.when(spaceship.getPosition()).thenReturn(new Position(0,0));
        Mockito.when(coin.getPosition()).thenReturn(new Position(0,0));
        Mockito.when(asteroid.getPosition()).thenReturn(new Position(0,0));
        Mockito.when(bullet2.getPosition()).thenReturn(new Position(0,0));

        gameController.checkOutsideBounds();

        Mockito.verify(bullet, Mockito.times(0)).deactivate();
        Mockito.verify(spaceship, Mockito.times(0)).deactivate();
        Mockito.verify(coin, Mockito.times(0)).deactivate();
        Mockito.verify(asteroid, Mockito.times(0)).deactivate();

        Mockito.when(bullet.getPosition()).thenReturn(new Position(-1,0));
        Mockito.when(spaceship.getPosition()).thenReturn(new Position(-1,0));
        Mockito.when(coin.getPosition()).thenReturn(new Position(-1,0));
        Mockito.when(asteroid.getPosition()).thenReturn(new Position(-1,0));
        Mockito.when(bullet2.getPosition()).thenReturn(new Position(-1,0));

        Position position = bullet2.getPosition();

        gameController.checkOutsideBounds();

        Mockito.verify(bullet2, Mockito.times(1)).deactivate();
        Mockito.verify(bullet, Mockito.times(1)).deactivate();
        Mockito.verify(spaceship, Mockito.times(1)).deactivate();
        Mockito.verify(coin, Mockito.times(1)).deactivate();
        Mockito.verify(asteroid, Mockito.times(1)).deactivate();
    }
}
