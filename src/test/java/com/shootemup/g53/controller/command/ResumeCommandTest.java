package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.MenuState;
import com.shootemup.g53.controller.state.PlayState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ResumeCommandTest {
    Game game;
    PlayState playState;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        playState = Mockito.mock(PlayState.class);
    }

    @Test
    void execute() {
        ResumeCommand command = new ResumeCommand(game, playState);
        command.execute();
        Mockito.verify(game, Mockito.never()).setToExit();
        Mockito.verify(game, Mockito.times(1)).changeState(playState);
    }
}
