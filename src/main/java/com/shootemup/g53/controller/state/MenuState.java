package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.MenuStateController;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.MenuViewer;

public class MenuState extends State<MenuModel> {
    private MenuStateController menuController;
    private Viewer<MenuModel> menuViewer;
    private Gui gui;

    public MenuState(Game game, Gui gui) {
        this.game = game;
        this.gui = gui;
        this.menuController = new MenuStateController();
        this.menuViewer = new MenuViewer(gui);
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
            while (true) {
                Thread.sleep(100);
                menuController.handleKeyPress(gui);
                menuViewer.draw(getStateModel());
                if(menuController.isClose()){
                    return;
                }

            }
        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
}
