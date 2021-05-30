package com.shootemup.g53.controller.gamebuilder;


import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.SpaceshipGenerator;
import com.shootemup.g53.controller.observer.WaveCompletionController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class WaveFactoryTest {

    GameController gameController;
    GameModel gameModel;
    private final int gameWidth = 10;

    @BeforeEach
    void setUp() {
        gameController = Mockito.mock(GameController.class);
        gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);
        Mockito.when(gameModel.getWidth()).thenReturn(gameWidth);
    }

    @Test
    void normalWave() {
        WaveFactory waveFactory = new WaveFactory(1, 1, 2, 5, 1);

        Wave wave = waveFactory.getNextWave(gameController);

        Assertions.assertEquals(5, wave.getBaseSkip());
        Assertions.assertEquals(1, wave.getMaxEnemies());

        SpaceshipGenerator spaceshipGenerator = (SpaceshipGenerator) wave.getGenerator();

        Assertions.assertEquals(5, spaceshipGenerator.getxMinPos());
        Assertions.assertEquals(gameWidth-5, spaceshipGenerator.getxMaxPos());
        Assertions.assertEquals(0.2, spaceshipGenerator.getMinSpeed());
        Assertions.assertEquals(1.5, spaceshipGenerator.getMaxSpeed());
        Assertions.assertEquals(2, spaceshipGenerator.getMinSize());
        Assertions.assertEquals(5, spaceshipGenerator.getMaxSize());
        Assertions.assertEquals(10, spaceshipGenerator.getMaxFireRate());
        Assertions.assertEquals(3, spaceshipGenerator.getMaxDamage());
        Assertions.assertEquals(5, spaceshipGenerator.getMinHealth());
        Assertions.assertEquals(10, spaceshipGenerator.getMaxHealth());

    }

    @Test
    void bossWave() {
        WaveFactory waveFactory = new WaveFactory(5, 5, 2, 5, 1);

        waveFactory.getNextWave(gameController);
        Wave wave = waveFactory.getNextWave(gameController);

        Assertions.assertEquals(1, wave.getBaseSkip());
        Assertions.assertEquals(1, wave.getMaxEnemies());

        SpaceshipGenerator spaceshipGenerator = (SpaceshipGenerator) wave.getGenerator();
        Assertions.assertEquals(5, spaceshipGenerator.getxMinPos());
        Assertions.assertEquals(gameWidth-5, spaceshipGenerator.getxMaxPos());
        Assertions.assertEquals(0.05, spaceshipGenerator.getMinSpeed());
        Assertions.assertEquals(0.1, spaceshipGenerator.getMaxSpeed());
        Assertions.assertEquals(15, spaceshipGenerator.getMinSize());
        Assertions.assertEquals(25, spaceshipGenerator.getMaxSize());
        Assertions.assertEquals(5, spaceshipGenerator.getMaxFireRate());
        Assertions.assertEquals(5, spaceshipGenerator.getMaxDamage());
        Assertions.assertEquals(100, spaceshipGenerator.getMinHealth());
        Assertions.assertEquals(120, spaceshipGenerator.getMaxHealth());
    }

    @Test
    void secondBossWave() {
        WaveFactory waveFactory = new WaveFactory(1, 1, 1, 5, 1);

        waveFactory.getNextWave(gameController);
        Wave wave = waveFactory.getNextWave(gameController);

        Assertions.assertEquals(1, wave.getBaseSkip());
        Assertions.assertEquals(2, wave.getMaxEnemies());
    }

    @Test
    void incrementalWaves() {
        WaveFactory waveFactory = new WaveFactory(1, 0.2f, 1000, 4, 0.25f);

        Wave wave = waveFactory.getNextWave(gameController);
        Assertions.assertEquals(4, wave.getBaseSkip());
        Assertions.assertEquals(1, wave.getMaxEnemies());

        wave = waveFactory.getNextWave(gameController);
        Assertions.assertEquals(3, wave.getBaseSkip());
        Assertions.assertEquals(1, wave.getMaxEnemies());

        wave = waveFactory.getNextWave(gameController);
        Assertions.assertEquals(2, wave.getBaseSkip());
        Assertions.assertEquals(1, wave.getMaxEnemies());

        wave = waveFactory.getNextWave(gameController);
        Assertions.assertEquals(1, wave.getBaseSkip());
        Assertions.assertEquals(2, wave.getMaxEnemies());

        wave = waveFactory.getNextWave(gameController);
        Assertions.assertEquals(1, wave.getBaseSkip());
        Assertions.assertEquals(2, wave.getMaxEnemies());

    }

    @Test
    void notifiers() {
        WaveCompletionController waveCompletionController = Mockito.mock(WaveCompletionController.class);
        WaveFactory waveFactory = new WaveFactory(waveCompletionController,
                5, 5, 1, 5, 1);

        waveFactory.getNextWave(gameController);
        Mockito.verify(waveCompletionController, Mockito.times(1)).notifyObservers();
    }

    @Test
    void setupStrategies() {
        List<MovementStrategyFactory.Strategy> movement = Arrays.asList(MovementStrategyFactory.Strategy.CHANGING,
                MovementStrategyFactory.Strategy.COMPOSITE,
                MovementStrategyFactory.Strategy.DOWN,
                MovementStrategyFactory.Strategy.DIAGONAL_BOUNCE,
                MovementStrategyFactory.Strategy.CIRCULAR,
                MovementStrategyFactory.Strategy.DIAGONAL_DOWN);

        List<FiringStrategyFactory.Strategy> normalFire = Arrays.asList(FiringStrategyFactory.Strategy.NORMAL,
                FiringStrategyFactory.Strategy.SPREAD_DOWN
        );

        List<FiringStrategyFactory.Strategy> bossFire = Arrays.asList(FiringStrategyFactory.Strategy.NORMAL,
                FiringStrategyFactory.Strategy.SPREAD_DOWN,
                FiringStrategyFactory.Strategy.MULTIPLE,
                FiringStrategyFactory.Strategy.ALL_SPREAD
        );


        WaveFactory waveFactory = new WaveFactory(1, 1, 1, 1, 1);

        Assertions.assertEquals(movement, waveFactory.getMovementStrategyFactory().getStrategies());
        Assertions.assertEquals(normalFire, waveFactory.getNormalFiringStrategyFactory().getStrategies());
        Assertions.assertEquals(bossFire, waveFactory.getBossFiringStrategyFactory().getStrategies());
    }

}