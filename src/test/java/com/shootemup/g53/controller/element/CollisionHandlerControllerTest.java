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
import com.shootemup.g53.model.util.Position;
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

    @Test
    void playerCollisions() {
        Spaceship player = new Spaceship(new Position(0,0),0,10, "", 0, 2);
        PlayerController playerController = Mockito.spy(new PlayerController(player, Mockito.mock(Gui.class), Mockito.mock(BulletPoolController.class),Mockito.mock(FiringStrategy.class)));

        playerController.handleCoin(Mockito.mock(Coin.class));
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(bullet.getDamage()).thenReturn(4);
        playerController.handleBullet(bullet);

        Assertions.assertEquals(6, player.getHealth());

        playerController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertEquals(1, player.getHealth());

        playerController.handleAsteroid(Mockito.mock(Asteroid.class));

        Assertions.assertEquals(0, player.getHealth());
    }

    @Test
    void spaceshipCollisions() {
        Spaceship spaceship = new Spaceship(new Position(0,0),0,10, "", 0, 3);
        SpaceshipController spaceshipController = Mockito.spy(new SpaceshipController(spaceship, Mockito.mock(FiringStrategy.class), Mockito.mock(MovementStrategy.class), Mockito.mock(BulletPoolController.class)));

        spaceshipController.handleCoin(Mockito.mock(Coin.class));
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(bullet.getDamage()).thenReturn(3);
        spaceshipController.handleBullet(bullet);

        Assertions.assertEquals(7, spaceship.getHealth());

        spaceshipController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertEquals(7, spaceship.getHealth());

        spaceshipController.handleAsteroid(Mockito.mock(Asteroid.class));

        Assertions.assertEquals(0, spaceship.getHealth());
    }

    @Test
    void asteroidCollisions() {
        Asteroid asteroid = new Asteroid(new Position(0,0),1);
        AsteroidController asteroidController = Mockito.spy(new AsteroidController(asteroid, Mockito.mock(MovementStrategy.class)));

        asteroidController.handleCoin(Mockito.mock(Coin.class));
        asteroidController.handleBullet(Mockito.mock(Bullet.class));
        asteroidController.handleSpaceship(Mockito.mock(Spaceship.class));
        asteroidController.handleAsteroid(Mockito.mock(Asteroid.class));
    }

    @Test
    void bulletCollisions() {
        Bullet bullet = new Bullet(new Position(0,0),"",0,0, 2);
        BulletController bulletController = Mockito.spy(new BulletController(bullet, Mockito.mock(MovementStrategy.class)));

        bulletController.handleCoin(Mockito.mock(Coin.class));
        bulletController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertTrue(bullet.isActive());

        bulletController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertFalse(bullet.isActive());

        bulletController.handleAsteroid(Mockito.mock(Asteroid.class));
    }

    @Test
    void coinCollisions() {
        Coin coin = new Coin(new Position(0,0),1);
        CoinController coinController = Mockito.spy(new CoinController(coin, Mockito.mock(MovementStrategy.class)));

        coinController.handleCoin(Mockito.mock(Coin.class));
        coinController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertTrue(coin.isActive());

        coinController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertFalse(coin.isActive());

        coinController.handleAsteroid(Mockito.mock(Asteroid.class));
    }
}