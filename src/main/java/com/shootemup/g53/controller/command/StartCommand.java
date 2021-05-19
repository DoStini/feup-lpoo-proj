package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.gameBuilder.GameBuilder;
import com.shootemup.g53.controller.state.PlayState;

public class StartCommand extends ButtonCommand{

    public StartCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.changeState(new PlayState(game, game.getGui(), new GameBuilder()));
    }
}
