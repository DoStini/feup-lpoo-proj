package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.model.game.GameModel;

import java.util.*;

public class Wave {
    private final GameModel gameModel;
    private final GameController gameController;
    private long nextGeneration;
    private long baseSkip;
    private Random rand;
    private ElementGenerator generator;
    private int currentEnemies;
    private int maxEnemies;

    public Wave(GameController gameController, long baseSkip, ElementGenerator generator, int maxEnemies){
        this(new Random(), gameController, baseSkip, generator, maxEnemies);
    }

    public Wave(Random rand, GameController gameController, long baseSkip, ElementGenerator generator, int maxEnemies) {
        this.rand = rand;
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
        this.baseSkip = baseSkip;
        this.maxEnemies = maxEnemies;
        this.nextGeneration = 0;
        setupGenerator(generator);
    }

    private void setupGenerator(ElementGenerator generator) {
        this.generator = generator;
    }

    public void setNextGeneration() {
        nextGeneration = nextGeneration + baseSkip + rand.nextInt((int) baseSkip);
    }

    public long getNextGeneration() {
        return nextGeneration;
    }

    public boolean handle(long time) {
        if (time < nextGeneration) return false;
        if (currentEnemies >= maxEnemies)
            return gameModel.getEnemySpaceships().size() == 0;

        setNextGeneration();

        generator.generateElement();
        currentEnemies++;

        return false;
    }
}
