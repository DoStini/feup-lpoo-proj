package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.ColorOperation;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;


public class SpaceshipController extends MovableElementController implements CollisionHandlerController, ElementInterface {
    private Spaceship spaceship;
    private FiringStrategy firingStrategy;
    private BulletPoolController bulletPoolController;

    public SpaceshipController(Spaceship spaceship, FiringStrategy firingStrategy, MovementStrategy movementStrategy,
                               BulletPoolController bulletPoolController) {
        super(spaceship, movementStrategy);
        this.spaceship = spaceship;
        this.firingStrategy = firingStrategy;
        this.bulletPoolController = bulletPoolController;

    }

    public void fire(BulletPoolController bulletPoolController, long frame){
        firingStrategy.fire(spaceship, this.getSpaceship().getPosition(), bulletPoolController,
                ColorOperation.invertColor(spaceship.getColor()),
                ColliderCategory.ENEMY_BULLET, frame);
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
    public void handleShield(Shield shield) {
        spaceship.setHealth(0);
    }

    @Override
    public void handle(long frame) {
        fire(bulletPoolController,frame);
        Position newPosition = move();

        spaceship.setPosition(newPosition);
    }
}


