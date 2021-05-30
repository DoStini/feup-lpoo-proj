package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.command.MenuCommand;
import com.shootemup.g53.controller.command.ResumeCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.PauseStateController;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.PauseViewer;

public class PauseState extends State<PauseModel> {
    private PauseStateController pauseController;
    private Viewer<PauseModel> pauseViewer;
    private PlayState playState;
    private Gui gui;
    private KeyPressObserver keyPressObserver = new KeyPressObserver();
    private int sleepFrames = 200;

    public PauseState(Game game, Gui gui, PlayState playState) {
        this.game = game;
        this.gui = gui;
        this.playState = playState;
        setup();
    }

    private void setup() {
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
        return pauseViewer;
    }

    @Override
    public GenericController getStateController() {
        return pauseController;
    }

    @Override
    public void run() {
        gui.resetAllKeyPress();
        try {
            pauseViewer.draw(getStateModel());
            while (true) {
                pauseController.handleKeyPress(gui);
                if (keyPressObserver.getKeyPressed()) {

                    if (pauseController.isClosed()) {
                        return;
                    }

                    pauseViewer.draw(getStateModel());

                    keyPressObserver.resetKeyPress();
                    Thread.sleep(sleepFrames);

                }
            }

        } catch (InterruptedException e) {
                e.printStackTrace();
        }


    }

    protected void setPauseController(PauseStateController pauseController) {
        this.pauseController = pauseController;
    }

    protected void setPauseViewer(Viewer<PauseModel> pauseViewer) {
        this.pauseViewer = pauseViewer;
    }

    protected void setPlayState(PlayState playState) {
        this.playState = playState;
    }

    public void setKeyPressObserver(KeyPressObserver keyPressObserver) {
        this.keyPressObserver = keyPressObserver;
    }

    public void setSleepFrames(int sleepFrames) {
        this.sleepFrames = sleepFrames;
    }
}

