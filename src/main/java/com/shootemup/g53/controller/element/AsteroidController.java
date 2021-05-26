package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;

public class AsteroidController extends MovableElementController implements CollisionHandlerController, ElementInterface{
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
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleAsteroid(this.asteroid);
    }

    @Override
    public void handleShield(Shield shield) {
        asteroid.deactivate();
    }

    @Override
    public void handle(long frame) {
        Position newPosition = move();
        asteroid.setPosition(newPosition);
    }
}
