package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MenuCommandTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
    }

    @Test
    void execute() {
        MenuCommand command = new MenuCommand(game);
        command.execute();
        Mockito.verify(game, Mockito.never()).setToExit();
        Mockito.verify(game, Mockito.times(1)).changeState(Mockito.any(MenuState.class));
    }
}