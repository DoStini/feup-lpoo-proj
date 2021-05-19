package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.AsteroidGenerator;
import com.shootemup.g53.controller.gamebuilder.element.CoinGenerator;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

import java.util.*;

public class GameBuilder {
    private final GameModel gameModel;
    private final GameController gameController;
    private long nextGeneration;
    private long baseSkip;
    private Random rand;
    private List<ElementGenerator> generators;
    private final WaveFactory waveFactory;
    Wave currentWave;

    public GameBuilder(GameController gameController, long baseSkip){
        this(new Random(), gameController, baseSkip);
    }

    public GameBuilder(Random rand, GameController gameController, long baseSkip) {
        this.rand = rand;
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
        this.baseSkip = baseSkip;
        this.waveFactory = new WaveFactory(1, 1, 5, 20, 0.05f);
        setupGame();
    }

    private void setupGame() {
        Spaceship player = new Spaceship(new Position(20, 35), 3, "#aae243", 2, 15,
                null, new StraightBulletStrategy(new MoveUpwardsMovement(), 4));
        gameController.setPlayerController(new PlayerController(player));
        gameModel.setPlayer(player);
        gameModel.setCoins(new ArrayList<>());
        gameModel.setBulletList(new ArrayList<>());
        gameModel.setEnemySpaceships(new ArrayList<>());
        gameModel.setAsteroids(new ArrayList<>());
        setupElementGenerators();
        this.currentWave = waveFactory.getNextWave(gameController);
    }

    private void setupElementGenerators() {
        generators = Arrays.asList(
                new AsteroidGenerator(gameController, 0, gameModel.getWidth(), 1,
                        5, 10),
                new CoinGenerator(gameController, 0, gameModel.getWidth(), 1, 5,
                        4, -1, -1));
    }

    private void setNextGeneration() {
        nextGeneration = nextGeneration + baseSkip + rand.nextInt((int) baseSkip);
    }

    public void handle(long time) {
        if(currentWave.handle(time))
            currentWave = waveFactory.getNextWave(gameController);

        if (time < nextGeneration) return;

        setNextGeneration();
        generators.get(rand.nextInt(generators.size())).generateElement();
    }
}
