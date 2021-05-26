package com.shootemup.g53.controller.element;

import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;

public interface CollisionHandlerController {
    void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController);
    default void handleBullet(Bullet bullet){};
    default void handleSpaceship(Spaceship spaceship){};
    default void handlePlayer(Player player){};
    default void handleAsteroid(Asteroid asteroid){};
    default void handleCoin(Coin coin){};
    default void handleShield(Shield shield){};
    default void handleEssence(Essence essence){};
}
