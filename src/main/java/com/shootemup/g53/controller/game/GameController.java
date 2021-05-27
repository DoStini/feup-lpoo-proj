package com.shootemup.g53.controller.game;


import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.collision.CollisionController;
import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.observer.ScoreController;
import com.shootemup.g53.controller.observer.WaveCompletionController;

import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.controller.player.PowerupController;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.HashMap;
import java.util.*;


public class GameController implements GenericController {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;

    private CollisionController collisionController;
    private BackgroundController backgroundController;
    private List<ElementInterface> controllerCopy;
    private HashMap<Element, ElementInterface> controllerHashMap = new HashMap<>();
    private HashMap<Element, CollisionHandlerController> collisionHashMap = new HashMap<>();

    private ScoreController scoreController = new ScoreController();

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


    public void finishGame(){
        gameModel.setGameFinished(true);
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
        if(gui.isActionActive(Action.Q)){
            gameModel.setPaused(true);
        }
    }

     @Override
    public void handle(long frame){
        if(backgroundController != null) backgroundController.handle(frame);


        controllerCopy.clear();
        controllerCopy.addAll(controllerHashMap.values());

        for(ElementInterface elementInterface : controllerCopy) {
            elementInterface.handle(frame);
        }

        handleCollision();

        if(gameModel.getPlayer().getHealth() <= 0) finishGame();

        checkOutsideBounds();
        deactivateDead();
        removeInactiveElements();
    }



    protected void deactivateDead() {
        gameModel.getEnemySpaceships().stream()
                .filter(enemy -> enemy.getHealth() <= 0)
                .forEach(Element::deactivate);

        for( int i = 0; i < gameModel.getEnemySpaceships().stream()
                .filter(enemy -> enemy.getHealth() <= 0).count(); i++){
            scoreController.notifyObservers();
        }

        gameModel.getShieldList().stream()
                .filter(shield -> shield.getStrength() <= 0)
                .forEach(Element::deactivate);
    }

    private void checkOutsideBounds() {
        for(Bullet bullet: gameModel.getBulletList())
            if (!insideBounds(bullet.getPosition(), 1, bullet.getSize()))
                bullet.deactivate();
        for(Coin coin: gameModel.getCoins())
            if (!insideBounds(coin.getPosition(), coin.getRadius()*2, coin.getRadius()*2))
                coin.deactivate();
        for(Asteroid asteroid: gameModel.getAsteroids())
            if (!insideBounds(asteroid.getPosition(), asteroid.getRadius()*2, asteroid.getRadius()*2))
                asteroid.deactivate();
        for(Spaceship spaceship: gameModel.getEnemySpaceships())
            if (!insideBounds(spaceship.getPosition(), spaceship.getHeight()*2, spaceship.getHeight()*2))
                spaceship.deactivate();
    }

    public void removeInactiveElements(){
        bulletPoolController.removeInactiveBullets();

        collisionHashMap.entrySet().removeIf(e -> !e.getKey().isActive());
        controllerHashMap.entrySet().removeIf(e -> !e.getKey().isActive());

        gameModel.removeInactive();
    }

    public boolean insideBounds(Position pos, int width, int height) {
        if(pos == null){
            return false;
        }
        return pos.getX() + width > 0 && pos.getX() < gameModel.getWidth() + width &&
                pos.getY() < gameModel.getHeight() + height && pos.getY() + height >= 0;
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
    public boolean isPaused(){
        return gameModel.isPaused();
    }

    public void unpause(){ gameModel.setPaused(false);}

    public void setBackgroundController(BackgroundController backgroundController) {
        this.backgroundController = backgroundController;
    }

    public BackgroundController getBackgroundController() {
        return backgroundController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public PlayerController getPlayerController(){
        return (PlayerController) controllerHashMap.get(gameModel.getPlayer());
    }


    public ScoreController getScoreController() {
        return scoreController;
    }
}
