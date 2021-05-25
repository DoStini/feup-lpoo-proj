package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;


public class MultipleMovingBulletStrategy extends FiringStrategy {
    private MovementStrategy movementStrategy;
    private int bullets;
    private int slope;

    public MultipleMovingBulletStrategy(MovementStrategy movementStrategy, int bullets, int slope, double speed, int fireRate) {
        super();
        this.movementStrategy = movementStrategy;
        this.bullets = bullets;
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
        this.slope = slope;
    }


    @Override
    public void createBullets(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color, ColliderCategory category) {
        int middleBullet = this.bullets/2;
        boolean odd = bullets % 2 != 0;

        if(odd) {
            for(int bullet = 0; bullet < this.bullets; bullet++) {
                bulletPoolController.addBullet(position.getX()+(3)*(middleBullet-bullet), position.getY()+slope*2*(Math.abs(middleBullet-bullet)),
                        color, 3, bulletSpeed, this.movementStrategy.cloneStrategy(), category);
            }
        } else {
            for(int bullet = 0; bullet < this.bullets; bullet++) {
                int yDiff;

                if(bullet < middleBullet ) yDiff = Math.abs(middleBullet - bullet - 1);
                else yDiff = Math.abs(middleBullet-bullet);

                bulletPoolController.addBullet(position.getX()+(2)*(middleBullet-bullet)-1, position.getY()+slope*2*yDiff,
                        color, 3, bulletSpeed, this.movementStrategy.cloneStrategy(), category);
            }
        }

    }
}
