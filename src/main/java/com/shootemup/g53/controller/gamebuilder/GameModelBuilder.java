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
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.*;

public class GameModelBuilder {
    private final Gui gui;
    private GameModel gameModel;

    public GameModelBuilder(Gui gui) {
        this.gui = gui;
        this.gameModel = new GameModel(50, 50);
    }

    protected void setHeight(int height) {
        this.gameModel.setHeight(height);
    }

    protected void setWidth(int width) {
        this.gameModel.setWidth(width);
    }

    protected void setupPlayer() {
        Player spaceship = new Player(new Position(20, 35), 3, 1, 20, "#aae253",
                3, 10);
        gameModel.setPlayer(spaceship);

        List<BodyCollider> colliders = new ArrayList<>();

        BodyCollider playerCollider =
                new LineCompositeFactory().createFromIsoscelesTriangle(spaceship, new Position(0, 0), spaceship.getHitHeight());
        playerCollider.setCategory(ColliderCategory.PLAYER);
        playerCollider.setCategoryMask(
                (short) (ColliderCategory.ENEMY.getBits() |
                         ColliderCategory.ENEMY_BULLET.getBits()));

        BodyCollider pickupCollider =
                new LineCompositeFactory().createFromSquare(spaceship, new Position(-spaceship.getHeight(), 0), spaceship.getHeight()*2, spaceship.getHeight());
        pickupCollider.setCategory(ColliderCategory.PLAYER);
        pickupCollider.setCategoryMask(
                ColliderCategory.PICKUP.getBits());

        colliders.add(playerCollider);
        colliders.add(pickupCollider);
        gameModel.setColliders(colliders);
    }

    protected void setupBackground() {
        Background background = new Background(25, 30);

        gameModel.setBackground(background);
    }

    protected void setupElements() {
        gameModel.setCoins(new ArrayList<>());
        gameModel.setBulletList(new ArrayList<>());
        gameModel.setEnemySpaceships(new ArrayList<>());
        gameModel.setAsteroids(new ArrayList<>());
        gameModel.setShields(new ArrayList<>());
        gameModel.setEssences(new ArrayList<>());
    }

    public GameModel getGameModel() {
        return this.gameModel;
    }
}
