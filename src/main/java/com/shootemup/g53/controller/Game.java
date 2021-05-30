package com.shootemup.g53.controller;

import com.shootemup.g53.controller.state.MenuState;
import com.shootemup.g53.controller.state.State;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;

public class Game {
    private State state;
    private Gui gui;
    protected int width;
    protected int height;
    private boolean toExit = false;

    public Game(Gui gui, int width, int height){
        this.gui = gui;
        this.width = width;
        this.height = height;
        this.state = new MenuState(this,gui);
    }

    public void changeState(State state){
        this.state = state;
    }

    public void run(){
        while(!toExit) this.state.run();
    }

    public boolean isToExit() {
        return toExit;
    }

    public void setToExit() {
        this.state.exit();
        this.toExit = true;
    }

    public Gui getGui() {
        return gui;
    }

    public State getState() {
        return state;
    }
}
