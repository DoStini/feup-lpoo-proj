package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.MenuState;

public class MenuCommand extends ButtonCommand {
    public MenuCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.changeState(new MenuState(game, game.getGui()));
    }
}
