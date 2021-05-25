package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.MenuCommand;
import com.shootemup.g53.controller.command.ResumeCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.MenuStateController;
import com.shootemup.g53.controller.game.PauseStateController;
import com.shootemup.g53.controller.gameBuilder.GameBuilder;
import com.shootemup.g53.controller.input.InputObserver;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.MenuViewer;
import com.shootemup.g53.view.game.PauseViewer;

public class PauseState extends State<PauseModel> {
    private PauseStateController pauseController;
    private Viewer<PauseModel> pauseViewer;
    private PlayState playState;
    private Gui gui;
    private KeyPressObserver keyPressObserver = new KeyPressObserver();

    public PauseState(Game game, Gui gui, PlayState playState) {
        this.game = game;
        this.gui = gui;
        this.pauseController = new PauseStateController(playState);
        this.pauseController.getInputNotifier().addObserver(keyPressObserver);
        this.pauseViewer = new PauseViewer(gui);
        getStateModel().getOptions().get(0).setButtonCommand(new ResumeCommand(game, playState));
        getStateModel().getOptions().get(1).setButtonCommand(new StartCommand(game));
        getStateModel().getOptions().get(2).setButtonCommand(new MenuCommand(game));
    }

    @Override
    public PauseModel getStateModel() {
        return pauseController.getPauseModel();
    }

    @Override
    public void exit() {

    }

    @Override
    public Viewer<PauseModel> getStateView() {
        return null;
    }

    @Override
    public GenericController getStateController() {
        return null;
    }

    @Override
    public void run() {
        try {
        pauseViewer.draw(getStateModel());
        Thread.sleep(100);
        while (true) {
            pauseController.handleKeyPress(gui);
            if (keyPressObserver.getKeyPressed()) {
                pauseViewer.draw(getStateModel());
                if (pauseController.isClose()) {
                    return;
                }
                keyPressObserver.resetKeyPress();
                Thread.sleep(200);

            }
        }


        } catch (InterruptedException e) {
                e.printStackTrace();
        }


    }


}

