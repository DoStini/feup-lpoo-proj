package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.command.MenuCommand;
import com.shootemup.g53.controller.command.ResumeCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.PauseStateController;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.game.PauseViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class PauseStateTest {
    PauseStateController pauseStateController;
    PauseViewer pauseViewer;
    PlayState playState;
    PauseState pauseState;
    KeyPressObserver keyPressObserver;
    private Gui gui;
    private Game game;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(Gui.class);
        game = Mockito.mock(Game.class);
        playState = Mockito.mock(PlayState.class);
        pauseState = new PauseState(game, gui, playState);
        pauseViewer = Mockito.mock(PauseViewer.class);
        pauseStateController = Mockito.mock(PauseStateController.class);
        keyPressObserver = Mockito.mock(KeyPressObserver.class);
        Mockito.when(pauseStateController.getPauseModel()).thenReturn(Mockito.mock(PauseModel.class));
    }

    @Test
    void handle() {
        pauseState.setSleepFrames(0);
        pauseState.setPauseController(pauseStateController);
        pauseState.setPauseViewer(pauseViewer);
        pauseState.setKeyPressObserver(keyPressObserver);

        Mockito.when(keyPressObserver.getKeyPressed()).thenReturn(false, true);
        Mockito.when(pauseStateController.isClosed()).thenReturn(false, false, true);

        pauseState.run();

        Mockito.verify(pauseViewer, Mockito.times(3)).draw(Mockito.any(PauseModel.class));
        Mockito.verify(pauseStateController, Mockito.times(3)).isClosed();
        Mockito.verify(pauseStateController, Mockito.times(4)).handleKeyPress(gui);
        Mockito.verify(keyPressObserver, Mockito.times(4)).getKeyPressed();
        Mockito.verify(keyPressObserver, Mockito.times(2)).resetKeyPress();
    }

    @Test
    void setupPause() {
        PauseState pauseStateConst = new PauseState(game, gui, playState);
        Assertions.assertNotNull(pauseStateConst.getStateView());
        Assertions.assertNotNull(pauseStateConst.getStateController());

        Assertions.assertTrue(pauseStateConst.getStateController() instanceof PauseStateController);

        PauseStateController pauseStateController = (PauseStateController) pauseStateConst.getStateController();

        Assertions.assertEquals(1, pauseStateController.getInputNotifier().getInputObservers().size());


        List<Button> options = pauseStateController.getPauseModel().getOptions();
        Assertions.assertEquals(ResumeCommand.class, options.get(0).getButtonCommand().getClass());
        Assertions.assertEquals(StartCommand.class, options.get(1).getButtonCommand().getClass());
        Assertions.assertEquals(MenuCommand.class, options.get(2).getButtonCommand().getClass());
    }

}