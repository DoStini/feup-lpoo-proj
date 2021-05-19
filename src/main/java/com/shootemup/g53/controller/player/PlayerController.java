package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController implements CollisionHandlerController, ElementInterface {
    private Spaceship spaceship;
    private FiringStrategy firingStrategy;
    private BulletPoolController bulletPoolController;
    private Gui gui;
    public PlayerController(Spaceship spaceship, Gui gui, BulletPoolController bulletPoolController, FiringStrategy firingStrategy) {
        this.spaceship = spaceship;
        this.gui = gui;
        this.bulletPoolController = bulletPoolController;
        this.firingStrategy = firingStrategy;
    }

    public void fire(Gui gui, BulletPoolController bulletPoolController) {
        firingStrategy.increaseFrame();
        if (gui.isActionActive(Action.SPACE)) {
            firingStrategy.fire(spaceship, bulletPoolController);
        }
    }

    public void setPosition(Position position){
        spaceship.setPosition(position);
    }

    public Position move(Gui gui) {
        int speed = spaceship.getSpeed();
        Position newPosition = spaceship.getPosition();
        if (gui.isActionActive(Action.W)) {
            newPosition = newPosition.getUp(speed);
        }
        if (gui.isActionActive(Action.A)) {
            newPosition = newPosition.getLeft(speed);
        }
        if (gui.isActionActive(Action.S)) {
            newPosition = newPosition.getDown(speed);
        }
        if (gui.isActionActive(Action.D)) {
            newPosition = newPosition.getRight(speed);
        }
        return newPosition;
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleSpaceship(this.spaceship);
    }

    @Override
    public void handleBullet(Bullet bullet) {

    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {

    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {

    }

    @Override
    public void handleCoin(Coin coin) {

    }

    @Override
    public void handle() {
        Position newPosition = move(gui);
        setPosition(newPosition);
        fire(gui,bulletPoolController);
    }
}
