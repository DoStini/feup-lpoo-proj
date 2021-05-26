package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;


public class MenuStateController extends GenericController {
    private MenuModel menuModel = new MenuModel();
    private InputNotifier inputNotifier = new InputNotifier();
    @Override
    public void handleKeyPress(Gui gui) {
        if(gui.isActionActive(Action.UP)){
            menuModel.previousOption();
            inputNotifier.notifyObservers();
        }
        else if(gui.isActionActive(Action.DOWN)){
            menuModel.nextOption();
            inputNotifier.notifyObservers();
        }
        else if(gui.isActionActive(Action.SPACE)) {
            close();
            menuModel.getSelectedButton().getButtonCommand().execute();
            inputNotifier.notifyObservers();
            return;
        }
        else if(gui.isActionActive(Action.ESC)){
            close();
            menuModel.getOptions().get(1).getButtonCommand().execute();
            inputNotifier.notifyObservers();

        }

    }

    public boolean isClose(){
        return menuModel.isClosed();
    }

    public void close(){
        menuModel.setClosed(true);
    }

    @Override
    public void handle(long frame) {

    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public InputNotifier getInputNotifier() {
        return inputNotifier;
    }
}
