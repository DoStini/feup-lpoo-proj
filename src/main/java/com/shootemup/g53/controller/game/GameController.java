package com.shootemup.g53.controller.game;


import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.collision.CollisionController;
import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.element.MovableElementController;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.HashMap;
import java.util.*;


public class GameController extends GenericController {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;

    private CollisionController collisionController;
    private BackgroundController backgroundController;
    private List<ElementInterface> controllerCopy;
    private HashMap<Element, ElementInterface> controllerHashMap = new HashMap<>();
    private HashMap<Element, CollisionHandlerController> collisionHashMap = new HashMap<>();

    public GameController(GameModel gameModel) {
        this(gameModel, new BulletPoolController(gameModel, 30));
        this.bulletPoolController.setGameController(this);
        this.controllerCopy = new ArrayList<>();
    }

    public int numOfControllers(){
        return controllerHashMap.size();
    }


    public GameController(GameModel gameModel, BulletPoolController bulletPoolController) {
        this.gameModel = gameModel;
        this.bulletPoolController = bulletPoolController;
        this.collisionController = new CollisionController(this);
        this.controllerCopy = new ArrayList<>();

    }

    public boolean isGameFinished(){
        return gameModel.isGameFinished();
    }

    public void addToControllerMap(Element element, ElementInterface elementController){
        controllerHashMap.put(element,elementController);
    }

    public void removeFromControllerMap(Element element){
        controllerHashMap.remove(element);
    }

    public ElementInterface getElementController(Element element){
        return controllerHashMap.get(element);
    }

    public void addToCollisionMap(Element element, CollisionHandlerController elementController){
        collisionHashMap.put(element, elementController);
    }

    public void removeFromCollisionMap(Element element){
        collisionHashMap.remove(element);
    }

    public CollisionHandlerController getCollisionHandler(Element element){
        return collisionHashMap.get(element);
    }

    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.ESC)){
            gameModel.setGameFinished(true);
        }
    }

    @Override
    public void handle(){

        if(backgroundController != null) backgroundController.handle();

        controllerCopy.clear();
        controllerCopy.addAll(controllerHashMap.values());

        for(ElementInterface elementInterface : controllerCopy) {
            elementInterface.handle();
        }

        handleCollision();

        removeInactiveElements();
    }

    public void removeInactiveElements(){
        bulletPoolController.removeInactiveBullets();

        collisionHashMap.entrySet().removeIf(e -> !e.getKey().isActive());
        controllerHashMap.entrySet().removeIf(e -> !e.getKey().isActive());

        gameModel.removeInactive();
    }

    public boolean insideBounds(Position pos) {
        if(pos == null){
            return false;
        }
        return pos.getX() > 0 && pos.getX() < gameModel.getWidth() &&
                pos.getY() < gameModel.getHeight();
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
            if (!insideBounds(enemy.getPosition()))
                enemy.deactivate();
        }
    }

    public void handleCoins() {
        for (Coin coin : gameModel.getCoins()) {
            getElementController(coin).handle();
            if (!insideBounds(coin.getPosition()))
                coin.deactivate();
        }
    }

    public void handleAsteroids() {
        for (Asteroid asteroid : gameModel.getAsteroids()) {
            getElementController(asteroid).handle();
            if (!insideBounds(asteroid.getPosition()))
                asteroid.deactivate();
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

    public void setBackgroundController(BackgroundController backgroundController) {
        this.backgroundController = backgroundController;
    }

    public BackgroundController getBackgroundController() {
        return backgroundController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
