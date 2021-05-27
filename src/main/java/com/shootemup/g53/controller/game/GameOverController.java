package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.model.game.GameOverModel;

import com.shootemup.g53.ui.Gui;


public class GameOverController implements GenericController {
    private GameOverModel gameOverModel;
    private InputNotifier inputNotifier;

    public GameOverController() {
        this(new GameOverModel(), new InputNotifier());
    }

    public GameOverController(GameOverModel gameOverModel, InputNotifier inputNotifier) {
        this.gameOverModel = gameOverModel;
        this.inputNotifier = inputNotifier;
    }

    @Override
    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.UP)){
            gameOverModel.previousOption();
            inputNotifier.notifyObservers();
        }
        else if(gui.isActionActive(Action.DOWN)){
            gameOverModel.nextOption();
            inputNotifier.notifyObservers();
        }
        else if(gui.isActionActive(Action.SPACE)) {
            close();
            gameOverModel.getSelectedButton().getButtonCommand().execute();
            inputNotifier.notifyObservers();
            return;
        }
        else if(gui.isActionActive(Action.ESC)){
            close();
            gameOverModel.getExitBtn().getButtonCommand().execute();
            inputNotifier.notifyObservers();

        }

    }

    @Override
    public void handle(long frame) {

    }

    public boolean isClose(){
        return gameOverModel.isClosed();
    }

    public void close(){
        gameOverModel.setClosed(true);
    }

    public GameOverModel getGameOverModel() {
        return gameOverModel;
    }

    public InputNotifier getInputNotifier() {
        return inputNotifier;
    }
}
