package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.model.game.GameModel;

public class StartCommand extends ButtonCommand{

    public StartCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.changeState(new PlayState(game, game.getGui()));
    }
}
