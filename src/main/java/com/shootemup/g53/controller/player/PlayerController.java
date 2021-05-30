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
    private PlayerState currState;
    protected MovementStrategy leftStrategy;
    protected MovementStrategy rightStrategy;
    protected MovementStrategy upStrategy;
    protected MovementStrategy downStrategy;
    private Gui gui;
    private String normalPlayerColor = "#aae253";
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
        currState = new NormalState( this);
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

    public void setPosition(Position position){
        player.setPosition(position);
    }

    public Position move() {
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
        this.player.setHealth(this.player.getHealth()-bullet.getDamage());
        getLifeController().setLife(player.getHealth());
        getLifeController().notifyObservers();
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {
        getCurrState().handleSpaceship(spaceship);
    }


    public void handleEssence(Essence essence) {
        this.player.addEssence(essence.getValue());
        getEssenceController().setAmount(player.getEssence());
        getEssenceController().notifyObservers();
    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {
        player.setHealth(0);
    }

    @Override
    public void handleCoin(Coin coin) {
        getScoreController().notifyObservers();
    }

    @Override
    public void handle(long frame) {
        getCurrState().handle(frame);
    }
    public void handleShield(Shield shield) {

    }


    public void usePowerups() {
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


    public void setColor(String color){
        this.player.setColor(color);
    }
  

    public Player getPlayer() {
        return player;
    }
    public void changeState(PlayerState playerState){
        this.currState = playerState;
    }

    public String getNormalPlayerColor() {
        return normalPlayerColor;
    }

    public FiringStrategy getFiringStrategy() {
        return firingStrategy;
    }


    public BulletPoolController getBulletPoolController() {
        return bulletPoolController;
    }

    public Gui getGui() {
        return gui;
    }

    public PlayerState getCurrState() {
        return currState;
    }

    public void setLifeController(LifeController lifeController) {
        this.lifeController = lifeController;
    }

    public void setScoreController(ScoreController scoreController) {
        this.scoreController = scoreController;
    }

    public void setEssenceController(EssenceController essenceController) {
        this.essenceController = essenceController;
    }
    
}
