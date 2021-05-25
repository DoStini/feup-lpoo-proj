package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;


public class SpaceshipController extends MovableElementController implements CollisionHandlerController, ElementInterface {
    private Spaceship spaceship;
    private FiringStrategy firingStrategy;
    private BulletPoolController bulletPoolController;

    public SpaceshipController(Spaceship spaceship, FiringStrategy firingStrategy, MovementStrategy movementStrategy, BulletPoolController bulletPoolController) {
        super(spaceship, movementStrategy);
        this.spaceship = spaceship;
        this.firingStrategy = firingStrategy;
        this.bulletPoolController = bulletPoolController;

    }

    public void fire(BulletPoolController bulletPoolController){
        firingStrategy.fire(spaceship, this.getSpaceship().getPosition(), bulletPoolController, "#ffff00", ColliderCategory.ENEMY_BULLET);
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleSpaceship(this.spaceship);
    }

    @Override
    public void handleBullet(Bullet bullet) {
        spaceship.setHealth(spaceship.getHealth() - bullet.getDamage());
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {

    }

    @Override
    public void handlePlayer(Player player) {

    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        spaceship.setHealth(0);
    }

    @Override
    public void handleCoin(Coin coin) {
    }

    @Override
    public void handle() {
        fire(bulletPoolController);
        Position newPosition = move();


        spaceship.setPosition(newPosition);
    }
}


