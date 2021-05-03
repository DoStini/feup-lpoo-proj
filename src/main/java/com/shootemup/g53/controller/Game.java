package com.shootemup.g53.controller;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.controller.state.State;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.utility.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.input.KeyPressEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Game {
    private State state;
    private Gui gui;
    private boolean toExit = false;

    public Game(Gui gui, GameModel gameModel){
        this.gui = gui;
        this.state = new PlayState(this,gameModel,gui);
    }

    public void changeState(State state){
        this.state = state;
    }
    public void run(){
        while(!toExit) this.state.run();
        System.exit(0);
    }

    public boolean isToExit() {
        return toExit;
    }

    public void setToExit(boolean toExit) {
        this.toExit = toExit;
    }
}
