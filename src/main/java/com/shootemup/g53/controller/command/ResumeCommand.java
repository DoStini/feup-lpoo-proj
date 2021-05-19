package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.PlayState;

public class ResumeCommand extends ButtonCommand{
    private PlayState playState;
    public ResumeCommand(Game game, PlayState playState) {
        super(game);
        this.playState = playState;
    }
    @Override
    public void execute() {
        game.changeState(playState);
    }
}
