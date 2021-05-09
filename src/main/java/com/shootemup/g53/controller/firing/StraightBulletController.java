package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MoveUpwardsMovement;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Direction;

public class StraightBulletController implements FiringController{
    private Direction direction;
    private int bulletSpeed;

    public StraightBulletController(Direction direction, int speed) {
        this.direction = direction;
        this.bulletSpeed = speed;
    }

    @Override
    public void fire(Spaceship spaceship, BulletPoolController bulletPoolController) {
        spaceship.increaseFrame();
        if (spaceship.getFrame() > spaceship.getLastFire() + spaceship.getFireRate()) {
            if(direction == Direction.DOWN){
                bulletPoolController.addBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                        "#ff0000", 3, bulletSpeed, new FallDownMovement());
            }
            else bulletPoolController.addBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                    "#ff0000", 3, bulletSpeed,new MoveUpwardsMovement());

            spaceship.setLastFire(spaceship.getFrame());

        }

    }
}
