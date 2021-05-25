package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;


public class SpreadAllStrategy extends FiringStrategy {
    MoveUpwardsMovement upwardsMovement;
    CompositeMovement leftUpMovement;
    CompositeMovement rightUpMovement;
    DiagonalDownLeftMovement leftDownMovement;
    DiagonalDownRightMovement rightDownMovement;
    LeftMovement leftMovement;
    RightMovement rightMovement;
    FallDownMovement downMovement;



    public SpreadAllStrategy(double speed, int fireRate) {
        super();
        this.bulletSpeed = speed;
        this.fireRate = fireRate;
        upwardsMovement = new MoveUpwardsMovement();

        leftUpMovement = new CompositeMovement();
        leftUpMovement.addMovement(new MoveUpwardsMovement());
        leftUpMovement.addMovement(new LeftMovement());

        rightUpMovement = new CompositeMovement();
        rightUpMovement.addMovement(new MoveUpwardsMovement());
        rightUpMovement.addMovement(new RightMovement());

        downMovement = new FallDownMovement();

        leftDownMovement = new DiagonalDownLeftMovement();
        rightDownMovement = new DiagonalDownRightMovement();

        leftMovement = new LeftMovement();
        rightMovement = new RightMovement();
    }


    @Override
    public void createBullets(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color, ColliderCategory category) {
        bulletPoolController.addBullet(position.getX(), position.getY(),
                color, 3, bulletSpeed, upwardsMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX()-3, position.getY(),
                color, 3, bulletSpeed/2, leftDownMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX()+3, position.getY(),
                color, 3, bulletSpeed/2, rightDownMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX()-3, position.getY(),
                color, 3, bulletSpeed/2, leftUpMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX()+3, position.getY(),
                color, 3, bulletSpeed/2, rightUpMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX(), position.getY(),
                color, 3, bulletSpeed, downMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX()-3, position.getY(),
                color, 3, bulletSpeed/2, leftMovement.cloneStrategy(), category);
        bulletPoolController.addBullet(position.getX()+3, position.getY(),
                color, 3, bulletSpeed/2, rightMovement.cloneStrategy(), category);
    }
}
