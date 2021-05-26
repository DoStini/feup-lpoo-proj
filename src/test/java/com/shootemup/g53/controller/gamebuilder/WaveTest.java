package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WaveTest {

    private Wave wave;
    private Random random;
    private GameController gameController;
    private GameModel gameModel;
    private ElementGenerator generator;

    @BeforeEach
    void setup() {
        generator = Mockito.mock(ElementGenerator.class);
        gameModel = Mockito.mock(GameModel.class);
        gameController = Mockito.mock(GameController.class);
        random = Mockito.mock(Random.class);
        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);
        wave = new Wave(random, gameController, 50, generator, 2);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);
    }

    @Test
    void nextGeneration() {
        wave.setNextGeneration();
        Assertions.assertEquals(51, wave.getNextGeneration());
        wave.setNextGeneration();
        Assertions.assertEquals(102, wave.getNextGeneration());
    }

    @Test
    void handle() {
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));

        Assertions.assertFalse(wave.handle(0));

        Mockito.verify(generator, Mockito.times(1)).generateElement();

    }

    @Test
    void handleFailed() {
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));

        Assertions.assertFalse(wave.handle(0));
        Assertions.assertFalse(wave.handle(1));

        Mockito.verify(generator, Mockito.times(1)).generateElement();

    }

    @Test
    void handleRate() {
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));

        Assertions.assertFalse(wave.handle(0));
        Assertions.assertFalse(wave.handle(50));

        Assertions.assertFalse(wave.handle(51));

        Assertions.assertFalse(wave.handle(102));

        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList());

        Assertions.assertTrue(wave.handle(154));


        Mockito.verify(generator, Mockito.times(2)).generateElement();
    }
}
