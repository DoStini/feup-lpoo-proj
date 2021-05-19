package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;

public class ExitCommand extends ButtonCommand {
    public ExitCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.setToExit();
    }
}
