package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.AsteroidGenerator;
import com.shootemup.g53.controller.gamebuilder.element.CoinGenerator;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.gamebuilder.element.EssenceGenerator;
import com.shootemup.g53.model.game.GameModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameGenerator {
    private long nextGeneration;
    private long baseSkip;
    private Random rand;
    private GameController gameController;
    private List<ElementGenerator> generators;
    private final WaveFactory waveFactory;
    protected GameModel gameModel;
    Wave currentWave;

    public GameGenerator(GameController gameController, long baseSkip){
        this(new Random(), gameController,
                new WaveFactory(1, 0.2f, 5, 100, 0.02f),
                baseSkip);
    }

    public GameGenerator(Random rand, GameController gameController, WaveFactory waveFactory, long baseSkip) {
        this.baseSkip = baseSkip;
        this.rand = rand;
        this.waveFactory = waveFactory;
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
        this.currentWave = waveFactory.getNextWave(gameController);

        setupElementGenerators();
    }

    private void setupElementGenerators() {
        MovementStrategyFactory movementStrategyFactory =
                new MovementStrategyFactory(Arrays.asList(MovementStrategyFactory.Strategy.DOWN));
        generators = Arrays.asList(
                new AsteroidGenerator(gameController, movementStrategyFactory, 0, gameModel.getWidth(), 0.2,
                        1, 10),
                new CoinGenerator(gameController, movementStrategyFactory,0, gameModel.getWidth(), 0.2, 1,
                        4),
                new EssenceGenerator(gameController, movementStrategyFactory, 0, gameModel.getWidth(), 0.2, 1));
    }

    protected void setupElementGenerators(List<ElementGenerator> strategyFactories) {
        generators = strategyFactories;
    }

    protected void setNextGeneration() {
        nextGeneration = nextGeneration + baseSkip + rand.nextInt((int) baseSkip);
    }

    public void handle(long time) {
        if(currentWave.handle(time))
            currentWave = waveFactory.getNextWave(gameController);

        if (time < nextGeneration) return;

        setNextGeneration();
        generators.get(rand.nextInt(generators.size())).generateElement();
    }

    protected List<ElementGenerator> getGenerators() {
        return generators;
    }

    protected long getNextGeneration() {
        return nextGeneration;
    }

    public WaveFactory getWaveFactory() {
        return waveFactory;
    }

    public long getBaseSkip() {
        return baseSkip;
    }
}
