package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.element.BackgroundController;
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

class GameModelBuilderTest {

    private GameController gameController;
    private GameModel gameModel;
    private WaveFactory waveFactory;
    private Wave wave;
    private Gui gui;

    private GameModelBuilder gameModelBuilder;
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
        gameModelBuilder = new GameModelBuilder();

        Player spaceship = new Player(new Position(20, 35), 3, 1, 20, "#aae253", 3,5);

        BodyCollider playerCollider =
                new LineCompositeFactory().createFromIsoscelesTriangle(spaceship, new Position(0, 0), 1);
        playerCollider.setCategory(ColliderCategory.PLAYER);
        playerCollider.setCategoryMask(
                (short) (ColliderCategory.ENEMY.getBits() |
                        ColliderCategory.ENEMY_BULLET.getBits()));

        BodyCollider pickupCollider =
                new LineCompositeFactory().createFromSquare(spaceship, new Position(-spaceship.getHeight(), 0), spaceship.getHeight()*2, spaceship.getHeight());
        pickupCollider.setCategory(ColliderCategory.PLAYER);
        pickupCollider.setCategoryMask(
                ColliderCategory.PICKUP.getBits());

        List<BodyCollider> colliders = new ArrayList<>();

        colliders.add(playerCollider);
        colliders.add(pickupCollider);

        Mockito.verify(gameModel, Mockito.times(1))
                .setPlayer(spaceship);
        Assertions.assertEquals(playerCollider, gameModel.getColliders().get(0));
        Assertions.assertEquals(pickupCollider, gameModel.getColliders().get(1));

        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.eq(spaceship), Mockito.any(PlayerController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.eq(spaceship), Mockito.any(PlayerController.class));
    }

    @Test
    void setupElements() {
        gameModelBuilder = new GameModelBuilder();

        Mockito.verify(gameModel, Mockito.times(1)).setCoins(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setBulletList(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setEnemySpaceships(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setAsteroids(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setShields(new ArrayList<>());
        Mockito.verify(gameModel, Mockito.times(1)).setEssences(new ArrayList<>());

    }

    @Test
    void setupBackground() {
        gameModelBuilder = new GameModelBuilder();

        Mockito.verify(gameController, Mockito.times(1))
                .setBackgroundController(Mockito.any(BackgroundController.class));

        Mockito.verify(gameModel, Mockito.times(1))
                .setBackground(Mockito.any(Background.class));

        Assertions.assertEquals(25, gameModel.getBackground().getMinStars());
        Assertions.assertEquals(30, gameModel.getBackground().getMaxStars());
    }
}
