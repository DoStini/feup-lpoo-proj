package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.view.Viewer;

public class GameOverState extends State<GameOverModel> {
    @Override
    public GameOverModel getStateModel() {
        return null;
    }

    @Override
    public Viewer<GameOverModel> getStateView() {
        return null;
    }

    @Override
    public GenericController getStateController() {
        return null;
    }

    @Override
    public void run() {

    }
}
