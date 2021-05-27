package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.command.ButtonCommand;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.input.InputNotifier;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MenuStateControllerTest {

    private MenuModel model;
    private Gui gui;
    private InputNotifier inputNotifier;

    @BeforeEach
    void setUp() {
        model = Mockito.spy(new MenuModel());
        gui = Mockito.mock(Gui.class);
        inputNotifier = Mockito.mock(InputNotifier.class);
    }

    @Test
    void handleNoPress() {
        MenuStateController menuStateController = new MenuStateController(model, inputNotifier);

        menuStateController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.never()).notifyObservers();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).getOptions();
    }

    @Test
    void handleUpPress() {
        MenuStateController menuStateController = new MenuStateController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.UP)).thenReturn(true);

        menuStateController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.times(1)).previousOption();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).getOptions();
    }

    @Test
    void handleDownPress() {
        MenuStateController menuStateController = new MenuStateController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.DOWN)).thenReturn(true);

        menuStateController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.times(1)).nextOption();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).getOptions();
    }

    @Test
    void handleSpacePress() {
        MenuStateController menuStateController = new MenuStateController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.SPACE)).thenReturn(true);
        Button button = Mockito.mock(Button.class);
        ButtonCommand cmd = Mockito.mock(ButtonCommand.class);

        Mockito.when(button.getButtonCommand()).thenReturn(cmd);
        Mockito.when(model.getSelectedButton()).thenReturn(button);

        menuStateController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).getOptions();
        Mockito.verify(cmd, Mockito.times(1)).execute();

        Assertions.assertTrue(model.isClosed());
    }

    @Test
    void handleEscPress() {
        MenuStateController menuStateController = new MenuStateController(model, inputNotifier);
        Mockito.when(gui.isActionActive(Action.ESC)).thenReturn(true);
        Button button = Mockito.mock(Button.class);
        ButtonCommand cmd = Mockito.mock(ButtonCommand.class);

        Mockito.when(button.getButtonCommand()).thenReturn(cmd);
        Mockito.when(model.getExitBtn()).thenReturn(button);

        menuStateController.handleKeyPress(gui);
        Mockito.verify(inputNotifier, Mockito.times(1)).notifyObservers();
        Mockito.verify(model, Mockito.never()).nextOption();
        Mockito.verify(model, Mockito.never()).previousOption();
        Mockito.verify(model, Mockito.never()).getOptions();
        Mockito.verify(cmd, Mockito.times(1)).execute();

        Assertions.assertTrue(model.isClosed());
    }
}