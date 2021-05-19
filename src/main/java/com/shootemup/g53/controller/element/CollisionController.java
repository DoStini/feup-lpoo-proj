package com.shootemup.g53.controller.element;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;

public interface CollisionController {
    void handleBullet(Bullet bullet);
    void handleSpaceship(Spaceship spaceship);
    void handleAsteroid(Asteroid asteroid);
    void handleCoin(Coin coin);
}
