package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.*;


public class GameController extends GenericController {
    private GameModel gameModel;
    private BulletPoolController bulletPoolController;
    private BackgroundController backgroundController;
    private List<ElementInterface> controllerCopy;
    private HashMap<Element, ElementInterface> controllerHashMap = new HashMap<>();

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
        this.controllerCopy = new ArrayList<>();
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
        if(backgroundController != null) backgroundController.handle();

        controllerCopy.clear();
        controllerCopy.addAll(controllerHashMap.values());

        for(ElementInterface elementInterface : controllerCopy) {
            elementInterface.handle();
        }

        removeInactiveElements();
    }

    public void removeInactiveElements(){
        bulletPoolController.removeInactiveBullets();

        controllerHashMap.entrySet().removeIf(e -> !e.getKey().isActive());
    }

    public boolean insideBounds(Position pos) {
        if(pos == null){
            return false;
        }
        return pos.getX()> 0 && pos.getX() < gameModel.getWidth() &&
                pos.getY() > 0 && pos.getY() < gameModel.getHeight();
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
