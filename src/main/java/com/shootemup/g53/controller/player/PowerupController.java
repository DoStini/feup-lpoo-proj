package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.ShieldController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.observer.EssenceController;
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
    private Player player;
    private EssenceController essenceController = new EssenceController();
    private int shieldEssenceCost = 5;
    private int healthCost = 3;
    public PowerupController(GameController gameController) {
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
    }

    public boolean spawnShield(Player player) {
        if (player.getEssence() < shieldEssenceCost)
            return false;
        essenceController.setAmount(-shieldEssenceCost);
        essenceController.notifyObservers();
        player.removeEssence(shieldEssenceCost);

        generateShield(player);

        return true;
    }

    private void generateShield(Player player) {
        Shield shield = new Shield(player.getPosition().getUp(2), player.getColor(), 5, 5);
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

    public EssenceController getEssenceController() {
        return essenceController;
    }
}
