package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;

public class PauseStateController implements GenericController {

    private PauseModel pauseModel;
    private InputNotifier inputNotifier;

    public PauseStateController(PlayState playState) {
        this(new PauseModel(playState), new InputNotifier());
    }

    public PauseStateController(PauseModel pauseModel, InputNotifier inputNotifier) {
        this.pauseModel = pauseModel;
        this.inputNotifier = inputNotifier;
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
        } else if (gui.isActionActive(Action.ESC)) {
            close();
            pauseModel.getExitBtn().getButtonCommand().execute();
            inputNotifier.notifyObservers();
        }
    }

    public boolean isClose() {
        return pauseModel.isClosed();
    }

    public void close() {
        pauseModel.setClosed(true);
    }

    public PauseModel getPauseModel() {
        return pauseModel;
    }

    public InputNotifier getInputNotifier() {
        return inputNotifier;
    }
}
