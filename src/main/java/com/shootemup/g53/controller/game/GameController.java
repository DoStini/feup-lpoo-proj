package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.spaceship.AIChangingController;
import com.shootemup.g53.controller.spaceship.AIShootingController;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameController extends GenericController {
    private GameModel gameModel;
    private PlayerController playerController;
    SpaceshipController enemyController;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
        playerController = new PlayerController(gameModel.getPlayer());
        Spaceship s = gameModel.getEnemySpaceships().get(0);
        List<MovementController> controllers = Arrays.asList(
                new CircularMovement(s, 5, 0, 30),
                new DiagonalBounceMovement(s, 3, 3, DiagonalBounceMovement.Direction.DOWN_LEFT),
                new FallDownMovement(s)
        );

        enemyController = new AIChangingController(s, controllers, 20);
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
