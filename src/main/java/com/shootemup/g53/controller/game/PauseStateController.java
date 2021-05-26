package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;

public class PauseStateController extends GenericController {

    private PauseModel pauseModel;
    private InputNotifier inputNotifier = new InputNotifier();
    public PauseStateController(PlayState playState) {
        this.pauseModel = new PauseModel(playState);
    }


    @Override
    public void handleKeyPress(Gui gui) {
        if (gui.isActionActive(Action.UP)) {
            pauseModel.previousOption();
            inputNotifier.notifyObservers();
        } else if (gui.isActionActive(Action.DOWN)) {
            pauseModel.nextOption();
            inputNotifier.notifyObservers();
        } else if (gui.isActionActive(Action.SPACE)) {
            close();
            pauseModel.getSelectedButton().getButtonCommand().execute();
            inputNotifier.notifyObservers();
            return;
        } else if (gui.isActionActive(Action.ESC)) {
            close();
            pauseModel.getOptions().get(1).getButtonCommand().execute();
            inputNotifier.notifyObservers();
        }
    }

    public boolean isClose() {
        return pauseModel.isClosed();
    }

    public void close() {
        pauseModel.setClosed(true);
    }


    @Override
    public void handle(long frame) {

    }


    public PauseModel getPauseModel() {
        return pauseModel;
    }

    public InputNotifier getInputNotifier() {
        return inputNotifier;
    }
}
