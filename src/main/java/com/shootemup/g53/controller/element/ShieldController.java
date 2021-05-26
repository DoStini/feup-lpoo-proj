package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.collision.CollisionController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;

public class ShieldController implements ElementInterface, CollisionHandlerController {

    private Shield shield;

    public ShieldController(Shield shield) {
        this.shield = shield;
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleShield(shield);
    }

    @Override
    public void handleBullet(Bullet bullet) {
        shield.setStrength(shield.getStrength() - 1);
    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        shield.setStrength(shield.getStrength() - asteroid.getRadius());
    }

    @Override
    public void handle() {

    }
}
