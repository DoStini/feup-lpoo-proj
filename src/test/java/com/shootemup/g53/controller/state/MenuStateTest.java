package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.MenuStateController;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.game.MenuViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class MenuStateTest {
    MenuStateController menuStateController;
    MenuViewer menuViewer;
    MenuState menuState;
    KeyPressObserver keyPressObserver;
    private Gui gui;
    private Game game;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(Gui.class);
        game = Mockito.mock(Game.class);
        menuState = new MenuState(game, gui);
        menuViewer = Mockito.mock(MenuViewer.class);
        menuStateController = Mockito.mock(MenuStateController.class);
        keyPressObserver = Mockito.mock(KeyPressObserver.class);
        Mockito.when(menuStateController.getMenuModel()).thenReturn(Mockito.mock(MenuModel.class));
    }

    @Test
    void handle() {
        menuState.setSleepFrames(0);
        menuState.setMenuController(menuStateController);
        menuState.setMenuViewer(menuViewer);
        menuState.setKeyPressObserver(keyPressObserver);

        Mockito.when(keyPressObserver.getKeyPressed()).thenReturn(false, true);
        Mockito.when(menuStateController.isClosed()).thenReturn(false, false, true);

        menuState.run();

        Mockito.verify(menuViewer, Mockito.times(3)).draw(Mockito.any(MenuModel.class));
        Mockito.verify(menuStateController, Mockito.times(3)).isClosed();
        Mockito.verify(menuStateController, Mockito.times(4)).handleKeyPress(gui);
        Mockito.verify(keyPressObserver, Mockito.times(4)).getKeyPressed();
        Mockito.verify(keyPressObserver, Mockito.times(2)).resetKeyPress();
    }

    @Test
    void setupGameOver() {
        MenuState menuState = new MenuState(game, gui);
        Assertions.assertNotNull(menuState.getStateView());
        Assertions.assertNotNull(menuState.getStateController());

        Assertions.assertTrue(menuState.getStateController() instanceof MenuStateController);

        MenuStateController stateController = (MenuStateController) menuState.getStateController();

        Assertions.assertEquals(1, stateController.getInputNotifier().getInputObservers().size());


        List<Button> options = stateController.getMenuModel().getOptions();
        Assertions.assertEquals(StartCommand.class, options.get(0).getButtonCommand().getClass());
        Assertions.assertEquals(ExitCommand.class, options.get(1).getButtonCommand().getClass());
    }
}
