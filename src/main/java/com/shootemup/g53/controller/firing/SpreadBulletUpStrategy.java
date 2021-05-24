package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;


public class SpreadBulletUpStrategy extends FiringStrategy {
    MovementStrategy middleStrategy;
    CompositeMovement leftMovement;
    CompositeMovement rightMovement;

    public SpreadBulletUpStrategy(int speed, int fireRate) {
        super();
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
        middleStrategy = new MoveUpwardsMovement();
        leftMovement = new CompositeMovement();
        leftMovement.addMovement(new MoveUpwardsMovement());
        leftMovement.addMovement(new LeftMovement());
        rightMovement = new CompositeMovement();
        rightMovement.addMovement(new MoveUpwardsMovement());
        rightMovement.addMovement(new RightMovement());
    }


    @Override
    public void createBullets(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color) {
        bulletPoolController.addBullet(position.getX(), position.getY(),
                color, 3, bulletSpeed, middleStrategy);
        bulletPoolController.addBullet(position.getX()-3, position.getY(),
                color, 3, bulletSpeed/2, leftMovement);
        bulletPoolController.addBullet(position.getX()+3, position.getY(),
                color, 3, bulletSpeed/2, rightMovement);
    }
}
