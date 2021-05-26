package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.observer.LifeController;
import com.shootemup.g53.controller.observer.ScoreController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.element.*;

import com.shootemup.g53.model.util.ColorOperation;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController implements CollisionHandlerController, ElementInterface {
    private Player player;
    private FiringStrategy firingStrategy;
    private BulletPoolController bulletPoolController;
    private LifeController lifeController = new LifeController();
    private ScoreController scoreController = new ScoreController();
    protected MovementStrategy leftStrategy;
    protected MovementStrategy rightStrategy;
    protected MovementStrategy upStrategy;
    protected MovementStrategy downStrategy;
    private Gui gui;

    private PowerupController powerupController;

    public PlayerController(Player player, Gui gui, BulletPoolController bulletPoolController,
                            PowerupController powerupController, FiringStrategy firingStrategy) {
        this.player = player;
        this.gui = gui;
        this.bulletPoolController = bulletPoolController;
        this.firingStrategy = firingStrategy;


        this.powerupController = powerupController;

        this.leftStrategy = new LeftMovement();
        this.rightStrategy = new RightMovement();
        this.upStrategy = new MoveUpwardsMovement();
        this.downStrategy = new FallDownMovement();
    }

    public void setDownStrategy(MovementStrategy downStrategy) {
        this.downStrategy = downStrategy;
    }

    public void setLeftStrategy(MovementStrategy leftStrategy) {
        this.leftStrategy = leftStrategy;
    }

    public void setRightStrategy(MovementStrategy rightStrategy) {
        this.rightStrategy = rightStrategy;
    }

    public void setUpStrategy(MovementStrategy upStrategy) {
        this.upStrategy = upStrategy;
    }

    public void fire(Gui gui, BulletPoolController bulletPoolController, long frame) {
        if (gui.isActionActive(Action.SPACE)) {
            firingStrategy.fire(player, player.getPosition().getUp(player.getHeight()), bulletPoolController,
                    ColorOperation.invertColor(player.getColor()),
                    ColliderCategory.PLAYER_BULLET, frame);

        }
    }

    public void setPosition(Position position){
        player.setPosition(position);
    }

    public Position move(Gui gui) {
        Position newPosition = player.getPosition();

        if (gui.isActionActive(Action.W)) {
            newPosition = upStrategy.move(newPosition, player.getSpeed());
        }
        if (gui.isActionActive(Action.A)) {
            newPosition = leftStrategy.move(newPosition, player.getSpeed());
        }
        if (gui.isActionActive(Action.S)) {
            newPosition = downStrategy.move(newPosition, player.getSpeed());
        }
        if (gui.isActionActive(Action.D)) {
            newPosition = rightStrategy.move(newPosition, player.getSpeed());
        }
        return newPosition;
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handlePlayer(this.player);
    }

    @Override
    public void handleBullet(Bullet bullet) {

        lifeController.setLifeToRemove(bullet.getDamage());
        lifeController.notifyObservers();
        this.player.setHealth(this.player.getHealth()-bullet.getDamage());
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {
        lifeController.setLifeToRemove(5);
        lifeController.notifyObservers();
        this.player.setHealth(this.player.getHealth()-5);
        
    }


    public void handleEssence(Essence essence) {
        this.player.addEssence(essence.getValue());
        System.out.println(player.getEssence());
    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        player.setHealth(0);
    }

    @Override
    public void handleCoin(Coin coin) {
        scoreController.notifyObservers();
    }

    @Override
    public void handle(long frame) {
        Position newPosition = move(gui);
        setPosition(newPosition);
        fire(gui, bulletPoolController, frame);
        usePowerups(gui);
    }
    public void handleShield(Shield shield) {
    }

   

    private void usePowerups(Gui gui) {
        if (gui.isActionActive(Action.POWER_1))
            powerupController.spawnShield(player);
    }

    public LifeController getLifeController() {
        return lifeController;
    }


    public ScoreController getScoreController() {
        return scoreController;
    }
}
