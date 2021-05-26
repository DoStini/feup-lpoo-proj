package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.model.game.GameOverModel;

import com.shootemup.g53.ui.Gui;


public class GameOverController extends GenericController {
    private GameOverModel gameOverModel = new GameOverModel();
    private InputNotifier inputNotifier = new InputNotifier();

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
            gameOverModel.getOptions().get(1).getButtonCommand().execute();
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
