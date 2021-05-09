package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Spaceship;


public class StraightBulletStrategy implements FiringStrategy {
    private MovementStrategy movementStrategy;
    private int bulletSpeed;
    private int fireRate;
    private int lastFire = 0;
    private int frame = 0;

    public StraightBulletStrategy(MovementStrategy movementStrategy, int speed, int fireRate) {
        this.movementStrategy = movementStrategy;
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
    }

    @Override
    public void increaseFrame(){
        frame++;
    }

    @Override
    public void fire(Spaceship spaceship, BulletPoolController bulletPoolController) {
        increaseFrame();
        if (frame > lastFire + fireRate) {
            bulletPoolController.addBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                        "#ff0000", 3, bulletSpeed, this.movementStrategy);

            lastFire = frame;

        }

    }
}