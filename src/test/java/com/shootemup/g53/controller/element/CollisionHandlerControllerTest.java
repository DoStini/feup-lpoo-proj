package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CollisionHandlerControllerTest {
    AsteroidController asteroidController;
    BulletController bulletController;
    CoinController coinController;
    SpaceshipController spaceshipController;
    PlayerController playerController;

    @BeforeEach
    void setup() {
        asteroidController = Mockito.spy(new AsteroidController(Mockito.mock(Asteroid.class), Mockito.mock(MovementStrategy.class)));
        bulletController = Mockito.spy(new BulletController(Mockito.mock(Bullet.class), Mockito.mock(MovementStrategy.class)));
        coinController = Mockito.spy(new CoinController(Mockito.mock(Coin.class), Mockito.mock(MovementStrategy.class)));
        spaceshipController = Mockito.spy(new SpaceshipController(Mockito.mock(Spaceship.class), Mockito.mock(FiringStrategy.class), Mockito.mock(MovementStrategy.class), Mockito.mock(BulletPoolController.class)));
        playerController = Mockito.spy(new PlayerController(Mockito.mock(Spaceship.class), Mockito.mock(Gui.class), Mockito.mock(BulletPoolController.class),Mockito.mock(FiringStrategy.class)));
    }

    @Test
    void visitorCorrect() {
        BodyCollider fakeCollider = Mockito.mock(BodyCollider.class);

        asteroidController.handleCollision(fakeCollider, fakeCollider, bulletController);

        Mockito.verify(bulletController, Mockito.times(1)).handleAsteroid(Mockito.any());

        bulletController.handleCollision(fakeCollider, fakeCollider, asteroidController);

        Mockito.verify(asteroidController, Mockito.times(1)).handleBullet(Mockito.any());

        spaceshipController.handleCollision(fakeCollider, fakeCollider, coinController);

        Mockito.verify(coinController, Mockito.times(1)).handleSpaceship(Mockito.any());

        coinController.handleCollision(fakeCollider, fakeCollider, spaceshipController);

        Mockito.verify(spaceshipController, Mockito.times(1)).handleCoin(Mockito.any());

        playerController.handleCollision(fakeCollider, fakeCollider, spaceshipController);

        Mockito.verify(spaceshipController, Mockito.times(1)).handleSpaceship(Mockito.any());
    }
}