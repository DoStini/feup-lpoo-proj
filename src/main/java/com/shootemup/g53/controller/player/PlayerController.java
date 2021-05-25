package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.*;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController implements CollisionHandlerController, ElementInterface {
    private Player player;
    private FiringStrategy firingStrategy;
    private BulletPoolController bulletPoolController;
    private Gui gui;
    public PlayerController(Player player, Gui gui, BulletPoolController bulletPoolController, FiringStrategy firingStrategy) {
        this.player = player;
        this.gui = gui;
        this.bulletPoolController = bulletPoolController;
        this.firingStrategy = firingStrategy;
    }

    public void fire(Gui gui, BulletPoolController bulletPoolController) {
        firingStrategy.increaseFrame();
        if (gui.isActionActive(Action.SPACE)) {
            firingStrategy.fire(player, player.getPosition().getUp(player.getHeight()), bulletPoolController, "#ff0000", ColliderCategory.PLAYER_BULLET);
        }
    }

    public void setPosition(Position position){
        player.setPosition(position);
    }

    public Position move(Gui gui) {
        int speed = player.getSpeed();
        Position newPosition = player.getPosition();
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
        otherController.handlePlayer(this.player);
    }

    @Override
    public void handleBullet(Bullet bullet) {
        this.player.setHealth(this.player.getHealth()-bullet.getDamage());
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {
        this.player.setHealth(this.player.getHealth()-5);
        
    }

    @Override
    public void handlePlayer(Player player) {


    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        player.setHealth(0);
    }

    @Override
    public void handleCoin(Coin coin) {
        // add coin to inv
    }

    @Override
    public void handle() {
        Position newPosition = move(gui);
        setPosition(newPosition);
        fire(gui,bulletPoolController);
    }
}
