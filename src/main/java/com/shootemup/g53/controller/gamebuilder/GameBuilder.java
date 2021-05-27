package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.AsteroidGenerator;
import com.shootemup.g53.controller.gamebuilder.element.CoinGenerator;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.gamebuilder.element.EssenceGenerator;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.controller.player.PowerupController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.*;

public class GameBuilder {
    private final GameModel gameModel;
    private final GameController gameController;
    private final Gui gui;
    private long nextGeneration;
    private long baseSkip;
    private Random rand;
    private List<ElementGenerator> generators;
    private final WaveFactory waveFactory;
    Wave currentWave;

    public GameBuilder(Gui gui, GameController gameController, long baseSkip){
        this(new Random(), gui, gameController,
                new WaveFactory(1, 0.2f, 5, 100, 0.02f),
                baseSkip);
    }

    public GameBuilder(Random rand, Gui gui, GameController gameController, WaveFactory waveFactory, long baseSkip) {
        this.rand = rand;
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
        this.baseSkip = baseSkip;
        this.waveFactory = waveFactory;
        this.gui = gui;
        setupGame();
    }

    private void setupPlayer() {
        Player spaceship = new Player(new Position(20, 35), 3, 20, "#aae253",
                3, 10);
        gameModel.setPlayer(spaceship);

        List<BodyCollider> colliders = new ArrayList<>();

        BodyCollider playerCollider =
                new LineCompositeFactory().createFromIsoscelesTriangle(spaceship, new Position(0, 0), 3);
        playerCollider.setCategory(ColliderCategory.PLAYER);
        playerCollider.setCategoryMask(
                (short) (ColliderCategory.ENEMY.getBits() |
                         ColliderCategory.ENEMY_BULLET.getBits() |
                         ColliderCategory.PICKUP.getBits()));

        colliders.add(playerCollider);
        gameModel.setColliders(colliders);

        PowerupController powerupController = new PowerupController(gameController);

        PlayerController playerController = new PlayerController(spaceship, gui, gameController.getBulletPoolController(),
                powerupController,
                new MovingBulletStrategy(new MoveUpwardsMovement(), 3, 5));
        gameController.addToControllerMap(spaceship, playerController);
        gameController.addToCollisionMap(spaceship, playerController);
    }

    private void setupBackground() {
        Background background = new Background(25, 30);

        gameController.setBackgroundController(new BackgroundController(gameModel, background, 30, 5));
        gameModel.setBackground(background);
    }

    private void setupGame() {
        setupPlayer();
        setupBackground();
        setupElements();
        setupElementGenerators();
        this.currentWave = waveFactory.getNextWave(gameController);
    }

    private void setupElements() {
        gameModel.setCoins(new ArrayList<>());
        gameModel.setBulletList(new ArrayList<>());
        gameModel.setEnemySpaceships(new ArrayList<>());
        gameModel.setAsteroids(new ArrayList<>());
        gameModel.setShields(new ArrayList<>());
        gameModel.setEssences(new ArrayList<>());
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
}
