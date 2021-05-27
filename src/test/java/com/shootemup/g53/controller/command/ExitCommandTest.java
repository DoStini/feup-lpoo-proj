package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
    }

    @Test
    void execute() {
        ExitCommand exitCommand = new ExitCommand(game);
        exitCommand.execute();
        Mockito.verify(game, Mockito.times(1)).setToExit();
        Mockito.verify(game, Mockito.never()).changeState(Mockito.any());
    }
}