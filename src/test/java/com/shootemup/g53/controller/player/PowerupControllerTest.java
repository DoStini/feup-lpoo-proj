package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.ShieldController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Shield;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PowerupControllerTest {

    private Player player;
    private GameController gameController;
    private GameModel gameModel;
    private PowerupController controller;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        gameController = Mockito.mock(GameController.class);
        gameModel = Mockito.spy(new GameModel(20,20));
        gameModel.setShields(new ArrayList<>());
        gameModel.setColliders(new ArrayList<>());
        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);

        controller = new PowerupController(gameController);
        player.setPosition(new Position(5, 5));
        player.setColor("#aaaaaa");
    }

    @Test
    void NotEnoughEssence() {
        player.setEssence(3);
        Assertions.assertFalse(controller.spawnShield(player));
        Assertions.assertEquals(3, player.getEssence());
    }

    @Test
    void sameEssence() {
        player.setEssence(5);
        Assertions.assertTrue(controller.spawnShield(player));
        Assertions.assertEquals(0, player.getEssence());
    }

    @Test
    void oneTimeEssence() {
        player.setEssence(6);
        Assertions.assertTrue(controller.spawnShield(player));
        Assertions.assertEquals(1, player.getEssence());
        Assertions.assertFalse(controller.spawnShield(player));
        Assertions.assertEquals(1, player.getEssence());

        Shield shield = new Shield(player.getPosition().getUp(2), player.getColor(), 5, 5);

        BodyCollider bodyCollider = gameModel.getColliders().get(0);
        Assertions.assertEquals(5, bodyCollider.getBoundingBox().getWidth());
        Assertions.assertEquals(2, bodyCollider.getBoundingBox().getHeight());
        Assertions.assertEquals(new Position(-shield.getWidth()/2, 0),
                bodyCollider.getBoundingBox().getTopLeft());

        Assertions.assertEquals(ColliderCategory.SHIELD, bodyCollider.getCategory());
        Assertions.assertEquals(-1, bodyCollider.getCategoryMask());

        Mockito.verify(gameModel, Mockito.times(1)).addShield(shield);
        Mockito.verify(gameModel, Mockito.times(1)).addCollider(Mockito.any(BodyCollider.class));
        Mockito.verify(gameController,  Mockito.times(1))
                .addToControllerMap(Mockito.eq(shield), Mockito.any(ShieldController.class));
        Mockito.verify(gameController,  Mockito.times(1))
                .addToCollisionMap(Mockito.eq(shield), Mockito.any(ShieldController.class));

    }
}