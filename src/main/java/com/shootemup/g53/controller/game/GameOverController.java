package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;

public class GameOverController extends GenericController {
    private GameOverModel gameOverModel = new GameOverModel();


    @Override
    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.UP)){
            gameOverModel.previousOption();
        }
        else if(gui.isActionActive(Action.DOWN)){
            gameOverModel.nextOption();
        }
        else if(gui.isActionActive(Action.SPACE)) {
            close();
            gameOverModel.getSelectedButton().getButtonCommand().execute();
            return;
        }
        else if(gui.isActionActive(Action.ESC)){
            close();
            gameOverModel.getOptions().get(1).getButtonCommand().execute();

        }

    }

    @Override
    public void handle() {

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
}
