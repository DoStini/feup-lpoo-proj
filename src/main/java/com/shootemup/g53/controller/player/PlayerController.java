package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.observer.EssenceController;
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
    private EssenceController essenceController = new EssenceController();
    protected MovementStrategy leftStrategy;
    protected MovementStrategy rightStrategy;
    protected MovementStrategy upStrategy;
    protected MovementStrategy downStrategy;
    private Gui gui;
    int cooldown;
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
        Position newPosition = new Position(player.getPosition().getX(),player.getPosition().getY());
        Position oldPosition = newPosition;

        if (gui.isActionActive(Action.W)) {
            newPosition = upStrategy.move(newPosition, player.getSpeed());

            if(!insideBounds(newPosition)) {
                newPosition = oldPosition;
            } else {
                oldPosition = newPosition;
            }
        }
        if (gui.isActionActive(Action.A)) {
            newPosition = leftStrategy.move(newPosition, player.getSpeed());

            if(!insideBounds(newPosition)) {
                newPosition = oldPosition;
            } else {
                oldPosition = newPosition;
            }
        }
        if (gui.isActionActive(Action.S)) {
            newPosition = downStrategy.move(newPosition, player.getSpeed());

            if(!insideBounds(newPosition)) {
                newPosition = oldPosition;
            } else {
                oldPosition = newPosition;
            }
        }
        if (gui.isActionActive(Action.D)) {
            newPosition = rightStrategy.move(newPosition, player.getSpeed());

            if(!insideBounds(newPosition)) {
                newPosition = oldPosition;
            }
        }

        if (insideBounds(newPosition))
            return newPosition;

        return player.getPosition();
    }

    public boolean insideBounds(Position pos) {
        if(pos == null){
            return false;
        }
        return pos.getX() > 0 && pos.getX() < gui.getWidth() - 10  &&
                pos.getY() < gui.getHeight() && pos.getY() >= 0;
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
        if(cooldown == 0){
            lifeController.setLifeToRemove(5);
            lifeController.notifyObservers();
            this.player.setHealth(this.player.getHealth()-5);
            cooldown = 80;
            player.setColor("#FFFFFF");
        }

    }


    public void handleEssence(Essence essence) {
        this.player.addEssence(essence.getValue());
        essenceController.setAmount(essence.getValue());
        essenceController.notifyObservers();
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
        if(cooldown > 0) cooldown--;
        if(cooldown == 0) player.setColor("#aae253");
    }
    public void handleShield(Shield shield) {


    }


    private void usePowerups(Gui gui) {
        if (gui.isActionActive(Action.POWER_1))
            powerupController.spawnShield(player);
        if (gui.isActionActive(Action.POWER_2))
            powerupController.healthBoost(player);
    }

    public LifeController getLifeController() {
        return lifeController;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public EssenceController getEssenceController() {
        return essenceController;
    }

    public PowerupController getPowerupController() {
        return powerupController;
    }
}
