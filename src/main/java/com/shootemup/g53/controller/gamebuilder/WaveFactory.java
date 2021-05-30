package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.SpaceshipGenerator;
import com.shootemup.g53.controller.observer.WaveCompletionController;

import java.util.Arrays;

public class WaveFactory {
    int wave;
    private final int baseEnemies;
    private final float enemiesFactor;
    private final int bossWaveFactor;
    private final long baseSkip;
    private final float timeFactor;

    private MovementStrategyFactory movementStrategyFactory;
    private FiringStrategyFactory normalFiringStrategyFactory;
    private FiringStrategyFactory bossFiringStrategyFactory;

    private WaveCompletionController waveCompletionController;

    public WaveFactory(int baseEnemies, float enemiesFactor, int bossWaveFactor, int baseSkip, float timeFactor) {
        this(new WaveCompletionController(), baseEnemies, enemiesFactor, bossWaveFactor, baseSkip, timeFactor);
    }

    public WaveFactory(WaveCompletionController waveCompletionController, int baseEnemies, float enemiesFactor, int bossWaveFactor, int baseSkip, float timeFactor) {
        this.baseEnemies = baseEnemies;
        this.enemiesFactor = enemiesFactor;
        this.bossWaveFactor = bossWaveFactor;
        this.baseSkip = baseSkip;
        this.timeFactor = timeFactor;
        this.wave = 1;
        this.waveCompletionController = waveCompletionController;
        setupStrategies();
    }

    protected void setupStrategies() {
        movementStrategyFactory = new MovementStrategyFactory(
                Arrays.asList(MovementStrategyFactory.Strategy.CHANGING,
                        MovementStrategyFactory.Strategy.COMPOSITE,
                        MovementStrategyFactory.Strategy.DOWN,
                        MovementStrategyFactory.Strategy.DIAGONAL_BOUNCE,
                        MovementStrategyFactory.Strategy.CIRCULAR,
                        MovementStrategyFactory.Strategy.DIAGONAL_DOWN)
        );
        normalFiringStrategyFactory = new FiringStrategyFactory(
                Arrays.asList(FiringStrategyFactory.Strategy.NORMAL,
                                FiringStrategyFactory.Strategy.SPREAD_DOWN
                        )
        );
        bossFiringStrategyFactory = new FiringStrategyFactory(
                Arrays.asList(FiringStrategyFactory.Strategy.NORMAL,
                        FiringStrategyFactory.Strategy.SPREAD_DOWN,
                        FiringStrategyFactory.Strategy.MULTIPLE,
                        FiringStrategyFactory.Strategy.ALL_SPREAD
                )
        );
    }

    public Wave getNextWave(GameController gameController) {
        Wave result;
        int gameWidth = gameController.getGameModel().getWidth();
        if (isBossWave()) {
            result = getBossWave(gameController, gameWidth);
        } else {
            result = getNormalWave(gameController, gameWidth);
        }

        wave++;
        waveCompletionController.notifyObservers();
        return result;
    }

    private Wave getNormalWave(GameController gameController, int gameWidth) {
        Wave result;
        SpaceshipGenerator generator;
        generator = new SpaceshipGenerator(gameController, movementStrategyFactory, normalFiringStrategyFactory,
                5, gameWidth-5, 0.2,
                1.5, 2, 5, 10, 3);

        int skip = Math.max(1, Math.round(baseSkip*(1-timeFactor*(wave-1))));

        result = new Wave(gameController, skip,
                generator, Math.round(baseEnemies+(wave-1)*enemiesFactor));
        return result;
    }

    private Wave getBossWave(GameController gameController, int gameWidth) {
        Wave result;
        SpaceshipGenerator generator;
        generator = new SpaceshipGenerator(gameController, movementStrategyFactory, bossFiringStrategyFactory, 5,
                gameWidth-5, 0.05,
                0.1, 15, 25, 5, 5);
        generator.setMinHealth(100);
        generator.setMaxHealth(120);

        result = new Wave(gameController, 1, generator,
                wave/bossWaveFactor);
        return result;
    }

    private boolean isBossWave() {
        return wave % bossWaveFactor == 0;
    }

    public WaveCompletionController getWaveCompletionController() {
        return waveCompletionController;
    }

    public MovementStrategyFactory getMovementStrategyFactory() {
        return movementStrategyFactory;
    }

    public FiringStrategyFactory getNormalFiringStrategyFactory() {
        return normalFiringStrategyFactory;
    }

    public FiringStrategyFactory getBossFiringStrategyFactory() {
        return bossFiringStrategyFactory;
    }
}
