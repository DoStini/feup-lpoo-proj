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
    private GameModelBuilder gameModelBuilder;

    @BeforeEach
    void setup() {
        gameController = Mockito.mock(GameController.class);

        gameModelBuilder = new GameModelBuilder();
        gameModelBuilder.setWidth(20);
        gameModelBuilder.setHeight(20);
    }

    @Test
    void constructor() {
        Assertions.assertEquals(20, gameModelBuilder.gameModel.getWidth());
        Assertions.assertEquals(20, gameModelBuilder.gameModel.getHeight());
    }

    @Test
    void setupPlayer() {
        Player spaceship = new Player(new Position(20, 35), 3, 1, 20, "#aae253", 3,10);

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

        gameModelBuilder.setupPlayer();

        GameModel gameModel = gameModelBuilder.getGameModel();

        Assertions.assertEquals(spaceship, gameModel.getPlayer());
        Assertions.assertEquals(playerCollider, gameModel.getColliders().get(0));
        Assertions.assertEquals(pickupCollider, gameModel.getColliders().get(1));
    }

    @Test
    void setupElements() {
        gameModelBuilder.setupElements();
        GameModel gameModel = gameModelBuilder.getGameModel();

        Assertions.assertEquals(0, gameModel.getCoins().size());
        Assertions.assertTrue(gameModel.getCoins() instanceof ArrayList);

        Assertions.assertEquals(0, gameModel.getBulletList().size());
        Assertions.assertTrue(gameModel.getBulletList() instanceof ArrayList);

        Assertions.assertEquals(0, gameModel.getEnemySpaceships().size());
        Assertions.assertTrue(gameModel.getEnemySpaceships() instanceof ArrayList);

        Assertions.assertEquals(0, gameModel.getAsteroids().size());
        Assertions.assertTrue(gameModel.getAsteroids() instanceof ArrayList);

        Assertions.assertEquals(0, gameModel.getShieldList().size());
        Assertions.assertTrue(gameModel.getShieldList() instanceof ArrayList);

        Assertions.assertEquals(0, gameModel.getEssenceList().size());
        Assertions.assertTrue(gameModel.getEssenceList() instanceof ArrayList);
    }

    @Test
    void setupBackground() {
        gameModelBuilder.setupBackground();
        GameModel gameModel = gameModelBuilder.getGameModel();

//        Mockito.verify(gameController, Mockito.times(1))
//                .setBackgroundController(Mockito.any(BackgroundController.class));

        Assertions.assertEquals(25, gameModel.getBackground().getMinStars());
        Assertions.assertEquals(30, gameModel.getBackground().getMaxStars());
    }
}
