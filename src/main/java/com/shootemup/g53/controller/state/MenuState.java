package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.MenuStateController;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.MenuViewer;

public class MenuState extends State<MenuModel> {
    private MenuStateController menuController;
    private Viewer<MenuModel> menuViewer;
    private Gui gui;
    private KeyPressObserver keyPressObserver = new KeyPressObserver();

    public MenuState(Game game, Gui gui) {
        this.game = game;
        this.gui = gui;
        this.menuController = new MenuStateController();
        this.menuViewer = new MenuViewer(gui);
        this.menuController.getInputNotifier().addObserver(keyPressObserver);
        getStateModel().getOptions().get(0).setButtonCommand(new StartCommand(game));
        getStateModel().getOptions().get(1).setButtonCommand(new ExitCommand(game));
    }

    @Override
    public MenuModel getStateModel() {
        return menuController.getMenuModel();
    }

    @Override
    public void exit() {
        menuController.close();
    }

    @Override
    public Viewer<MenuModel> getStateView() {
        return menuViewer;
    }

    @Override
    public GenericController getStateController() {
        return menuController;
    }

    @Override
    public void run() {
        try {
        menuViewer.draw(getStateModel());
        Thread.sleep(100);
        while (true) {

            menuController.handleKeyPress(gui);
            if(keyPressObserver.getKeyPressed()){
                menuViewer.draw(getStateModel());
                if(menuController.isClose()){
                    return;
                }
                Thread.sleep(200);
                keyPressObserver.resetKeyPress();
            }

            }
        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
}
