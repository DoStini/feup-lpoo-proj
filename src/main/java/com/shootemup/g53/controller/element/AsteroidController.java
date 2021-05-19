package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;

public class AsteroidController extends MovableElementController implements CollisionController, ElementInterface{
    private Asteroid asteroid;
    public AsteroidController(Asteroid asteroid, MovementStrategy movementStrategy) {
        super(asteroid, movementStrategy);
        this.asteroid = asteroid;
    }


    public Asteroid getAsteroid() {
        return asteroid;
    }

    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    @Override
    public void handleBullet(Bullet bullet) {

    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {

    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {

    }

    @Override
    public void handleCoin(Coin coin) {

    }

    @Override
    public void handle() {
        Position newPosition = move();
        asteroid.setPosition(newPosition);
    }
}
