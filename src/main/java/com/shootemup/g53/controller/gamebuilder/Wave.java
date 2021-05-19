package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.gamebuilder.element.SpaceshipGenerator;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

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
        setNextGeneration();
        setupGenerator(generator);
    }

    private void setupGenerator(ElementGenerator generator) {
        this.generator = generator;
    }

    public void setNextGeneration() {
        System.out.println(baseSkip);
        nextGeneration = nextGeneration + baseSkip + rand.nextInt((int) baseSkip);
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
