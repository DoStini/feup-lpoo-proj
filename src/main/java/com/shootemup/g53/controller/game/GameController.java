package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.spaceship.AIShootingController;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;


public class GameController extends GenericController {
    private GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }


    @Override
    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.Q)){
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
        PlayerController pc = new PlayerController(player);
        Position desiredPosition = pc.move(gui);
        if(insideBounds(desiredPosition)){
            player.setPosition(desiredPosition);
        }

    }

    public void handleEnemies(Gui gui){
        AIShootingController enemyController;
        for(Spaceship enemy: gameModel.getEnemySpaceships()){
            enemyController = new AIShootingController(enemy);
            Position desiredEnemyPosition = enemyController.move(gui);
            if(insideBounds(desiredEnemyPosition)){
                enemy.setPosition(desiredEnemyPosition);
            }
        }
    }



}
