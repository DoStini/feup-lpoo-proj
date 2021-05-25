package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;
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
    ShieldController shieldController;

    @BeforeEach
    void setup() {
        asteroidController = Mockito.spy(new AsteroidController(Mockito.mock(Asteroid.class), Mockito.mock(MovementStrategy.class)));
        bulletController = Mockito.spy(new BulletController(Mockito.mock(Bullet.class), Mockito.mock(MovementStrategy.class)));
        coinController = Mockito.spy(new CoinController(Mockito.mock(Coin.class), Mockito.mock(MovementStrategy.class)));
        spaceshipController = Mockito.spy(new SpaceshipController(Mockito.mock(Spaceship.class), Mockito.mock(FiringStrategy.class), Mockito.mock(MovementStrategy.class), Mockito.mock(BulletPoolController.class)));
        playerController = Mockito.spy(new PlayerController(Mockito.mock(Player.class), Mockito.mock(Gui.class), Mockito.mock(BulletPoolController.class),Mockito.mock(FiringStrategy.class)));
        shieldController = Mockito.spy(new ShieldController(Mockito.mock(Shield.class)));
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

        Mockito.verify(spaceshipController, Mockito.times(1)).handlePlayer(Mockito.any());

        shieldController.handleCollision(fakeCollider, fakeCollider, bulletController);

        Mockito.verify(bulletController, Mockito.times(1)).handleShield(Mockito.any());

    }

    @Test
    void playerCollisions() {
        Player player = new Player(new Position(0,0),0,10, "", 0);
        PlayerController playerController = Mockito.spy(new PlayerController(player, Mockito.mock(Gui.class), Mockito.mock(BulletPoolController.class),Mockito.mock(FiringStrategy.class)));

        playerController.handleCoin(Mockito.mock(Coin.class));
        playerController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertEquals(9, player.getHealth());

        playerController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertEquals(4, player.getHealth());

        playerController.handleAsteroid(Mockito.mock(Asteroid.class));

        Assertions.assertEquals(0, player.getHealth());

        playerController.handleShield(Mockito.mock(Shield.class));
        Assertions.assertEquals(0, player.getHealth());

    }

    @Test
    void spaceshipCollisions() {
        Spaceship spaceship = new Spaceship(new Position(0,0),0,10, "", 0);
        SpaceshipController spaceshipController = Mockito.spy(new SpaceshipController(spaceship, Mockito.mock(FiringStrategy.class), Mockito.mock(MovementStrategy.class), Mockito.mock(BulletPoolController.class)));

        spaceshipController.handleCoin(Mockito.mock(Coin.class));
        spaceshipController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertEquals(9, spaceship.getHealth());

        spaceshipController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertEquals(9, spaceship.getHealth());

        spaceshipController.handleAsteroid(Mockito.mock(Asteroid.class));

        Assertions.assertEquals(0, spaceship.getHealth());

        spaceship.setHealth(5);
        Assertions.assertEquals(5, spaceship.getHealth());

        spaceshipController.handleShield(Mockito.mock(Shield.class));
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
        Bullet bullet = new Bullet(new Position(0,0),"",0,0);
        BulletController bulletController = Mockito.spy(new BulletController(bullet, Mockito.mock(MovementStrategy.class)));

        bulletController.handleCoin(Mockito.mock(Coin.class));
        bulletController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertTrue(bullet.isActive());

        bulletController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertFalse(bullet.isActive());

        bullet.activate();

        Assertions.assertTrue(bullet.isActive());

        bulletController.handleAsteroid(Mockito.mock(Asteroid.class));

        Assertions.assertFalse(bullet.isActive());

        bullet.activate();

        Assertions.assertTrue(bullet.isActive());

        bulletController.handlePlayer(Mockito.mock(Player.class));

        Assertions.assertFalse(bullet.isActive());

        bullet.activate();

        Assertions.assertTrue(bullet.isActive());

        bulletController.handleShield(Mockito.mock(Shield.class));

        Assertions.assertFalse(bullet.isActive());


    }

    @Test
    void coinCollisions() {
        Coin coin = new Coin(new Position(0,0),1);
        CoinController coinController = Mockito.spy(new CoinController(coin, Mockito.mock(MovementStrategy.class)));

        coinController.handleCoin(Mockito.mock(Coin.class));
        coinController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertTrue(coin.isActive());

        coinController.handlePlayer(Mockito.mock(Player.class));

        Assertions.assertFalse(coin.isActive());

        coinController.handleAsteroid(Mockito.mock(Asteroid.class));
    }

    @Test
    void shieldCollisions() {
        Shield shield = new Shield(new Position(0,0), "#aaaaaa", 5, 1);
        ShieldController shieldController = Mockito.spy(new ShieldController(shield));

        Assertions.assertEquals(5, shield.getStrength());

        shieldController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertEquals(5, shield.getStrength());

        shieldController.handleSpaceship(Mockito.mock(Spaceship.class));

        Assertions.assertEquals(5, shield.getStrength());

        shieldController.handleBullet(Mockito.mock(Bullet.class));

        Assertions.assertEquals(4, shield.getStrength());

        Asteroid asteroid = Mockito.mock(Asteroid.class);
        Mockito.when(asteroid.getRadius()).thenReturn(2);
        shieldController.handleAsteroid(asteroid);

        Assertions.assertEquals(2, shield.getStrength());
    }
}
