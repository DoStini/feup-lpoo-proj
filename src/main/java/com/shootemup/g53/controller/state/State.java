package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.model.Model;
import com.shootemup.g53.view.Viewer;

public abstract class State<M extends Model> {
    protected Game game;
    public abstract M getStateModel();
    public abstract Viewer<M> getStateView();
    public abstract GenericController getStateController();
    public abstract void run();
}