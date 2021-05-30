package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.AsteroidGenerator;
import com.shootemup.g53.controller.gamebuilder.element.CoinGenerator;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameGeneratorTest {
    private GameController gameController;
    private GameModel gameModel;
    private WaveFactory waveFactory;
    private Wave wave;
    private Gui gui;

    private Random random;

    @BeforeEach
    void setup() {
        gameModel = Mockito.spy(new GameModel(20, 20));
        gameController = Mockito.mock(GameController.class);
        random = Mockito.mock(Random.class);
        waveFactory = Mockito.mock(WaveFactory.class);
        wave = Mockito.mock(Wave.class);
        Mockito.when(waveFactory.getNextWave(gameController)).thenReturn(wave);
        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);
        gui = Mockito.mock(Gui.class);
    }

    @Test
    void setupElementGenerators() {
        GameGenerator gameGenerator = new GameGenerator(gameController,2);

        List<ElementGenerator> generators = gameGenerator.getGenerators();

        Assertions.assertEquals(3, generators.size());
        Assertions.assertEquals(AsteroidGenerator.class, generators.get(0).getClass());
        Assertions.assertEquals(CoinGenerator.class, generators.get(1).getClass());
    }

    @Test
    void nextGeneration() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);
        GameGenerator gameGenerator = new GameGenerator(random, gameController, waveFactory, 2);

        Assertions.assertEquals(0, gameGenerator.getNextGeneration());
        gameGenerator.setNextGeneration();
        Assertions.assertEquals(3, gameGenerator.getNextGeneration());
        gameGenerator.setNextGeneration();
        Assertions.assertEquals(6, gameGenerator.getNextGeneration());
    }

    @Test
    void handle() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));
        List<ElementGenerator> elementGenerators = Arrays.asList(Mockito.mock(ElementGenerator.class));

        GameGenerator gameGenerator = new GameGenerator(random, gameController, waveFactory, 2);
        gameGenerator.setupElementGenerators(elementGenerators);

        gameGenerator.handle(0);
        Mockito.verify(waveFactory, Mockito.times(1))
                .getNextWave(gameController);
    }

    @Test
    void handleFailed() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));

        ElementGenerator elementGenerator = Mockito.mock(ElementGenerator.class);
        List<ElementGenerator> elementGenerators = Arrays.asList(elementGenerator);

        GameGenerator gameGenerator = new GameGenerator(random, gameController, waveFactory, 2);
        gameGenerator.setupElementGenerators(elementGenerators);

        gameGenerator.handle(0);
        gameGenerator.handle(1);

        Mockito.verify(wave, Mockito.times(1)).handle(0);
        Mockito.verify(waveFactory, Mockito.times(1))
                .getNextWave(gameController);
        Mockito.verify(elementGenerator, Mockito.times(1)).generateElement();
    }

    @Test
    void handleSeveral() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));

        ElementGenerator elementGenerator = Mockito.mock(ElementGenerator.class);
        List<ElementGenerator> elementGenerators = Arrays.asList(elementGenerator);

        GameGenerator gameGenerator = new GameGenerator(random, gameController, waveFactory, 2);
        gameGenerator.setupElementGenerators(elementGenerators);

        gameGenerator.handle(0);
        gameGenerator.handle(1);
        gameGenerator.handle(2);
        gameGenerator.handle(3);
        gameGenerator.handle(4);

        Mockito.verify(wave, Mockito.times(1)).handle(0);
        Mockito.verify(wave, Mockito.times(1)).handle(2);
        Mockito.verify(wave, Mockito.times(1)).handle(4);
        Mockito.verify(elementGenerator, Mockito.times(3)).generateElement();

        Mockito.verify(waveFactory, Mockito.times(1))
                .getNextWave(gameController);
    }

    @Test
    void handleNewWave() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));

        ElementGenerator elementGenerator = Mockito.mock(ElementGenerator.class);
        List<ElementGenerator> elementGenerators = Arrays.asList(elementGenerator);

        GameGenerator gameGenerator = new GameGenerator(random, gameController, waveFactory, 2);
        gameGenerator.setupElementGenerators(elementGenerators);

        gameGenerator.handle(0);

        Mockito.when(wave.handle(1)).thenReturn(true);
        gameGenerator.handle(1);

        gameGenerator.handle(2);

        Mockito.verify(waveFactory, Mockito.times(2))
                .getNextWave(gameController);
    }
}