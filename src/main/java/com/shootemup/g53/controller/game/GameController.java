package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.movement.CircularMovement;
import com.shootemup.g53.controller.movement.DiagonalDownLeftMovement;
import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.controller.spaceship.AIShootingController;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;


public class GameController extends GenericController {
    private GameModel gameModel;
    private PlayerController playerController;
    AIShootingController enemyController;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
        playerController = new PlayerController(gameModel.getPlayer());
        MovementController m = new CircularMovement(gameModel.getEnemySpaceships().get(0), 5, 0, 20);
        enemyController = new AIShootingController(gameModel.getEnemySpaceships().get(0), m);
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
        Position desiredPosition = playerController.handle(gui);
        if(insideBounds(desiredPosition)){
            player.setPosition(desiredPosition);
        }
    }

    public void handleEnemies(Gui gui){
        for(Spaceship enemy: gameModel.getEnemySpaceships()) {
            Position desiredEnemyPosition = enemyController.handle(gui);
            if(insideBounds(desiredEnemyPosition)){
                enemy.setPosition(desiredEnemyPosition);
            }
        }
    }
}
