package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.command.ButtonCommand;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameOverControllerTest {

    private GameOverModel model;
    private Gui gui;
    private InputNotifier inputNotifier;

    @BeforeEach
    void setUp() {
        model = Mockito.spy(new GameOverModel());
        gui = Mockito.mock(Gui.class);
        inputNotifier = Mockito.mock(InputNotifier.class);
    }

    @Test
    void handleNoPress() {
        GameOverController gameOverController = new GameOverController(model, inputNotifier);

        gameOverController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.never()).notifyObservers();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).getOptions();
    }

    @Test
    void handleUpPress() {
        GameOverController gameOverController = new GameOverController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.UP)).thenReturn(true);

        gameOverController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.times(1)).previousOption();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).getOptions();
    }

    @Test
    void handleDownPress() {
        GameOverController gameOverController = new GameOverController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.DOWN)).thenReturn(true);

        gameOverController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.times(1)).nextOption();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).getOptions();
    }

    @Test
    void handleSpacePress() {
        GameOverController gameOverController = new GameOverController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);
        Button button = Mockito.mock(Button.class);
        ButtonCommand cmd = Mockito.mock(ButtonCommand.class);

        Mockito.when(button.getButtonCommand()).thenReturn(cmd);
        Mockito.when(model.getSelectedButton()).thenReturn(button);

        gameOverController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).getOptions();
        Mockito.verify(cmd, Mockito.times(1)).execute();

        Assertions.assertTrue(model.isClosed());
    }

    @Test
    void handleEscPress() {
        GameOverController gameOverController = new GameOverController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.ESC)).thenReturn(true);
        Button button = Mockito.mock(Button.class);
        ButtonCommand cmd = Mockito.mock(ButtonCommand.class);

        Mockito.when(button.getButtonCommand()).thenReturn(cmd);
        Mockito.when(model.getExitBtn()).thenReturn(button);

        gameOverController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).getOptions();
        Mockito.verify(cmd, Mockito.times(1)).execute();

        Assertions.assertTrue(model.isClosed());
    }

}