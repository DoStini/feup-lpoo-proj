package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;


public class MenuStateController implements GenericController {
    private MenuModel menuModel;
    private InputNotifier inputNotifier;

    public MenuStateController() {
        this(new MenuModel(), new InputNotifier());
    }

    public MenuStateController(MenuModel menuModel, InputNotifier inputNotifier) {
        this.menuModel = menuModel;
        this.inputNotifier = inputNotifier;
    }

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
        }
        else if(gui.isActionActive(Action.ESC)){
            close();
            menuModel.getExitBtn().getButtonCommand().execute();
            inputNotifier.notifyObservers();

        }

    }

    public boolean isClosed(){
        return menuModel.isClosed();
    }

    public void close(){
        menuModel.setClosed(true);
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public InputNotifier getInputNotifier() {
        return inputNotifier;
    }
}
