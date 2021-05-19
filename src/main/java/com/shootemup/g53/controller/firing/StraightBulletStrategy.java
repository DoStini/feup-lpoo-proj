package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Spaceship;


public class StraightBulletStrategy extends FiringStrategy {
    private MovementStrategy movementStrategy;


    public StraightBulletStrategy(MovementStrategy movementStrategy, int speed, int fireRate) {
        this.movementStrategy = movementStrategy;
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
    }

    @Override
    public void fire(Spaceship spaceship, BulletPoolController bulletPoolController, ColliderCategory category) {
        increaseFrame();
        if (frame > lastFire + fireRate) {
            bulletPoolController.addBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                    "#ff0000", 3, bulletSpeed, this.movementStrategy, category);

            lastFire = frame;
        }
    }
}
