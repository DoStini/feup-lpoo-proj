package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.gamebuilder.GameBuilder;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.element.AIFiringController;
import com.shootemup.g53.controller.element.MovableElementController;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;


public class GameController extends GenericController {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;
    private PlayerController playerController;
    private AIFiringController firingController;
    private MovableElementController movableElementController;

    public GameController(GameModel gameModel) {
        this(gameModel, new BulletPoolController(gameModel, 30));
    }

    public GameController(GameModel gameModel, BulletPoolController bulletPoolController) {
        this.gameModel = gameModel;
        this.bulletPoolController = bulletPoolController;
        firingController = new AIFiringController(null);
        movableElementController = new MovableElementController(null);
    }

    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.ESC)){
            gameModel.setGameFinished(true);
        }
    }

    public boolean insideBounds(Position pos) {
        if(pos == null){
            return false;
        }
        return pos.getX()> 0 && pos.getX() < gameModel.getWidth() &&
                pos.getY() > 0 && pos.getY() < gameModel.getHeight();
    }

    public void handlePlayerInput(Gui gui) {
        Position desiredPosition = playerController.move(gui);
        playerController.fire(gui, bulletPoolController);

        if(insideBounds(desiredPosition)){
            playerController.setPosition(desiredPosition);
        }
    }

    public void handleBullets(){
        for(Bullet bullet: gameModel.getBulletList()){
            movableElementController.setMovableElement(bullet);
            Position newBulletPos = movableElementController.move();
            if(insideBounds(newBulletPos)){
                bullet.setPosition(newBulletPos);
            }else{
                bulletPoolController.restoreBullet(bullet);
            }
        }

        bulletPoolController.removeInactiveBullets();
    }

    public void handleEnemies(){
        for(Spaceship enemy: gameModel.getEnemySpaceships()) {
            firingController.setSpaceship(enemy);
            movableElementController.setMovableElement(enemy);

            Position desiredEnemyPosition = movableElementController.move();
            firingController.fire(bulletPoolController);
                movableElementController.setPosition(desiredEnemyPosition);
        }
    }

    public void handleCoins(){
        for(Coin coin: gameModel.getCoins()) {
            movableElementController.setMovableElement(coin);
            Position desiredEnemyPosition = movableElementController.move();
            movableElementController.setPosition(desiredEnemyPosition);
        }
    }

    public void handleAsteroids(){
        for(Asteroid asteroid: gameModel.getAsteroids()) {
            movableElementController.setMovableElement(asteroid);
            Position desiredEnemyPosition = movableElementController.move();
            movableElementController.setPosition(desiredEnemyPosition);
        }
    }


    public void handle(Gui gui, long frame) {
        handleKeyPress(gui);
        handlePlayerInput(gui);
        handleEnemies();
        handleBullets();
        handleCoins();
        handleAsteroids();
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
