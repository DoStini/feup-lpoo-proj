package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.ArrayList;



public class GameController extends GenericController {
    private GameModel gameModel;
    private PlayerController playerController;
    private SpaceshipController spaceshipController;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
        this.playerController = new PlayerController(gameModel.getPlayer());
    }


    @Override
    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.ESC)){
            gameModel.setGameFinished(true);
        }
    }

    public boolean insideBounds(Position pos) {
        if(pos == null){
            return false;
        }
        return pos.getX() > 0 && pos.getX() < gameModel.getWidth() && pos.getY() > 0 && pos.getY() < gameModel.getHeight();
    }

    public void handlePlayerInput(Gui gui) {
        Spaceship player = gameModel.getPlayer();
        Position desiredPosition = playerController.move(gui);
        Bullet bullet = playerController.fire(gui);
        if(bullet != null) gameModel.addBullet(bullet);
        if(insideBounds(desiredPosition)){
            player.setPosition(desiredPosition);
        }
    }

    public void handleBullets(){
        for(Bullet bullet: new ArrayList<>(gameModel.getBulletList())){
            Position newBulletPos = bullet.move();
            if(insideBounds(newBulletPos)){
                bullet.setPosition(newBulletPos);
            }else{
                gameModel.removeBullet(bullet);
            }
        }
    }

    public void handleEnemies(){
        for(Spaceship enemy: gameModel.getEnemySpaceships()) {
            spaceshipController = new SpaceshipController(enemy);
            Position desiredEnemyPosition = spaceshipController.move();
            Bullet bullet = spaceshipController.fire();
            if(bullet != null){
                gameModel.addBullet(bullet);
            }
            if(insideBounds(desiredEnemyPosition)){
                enemy.setPosition(desiredEnemyPosition);
            }else{
                spaceshipController.handleFailedMovement();
                spaceshipController.move();
            }
        }
    }

    public void handleCoins(){
        for(Coin coin: gameModel.getCoins()) {
            Position desiredEnemyPosition = coin.move();
            if(insideBounds(desiredEnemyPosition)){
                coin.setPosition(desiredEnemyPosition);
            }else{
                coin.handleFailedMovement();
                coin.move();
            }
        }
    }
}
