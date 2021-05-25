package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.FiringStrategyFactory;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class SpaceshipGeneratorTest {

    private Random random;
    private Spaceship spaceship;
    private SpaceshipGenerator spaceshipGenerator;
    private GameController gameController;
    private MovementStrategyFactory movementStrategyFactory;
    private FiringStrategyFactory firingStrategyFactory;
    private GameModel gameModel;

    private int xMinPos = 0,
            xMaxPos = 10,
            minSpeed = 2,
            maxSpeed = 10,
            minSize = 2,
            maxSize = 4,
            maxFireRate = 5;

    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gameController = Mockito.mock(GameController.class);
        spaceship = Mockito.mock(Spaceship.class);
        gameModel = Mockito.mock(GameModel.class);
        movementStrategyFactory = Mockito.mock(MovementStrategyFactory.class);
        firingStrategyFactory = Mockito.mock(FiringStrategyFactory.class);

        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);

        spaceshipGenerator = new SpaceshipGenerator(random, gameController,
            movementStrategyFactory, firingStrategyFactory,
            xMinPos, xMaxPos, minSpeed, maxSpeed, minSize, maxSize, maxFireRate);
    }

    @Test
    void setFireRate() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
    }

    @Test
    void setFireController() {

    }

    @Test
    void setMovementController() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(Mockito.mock(MovementStrategy.class));
        Mockito.when(firingStrategyFactory.generate(Mockito.any())).thenReturn(Mockito.mock(FiringStrategy.class));

        spaceshipGenerator.setController(spaceship);
        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.eq(spaceship), Mockito.any(SpaceshipController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.eq(spaceship), Mockito.any(SpaceshipController.class));

    }

    @Test
    void setSize() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        spaceshipGenerator.setSize(spaceship);

        Mockito.verify(random, Mockito.times(1)).nextInt(maxSize-minSize);
        Mockito.verify(spaceship, Mockito.times(1)).setHeight(randomVal + minSize);
    }

    @Test
    void generateElement() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(Mockito.mock(MovementStrategy.class));
        Mockito.when(firingStrategyFactory.generate(Mockito.any())).thenReturn(Mockito.mock(FiringStrategy.class));

        String color = String.format("#%02x%02x%02x", randomVal, randomVal, randomVal);

        spaceshipGenerator.generateElement();

        Mockito.verify(gameModel, Mockito.times(1)).addEnemy(
                new Spaceship(new Position(randomVal, 0),
                        randomVal+minSize, 100, color, randomVal+minSpeed));
    }
}
