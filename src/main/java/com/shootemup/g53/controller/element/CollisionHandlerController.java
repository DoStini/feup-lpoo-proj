package com.shootemup.g53.controller.element;

import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;

public interface CollisionHandlerController {
    void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController);
    void handleBullet(Bullet bullet);
    void handleSpaceship(Spaceship spaceship);
    void handlePlayer(Player player);
    void handleAsteroid(Asteroid asteroid);
    void handleCoin(Coin coin);
}
