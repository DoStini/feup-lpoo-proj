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
        GameModel gameModel = new GameModel(game.getGui().getWidth(), game.getGui().getHeight());
        game.changeState(new PlayState(game, gameModel, game.getGui()));
    }
}
