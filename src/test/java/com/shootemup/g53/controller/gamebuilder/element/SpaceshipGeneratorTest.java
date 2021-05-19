package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.game.GameController;
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
    private GameModel gameModel;

    private int xMinPos = 0,
            xMaxPos = 10,
            minSpeed = 2,
            maxSpeed = 10,
            maxSize = 4,
            maxFireRate = 5;

    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gameController = Mockito.mock(GameController.class);
        spaceship = Mockito.mock(Spaceship.class);
        gameModel = Mockito.mock(GameModel.class);

        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);

        spaceshipGenerator = new SpaceshipGenerator(random, gameController,
            xMinPos, xMaxPos, minSpeed, maxSpeed, maxSize, maxFireRate);
    }

    @Test
    void setFireRate() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        spaceshipGenerator.setFireRate(spaceship);

        Mockito.verify(random, Mockito.times(1)).nextInt(maxFireRate-1);
        Mockito.verify(spaceship, Mockito.times(1)).setFireRate(randomVal + 1);
    }

    @Test
    void setFireController() {

    }

    @Test
    void setSize() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        spaceshipGenerator.setSize(spaceship);

        Mockito.verify(random, Mockito.times(1)).nextInt(maxSize-2); // Min Height is 2
        Mockito.verify(spaceship, Mockito.times(1)).setHeight(randomVal + 2);
    }

    @Test
    void generateElement() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        String color = String.format("#%02x%02x%02x", randomVal, randomVal, randomVal);

        spaceshipGenerator.generateElement();

        Mockito.verify(gameModel, Mockito.times(1)).addEnemy(
                new Spaceship(new Position(randomVal, 0),
                        randomVal+2, color,
                        randomVal+minSpeed, randomVal+1,
                        null, null));
    }
}