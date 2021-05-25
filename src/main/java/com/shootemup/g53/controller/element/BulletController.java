package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;

public class BulletController extends MovableElementController implements CollisionHandlerController, ElementInterface{
    private Bullet bullet;
    public BulletController(Bullet bullet, MovementStrategy movementStrategy) {
        super(bullet, movementStrategy);
        this.bullet = bullet;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public void setPosition(Position position){
        bullet.setPosition(position);
    }


    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleBullet(this.bullet);
    }

    @Override
    public void handleBullet(Bullet bullet) {
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {
        bullet.deactivate();
    }

    @Override
    public void handlePlayer(Player player) {
        bullet.deactivate();
    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        bullet.deactivate();
    }

    @Override
    public void handleCoin(Coin coin) {

    }

    @Override
    public void handleShield(Shield shield) {
        bullet.deactivate();
    }

    @Override
    public void handle() {
         Position newPosition = move();
         bullet.setPosition(newPosition);
         //if(outOfBounds) bullet.deactivate();
    }
}
