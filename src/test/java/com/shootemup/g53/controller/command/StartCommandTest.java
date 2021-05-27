package com.shootemup.g53.controller.command;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StartCommandTest {
    Game game;
    GameModel gameModel;
    Gui gui;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(Gui.class);
        game = Mockito.spy(new Game(gui, gameModel));
        Mockito.when(game.getGui()).thenReturn(gui);
        Mockito.when(gui.getHeight()).thenReturn(20);
        Mockito.when(gui.getWidth()).thenReturn(20);
    }

    @Test
    void execute() {
        GameModel expected = new GameModel(game.getGui().getWidth(), game.getGui().getHeight());
        StartCommand command = new StartCommand(game);
        command.execute();
        Mockito.verify(game, Mockito.never()).setToExit();
        Mockito.verify(game, Mockito.times(1))
                .changeState(Mockito.any(PlayState.class));

        GameModel got = ((PlayState)game.getState()).getGameModel();

        Assertions.assertEquals(expected.getWidth(), got.getWidth());
        Assertions.assertEquals(expected.getHeight(), got.getHeight());
    }
}