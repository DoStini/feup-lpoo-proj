package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.view.Viewer;

public class PauseState extends State<PauseModel>{

    @Override
    public PauseModel getStateModel() {
        return null;
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

    }
}
