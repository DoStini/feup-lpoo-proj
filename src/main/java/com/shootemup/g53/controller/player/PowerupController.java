package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.ShieldController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.observer.EssenceController;
import com.shootemup.g53.controller.observer.LifeController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeCollider;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Shield;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

public class PowerupController {
    private GameController gameController;
    private GameModel gameModel;
    private EssenceController essenceController = new EssenceController();
    private final int shieldEssenceCost = 5;
    private final int healthCost = 8;
    private final int healthBoost = 10;

    private LifeController lifeController = new LifeController();

    public PowerupController(GameController gameController) {
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
    }

    private boolean consumeEssence(Player player, int cost) {
        if(!player.removeEssence(cost))
            return false;

        essenceController.setAmount(-cost);
        essenceController.notifyObservers();
        return true;
    }

    public boolean spawnShield(Player player) {
        if(!consumeEssence(player, shieldEssenceCost))
            return false;

        generateShield(player);

        return true;
    }

    public boolean healthBoost(Player player) {
        if (player.getHealth() == player.getMaxHealth() || !consumeEssence(player, healthCost))
            return false;

        int health = player.getHealth();

        player.setHealth(health + healthBoost);
        lifeController.setLifeToRemove(-(player.getHealth() - health));
        lifeController.notifyObservers();
        return true;
    }

    private void generateShield(Player player) {
        Shield shield = new Shield(player.getPosition().getUp(2), player.getColor(), 5, 10);
        ShieldController shieldController = new ShieldController(shield);
        BodyCollider collider = new LineCompositeFactory()
                .createFromSquare(shield, new Position(-shield.getWidth()/2, 0), shield.getWidth(), 2);
        collider.setCategory(ColliderCategory.SHIELD);

        gameModel.addShield(shield);
        gameModel.addCollider(collider);
        gameController.addToControllerMap(shield, shieldController);
        gameController.addToCollisionMap(shield, shieldController);
    }

    public int getShieldEssenceCost() {
        return shieldEssenceCost;
    }

    public int getHealthCost() {
        return healthCost;
    }

    public LifeController getLifeController() {
        return lifeController;
    }

    public EssenceController getEssenceController() {
        return essenceController;
    }
}
