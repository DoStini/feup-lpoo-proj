package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.collision.CollisionController;
import com.shootemup.g53.controller.collision.PlayerCoinHandler;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.HashMap;


public class GameController extends GenericController {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;
    private CollisionController collisionController;
    private HashMap<Element, ElementInterface> controllerHashMap = new HashMap<>();

    public GameController(GameModel gameModel) {
        this(gameModel, new BulletPoolController(gameModel, 30));
        this.bulletPoolController.setGameController(this);
    }

    public int numOfControllers(){
        return controllerHashMap.size();
    }


    public GameController(GameModel gameModel, BulletPoolController bulletPoolController) {
        this.gameModel = gameModel;
        this.bulletPoolController = bulletPoolController;
        this.collisionController = new CollisionController(gameModel);
        collisionController.addHandler(new PlayerCoinHandler(gameModel.getPlayer(), collisionController));
    }

    public void addToControllerMap(Element element, ElementInterface elementController){
        controllerHashMap.put(element,elementController);
    }

    public boolean isGameFinished(){
        return gameModel.isGameFinished();
    }
    public ElementInterface getElementController(Element element){
        return controllerHashMap.get(element);
    }

    public void removeFromControllerMap(Element element){
        controllerHashMap.remove(element);
    }

    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.ESC)){
            gameModel.setGameFinished(true);
        }
    }

    @Override
    public void handle(){
        handlePlayerInput();
        handleEnemies();
        handleBullets();
        handleCoins();
        handleCollision();
        removeInactiveElements();
    }

    public void removeInactiveElements(){
        controllerHashMap.entrySet().removeIf(e -> !e.getKey().isActive());

    }

    public boolean insideBounds(Position pos) {
        if(pos == null){
            return false;
        }
        return pos.getX()> 0 && pos.getX() < gameModel.getWidth() &&
                pos.getY() > 0 && pos.getY() < gameModel.getHeight();
    }

    public void handlePlayerInput() {
        getElementController(getGameModel().getPlayer()).handle();
    }

    public void handleBullets(){
        for(Bullet bullet: gameModel.getBulletList()) {
            getElementController(bullet).handle();
        }
        bulletPoolController.removeInactiveBullets();
    }

    public void handleEnemies(){
        for(Spaceship enemy: gameModel.getEnemySpaceships()) {
            getElementController(enemy).handle();
        }
    }

    public void handleCoins() {
        for (Coin coin : gameModel.getCoins()) {
            getElementController(coin).handle();
        }
    }

    public void handleAsteroids() {
        for (Asteroid asteroid : gameModel.getAsteroids()) {
            getElementController(asteroid).handle();
        }
    }

   public void handleCollision() {
        collisionController.checkCollisions();
   }

    public BulletPoolController getBulletPoolController() {
        return bulletPoolController;
    }

    public void setBulletPoolController(BulletPoolController bulletPoolController) {
        this.bulletPoolController = bulletPoolController;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.bulletPoolController.setGameModel(this.gameModel);
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
