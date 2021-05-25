package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.DiagonalDownLeftMovement;
import com.shootemup.g53.controller.movement.DiagonalDownRightMovement;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;


public class SpreadBulletDownStrategy extends FiringStrategy {
    MovementStrategy middleStrategy;
    MovementStrategy leftMovement;
    MovementStrategy rightMovement;

    public SpreadBulletDownStrategy(int speed, int fireRate) {
        super();
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
        middleStrategy = new FallDownMovement();
        leftMovement = new DiagonalDownLeftMovement();
        rightMovement = new DiagonalDownRightMovement();
    }


    @Override
    public void createBullets(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color, ColliderCategory category) {
        bulletPoolController.addBullet(position.getX(), position.getY(),
                color, 3, bulletSpeed, middleStrategy, category);
        bulletPoolController.addBullet(position.getX()-3, position.getY(),
                color, 3, bulletSpeed/2, leftMovement, category);
        bulletPoolController.addBullet(position.getX()+3, position.getY(),
                color, 3, bulletSpeed/2, rightMovement, category);
    }
}
