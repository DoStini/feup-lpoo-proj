package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MoveUpwardsMovement;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Direction;

public class StraightBulletStrategy implements FiringStrategy {
    private Direction direction;
    private int bulletSpeed;
    private int fireRate;
    private int lastFire = 0;
    private int frame = 0;

    public StraightBulletStrategy(Direction direction, int speed, int fireRate) {
        this.direction = direction;
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
            if(direction == Direction.DOWN){
                bulletPoolController.addBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                        "#ff0000", 3, bulletSpeed, new FallDownMovement());
            }
            else bulletPoolController.addBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                    "#ff0000", 3, bulletSpeed,new MoveUpwardsMovement());

            lastFire = frame;

        }

    }
}
