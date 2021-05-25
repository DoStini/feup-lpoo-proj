package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;


public class MovingBulletStrategy extends FiringStrategy {
    private MovementStrategy movementStrategy;


    public MovingBulletStrategy(MovementStrategy movementStrategy, double speed, int fireRate) {
        super();
        this.movementStrategy = movementStrategy;
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
    }


    @Override
    public void createBullets(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color, ColliderCategory category) {
        bulletPoolController.addBullet(position.getX(), position.getY(),
                    color, 3, bulletSpeed, spaceship.getBulletDamage(), this.movementStrategy, category);

    }
}
