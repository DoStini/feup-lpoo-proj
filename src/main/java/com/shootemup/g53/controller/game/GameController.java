package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.spaceship.*;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.Iterator;


public class GameController extends GenericController {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;
    private PlayerController playerController;
    private SpaceshipController enemyController; // For testing purposes

    public GameController(GameModel gameModel) {
        bulletPoolController = new BulletPoolController(gameModel, 2);
        this.gameModel = gameModel;
        playerController = new PlayerController(gameModel.getPlayer());
        Spaceship s = gameModel.getEnemySpaceships().get(0);
        enemyController = new AIShootingController(s, new FallDownMovement(s));
    }


    @Override
    public void handle(Gui gui) {
        handleKeyPress(gui);
        handlePlayerInput(gui);
        handleEnemies(gui);
        handleBullets();

    }

    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.ESC)){
            gameModel.setGameFinished(true);
        }
    }

    public boolean insideBounds(Position pos, int width, int height) {
        if(pos == null){
            return false;
        }
        return pos.getX()> 0 && pos.getX() < gameModel.getWidth() &&
                pos.getY() > 0 && pos.getY() < gameModel.getHeight();
    }

    public void handlePlayerInput(Gui gui) {
        Spaceship player = gameModel.getPlayer();
        Position desiredPosition = playerController.handle(gui, bulletPoolController);
        if(insideBounds(desiredPosition, player.getHeight(), player.getHeight())){
            player.setPosition(desiredPosition);
        }
    }

    public void handleEnemies(Gui gui){
        for(Spaceship enemy: gameModel.getEnemySpaceships()) {
            Position desiredEnemyPosition = enemyController.handle(gui, bulletPoolController);
            if(insideBounds(desiredEnemyPosition, enemy.getHeight(), enemy.getHeight())){
                enemy.setPosition(desiredEnemyPosition);
            }
        }
    }

    public void handleBullets() {
        for (Bullet bullet: gameModel.getEnemyBullets()) {
            bullet.setPosition(new FallDownMovement(bullet).move());
        }
        for (Bullet bullet: gameModel.getPlayerBullets()) {
            bullet.setPosition(new GoUpMovement(bullet).move());
        }
        restoreBullets();
    }

    public void restoreBullets() {
        Iterator<Bullet> it = gameModel.getEnemyBullets().iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (!insideBounds(bullet.getPosition(), 0, 0)) {
                it.remove();
                bulletPoolController.removeBullet(bullet);
            }
        }
        it = gameModel.getPlayerBullets().iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (!insideBounds(bullet.getPosition(), 0, 0)) {
                it.remove();
                bulletPoolController.removeBullet(bullet);
            }
        }
    }
}
