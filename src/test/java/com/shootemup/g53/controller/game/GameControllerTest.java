package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.element.AsteroidController;
import com.shootemup.g53.controller.element.BulletController;
import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;
    private PlayerController playerController;
    private BulletController bulletController;
    private CoinController coinController;
    private AsteroidController asteroidController;
    private SpaceshipController spaceshipController;
    private Position position;
    private Position position2;
    private Bullet bullet;
    private Bullet bullet2;
    private Coin coin;
    private Asteroid asteroid;
    private Spaceship spaceship;
    private Spaceship player;

    Gui gui;

    @BeforeEach
    void setup() {
        gameModel = Mockito.mock(GameModel.class);
        position = Mockito.mock(Position.class);
        player = Mockito.mock(Spaceship.class);
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
        bullet = Mockito.mock(Bullet.class);
        bullet2 = Mockito.mock(Bullet.class);
        Mockito.when(gameModel.getWidth()).thenReturn(50);
        Mockito.when(gameModel.getHeight()).thenReturn(50);
        Mockito.when(gameModel.getBulletList()).thenReturn(Arrays.asList(bullet, bullet2));
        Mockito.when(gameModel.getAsteroids()).thenReturn(Arrays.asList(asteroid));
        Mockito.when(gameModel.getEnemySpaceships()).thenReturn(Arrays.asList(spaceship));
        Mockito.when(gameModel.getPlayer()).thenReturn(player);
        Mockito.when(gameModel.getCoins()).thenReturn(Arrays.asList(coin));
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
    void handleEscKeyPress() {
        Mockito.when(gui.isActionActive(Action.ESC)).thenReturn(true);
        GameController gameController = new GameController(gameModel,null);
        gameController.handleKeyPress(gui);
        Mockito.verify(gameModel, Mockito.times(1))
                .setGameFinished(true);
    }

    @Test
    void checkInsideBoounds() {
        GameController gameController = new GameController(gameModel, null);
        assertEquals(gameController.insideBounds(position), true);
        assertEquals(gameController.insideBounds(position2), false);
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
        gameController.handleBullets();
        Mockito.verify(bulletPoolController, Mockito.times(1)).removeInactiveBullets();

        Mockito.verify(bulletController, Mockito.times(2))
                .handle();

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
        GameController gameController = new GameController(gameModel, bulletPoolController);
        gameController.addToControllerMap(spaceship,spaceshipController);
        gameController.addToControllerMap(coin,coinController);
        gameController.addToControllerMap(asteroid,asteroidController);
        gameController.addToControllerMap(player,playerController);

        gameController.handlePlayerInput();
        Mockito.verify(playerController,Mockito.times(1)).handle();

        gameController.handleCoins();
        Mockito.verify(coinController,Mockito.times(1)).handle();

        gameController.handleAsteroids();
        Mockito.verify(asteroidController,Mockito.times(1)).handle();

        gameController.handleEnemies();
        Mockito.verify(spaceshipController,Mockito.times(1)).handle();

    }
}
