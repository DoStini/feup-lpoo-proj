package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;

public abstract class ButtonCommand {
    Game game;
    protected ButtonCommand(Game game) {
        this.game = game;
    }

    public abstract void execute();
}
