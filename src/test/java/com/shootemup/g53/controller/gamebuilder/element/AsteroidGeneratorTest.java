package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.AsteroidController;
import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class AsteroidGeneratorTest {

    private Random random;
    private Asteroid asteroid;
    private AsteroidGenerator asteroidGenerator;
    private MovementStrategyFactory movementStrategyFactory;
    private GameController gameController;
    private GameModel gameModel;

    private int xMinPos = 3,
            xMaxPos = 10,
            minSpeed = 2,
            maxSpeed = 10,
            maxRadius = 4,
            minVal = 2,
            maxVal = 3;

    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gameController = Mockito.mock(GameController.class);
        asteroid = Mockito.mock(Asteroid.class);
        gameModel = Mockito.mock(GameModel.class);

        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);
        movementStrategyFactory = Mockito.mock(MovementStrategyFactory.class);

        asteroidGenerator = new AsteroidGenerator(random, gameController, movementStrategyFactory,
                xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius);
    }

    @Test
    void setPosition() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        asteroidGenerator.setPosition(asteroid);

        Mockito.verify(random, Mockito.times(1)).nextInt(xMaxPos - xMinPos); // Min Height is 2
        Mockito.verify(asteroid, Mockito.times(1)).setPosition(new Position(randomVal+xMinPos, 0));
    }

    @Test
    void setMovementController() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(new FallDownMovement());

        asteroidGenerator.setMovement(asteroid);
        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.eq(asteroid), Mockito.any(AsteroidController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.eq(asteroid), Mockito.any(AsteroidController.class));

    }

    @Test
    void setCollider() {
        asteroidGenerator.setCollider(asteroid);
        BodyCollider collider =
                new LineCompositeFactory().createFromCircle(asteroid, new Position(0, 0), asteroid.getRadius());
        collider.setCategory(ColliderCategory.ENEMY);

        Mockito.verify(gameModel, Mockito.times(1))
                .addCollider(collider);
    }

    @Test
    void setColor() {
        int randomVal = 255;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        asteroidGenerator.setColor(asteroid);

        Mockito.verify(random, Mockito.times(3)).nextInt( 255); // Min Height is 2
        Mockito.verify(asteroid, Mockito.times(1)).setColor("#ffffff");
    }

    @Test
    void setRadius() {
        int randomVal = 6;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        asteroidGenerator.setRadius(asteroid);

        Mockito.verify(random, Mockito.times(1)).nextInt( maxRadius-1);
        Mockito.verify(asteroid, Mockito.times(1)).setRadius(randomVal+1);
    }

    @Test
    void generateElement() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(Mockito.mock(MovementStrategy.class));

        asteroidGenerator.generateElement();

        Mockito.verify(gameModel, Mockito.times(1)).addAsteroid(
                new Asteroid(new Position(randomVal+xMinPos, 0), randomVal+1));
        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.any(Asteroid.class), Mockito.any(AsteroidController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.any(Asteroid.class), Mockito.any(AsteroidController.class));
        Mockito.verify(gameModel, Mockito.times(1)).addCollider(Mockito.any(BodyCollider.class));
    }
}