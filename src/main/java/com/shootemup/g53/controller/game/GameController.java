package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;


public class GameController extends GenericController {
    private GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }


    @Override
    public void handleKeyPress(EventType event) {
        if(event == null){
            return;
        }
        switch (event){
            case EOF: case Q_KEY:
                gameModel.setGameFinished(true);
                return;

            default:
                return;
        }
    }



    public boolean insideBounds(Position pos) {
        return pos.getX() > 0 && pos.getX() < gameModel.getWidth() && pos.getY() > 0 && pos.getY() < gameModel.getHeight();
    }
    /*
    public void handleEnemies() {
        EnemyController ec;
        for (Enemy enemy : gameModel.getEnemyList()){
            ec = new EnemyController(enemy);
            ec.handle(this);
        }
    }


    public void setElementPosition(Enemy enemy, Position pos) {
        enemy.setPosition(pos);
    }
    */

}
