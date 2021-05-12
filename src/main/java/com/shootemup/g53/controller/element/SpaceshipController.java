package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;


public class SpaceshipController extends MovableElementController implements CollisionController, ElementInterface {
    private Spaceship spaceship;
    private FiringStrategy firingStrategy;
    private BulletPoolController bulletPoolController;

    public SpaceshipController(Spaceship spaceship, FiringStrategy firingStrategy, MovementStrategy movementStrategy, BulletPoolController bulletPoolController) {
        super(spaceship, movementStrategy);
        this.spaceship = spaceship;
        this.firingStrategy = firingStrategy;
        this.bulletPoolController = bulletPoolController;

    }

    public void fire(BulletPoolController bulletPoolController){
        firingStrategy.fire(spaceship, bulletPoolController);
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void handleBullet(Bullet bullet) {
        spaceship.setHealth(spaceship.getHealth() - 1);
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {

    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        spaceship.setHealth(0);
    }

    @Override
    public void handleCoin(Coin coin) {
        //adicionar score ao jogador ?
    }

    @Override
    public void handle() {
        fire(bulletPoolController);
        Position newPosition = move();

        /*
        if(outOfBounds || positionIsInvalid){
            handleFailedMovement();
            newPosition = move();
        }
        */

        spaceship.setPosition(newPosition);
    }
}


