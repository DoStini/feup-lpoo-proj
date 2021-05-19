package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementControllerTest {
    private Asteroid asteroid;
    private Spaceship spaceship;
    private Bullet bullet;
    private Coin coin;
    private MovementStrategy movementStrategy;
    private BulletPoolController bulletPoolController;
    private FiringStrategy firingStrategy;
    private Position position;
    private int speed = 5;

    Gui gui;

    @BeforeEach
    void setup() {
        asteroid = Mockito.mock(Asteroid.class);
        bullet = Mockito.mock(Bullet.class);
        spaceship = Mockito.mock(Spaceship.class);
        position = Mockito.mock(Position.class);
        coin = Mockito.mock(Coin.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        firingStrategy = Mockito.mock(StraightBulletStrategy.class);
        Mockito.when(coin.getPosition()).thenReturn(position);
        Mockito.when(coin.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getPosition()).thenReturn(position);
        Mockito.when(spaceship.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getHealth()).thenReturn(3);
        Mockito.when(bullet.getPosition()).thenReturn(position);
        Mockito.when(bullet.getSpeed()).thenReturn(speed);
        Mockito.when(asteroid.getSpeed()).thenReturn(speed);
        movementStrategy = Mockito.mock(FallDownMovement.class);
        Mockito.when(movementStrategy.move(position,speed)).thenReturn(position);

        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(5);

    }

    @Test
    void AsteroidControllerTest(){
        Mockito.when(asteroid.getPosition()).thenReturn(position);

        AsteroidController asteroidController = new AsteroidController(asteroid,movementStrategy);
        asteroidController.move();
        Mockito.verify(movementStrategy, Mockito.times(1)).move(position,speed);

        assertEquals(asteroidController.getAsteroid(), asteroid);
        asteroidController.handle();
        Mockito.verify(asteroid,Mockito.times(1)).setPosition(position);
    }

    @Test
    void BulletControllerTest(){

        BulletController bulletController = new BulletController(bullet,movementStrategy);
        bulletController.move();
        Mockito.verify(movementStrategy, Mockito.times(1)).move(position,speed);

        assertEquals(bulletController.getBullet(), bullet);
        bulletController.handle();

        Mockito.verify(bullet,Mockito.times(1)).setPosition(position);
        assertEquals(bulletController.getBullet(),bullet);
        bulletController.handleSpaceship(spaceship);
        Mockito.verify(bullet,Mockito.times(1)).deactivate();
    }
    @Test
    void CoinControllerTest(){

        CoinController coinController = new CoinController(coin,movementStrategy);
        coinController.move();
        Mockito.verify(movementStrategy, Mockito.times(1)).move(position,speed);

        assertEquals(coinController.getCoin(), coin);
        coinController.handle();

        Mockito.verify(coin,Mockito.times(1)).setPosition(position);

        coinController.handleSpaceship(spaceship);
        Mockito.verify(coin,Mockito.times(1)).deactivate();
    }

    @Test
    void SpaceshipControllerTest(){

        SpaceshipController spaceshipController = new SpaceshipController(spaceship,firingStrategy, movementStrategy,bulletPoolController);
        spaceshipController.move();
        Mockito.verify(movementStrategy, Mockito.times(1)).move(position,speed);

        assertEquals(spaceshipController.getSpaceship(), spaceship);
        spaceshipController.handle();

        Mockito.verify(spaceship,Mockito.times(1)).setPosition(position);
        Mockito.verify(firingStrategy,Mockito.times(1)).fire(spaceship,bulletPoolController, ColliderCategory.ENEMY_BULLET);

        spaceshipController.handleAsteroid(asteroid);
        Mockito.verify(spaceship,Mockito.times(1)).setHealth(0);

        spaceshipController.handleBullet(bullet);
        Mockito.verify(spaceship,Mockito.times(1)).setHealth(spaceship.getHealth() - 1);

        spaceshipController.handleFailedMovement();
        Mockito.verify(movementStrategy,Mockito.times(1)).handleFailedMovement();

    }




}
