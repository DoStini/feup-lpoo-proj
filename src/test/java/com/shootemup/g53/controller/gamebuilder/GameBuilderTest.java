package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.AsteroidGenerator;
import com.shootemup.g53.controller.gamebuilder.element.CoinGenerator;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameBuilderTest {

    private GameController gameController;
    private GameModel gameModel;
    private WaveFactory waveFactory;
    private Wave wave;
    private Gui gui;

    private GameBuilder gameBuilder;
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
    void setupPlayer() {
        gameBuilder = new GameBuilder(gui, gameController, 2);

        Player spaceship = new Player(new Position(20, 35), 3, 20, "#aae253", 4,5);

        BodyCollider playerCollider =
                new LineCompositeFactory().createFromIsoscelesTriangle(spaceship, new Position(0, 0), 3);
        playerCollider.setCategory(ColliderCategory.PLAYER);
        playerCollider.setCategoryMask(
                (short) (ColliderCategory.ENEMY.getBits() |
                        ColliderCategory.ENEMY_BULLET.getBits() |
                        ColliderCategory.PICKUP.getBits()));

        Mockito.verify(gameModel, Mockito.times(1))
                .setPlayer(spaceship);
        Mockito.verify(gameModel, Mockito.times(1))
                .setColliders(Arrays.asList(playerCollider));

        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.eq(spaceship), Mockito.any(PlayerController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.eq(spaceship), Mockito.any(PlayerController.class));
    }

    @Test
    void setupElements() {
        gameBuilder = new GameBuilder(gui, gameController, 2);

        Mockito.verify(gameModel, Mockito.times(1)).setCoins(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setBulletList(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setEnemySpaceships(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setAsteroids(new ArrayList<>());

    }

    @Test
    void setupBackground() {
        gameBuilder = new GameBuilder(gui, gameController, 2);

        Mockito.verify(gameController, Mockito.times(1))
                .setBackgroundController(Mockito.any(BackgroundController.class));

        Mockito.verify(gameModel, Mockito.times(1))
                .setBackground(Mockito.any(Background.class));

        Assertions.assertEquals(25, gameModel.getBackground().getMinStars());
        Assertions.assertEquals(30, gameModel.getBackground().getMaxStars());
    }

    @Test
    void setupElementGenerators() {
        gameBuilder = new GameBuilder(gui, gameController, 2);

        List<ElementGenerator> generators = gameBuilder.getGenerators();

        Assertions.assertEquals(2, generators.size());
        Assertions.assertEquals(AsteroidGenerator.class, generators.get(0).getClass());
        Assertions.assertEquals(CoinGenerator.class, generators.get(1).getClass());
    }

    @Test
    void nextGeneration() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);
        gameBuilder = new GameBuilder(random, gui, gameController, waveFactory, 2);

        Assertions.assertEquals(0, gameBuilder.getNextGeneration());
        gameBuilder.setNextGeneration();
        Assertions.assertEquals(3, gameBuilder.getNextGeneration());
        gameBuilder.setNextGeneration();
        Assertions.assertEquals(6, gameBuilder.getNextGeneration());
    }

    @Test
    void handle() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(gameModel.getEnemySpaceships())
                .thenReturn(Arrays.asList(Mockito.mock(Spaceship.class)));
        List<ElementGenerator> elementGenerators = Arrays.asList(Mockito.mock(ElementGenerator.class));

        gameBuilder = new GameBuilder(random, gui, gameController, waveFactory, 2);
        gameBuilder.setupElementGenerators(elementGenerators);

        gameBuilder.handle(0);
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

        gameBuilder = new GameBuilder(random, gui, gameController, waveFactory, 2);
        gameBuilder.setupElementGenerators(elementGenerators);

        gameBuilder.handle(0);
        gameBuilder.handle(1);

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

        gameBuilder = new GameBuilder(random, gui, gameController, waveFactory, 2);
        gameBuilder.setupElementGenerators(elementGenerators);

        gameBuilder.handle(0);
        gameBuilder.handle(1);
        gameBuilder.handle(2);
        gameBuilder.handle(3);
        gameBuilder.handle(4);

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

        gameBuilder = new GameBuilder(random, gui, gameController, waveFactory, 2);
        gameBuilder.setupElementGenerators(elementGenerators);

        gameBuilder.handle(0);

        Mockito.when(wave.handle(1)).thenReturn(true);
        gameBuilder.handle(1);

        gameBuilder.handle(2);

        Mockito.verify(waveFactory, Mockito.times(2))
                .getNextWave(gameController);
    }
}
