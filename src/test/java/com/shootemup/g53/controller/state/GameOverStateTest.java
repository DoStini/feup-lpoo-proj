package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.MenuCommand;
import com.shootemup.g53.controller.command.ResumeCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.GameOverController;
import com.shootemup.g53.controller.game.PauseStateController;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.game.GameOverViewer;
import com.shootemup.g53.view.game.PauseViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class GameOverStateTest {
    GameOverController gameOverController;
    GameOverViewer gameOverViewer;
    GameOverState gameOverState;
    KeyPressObserver keyPressObserver;
    private Gui gui;
    private Game game;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(Gui.class);
        game = Mockito.mock(Game.class);
        gameOverState = new GameOverState(game, gui,10);
        gameOverViewer = Mockito.mock(GameOverViewer.class);
        gameOverController = Mockito.mock(GameOverController.class);
        keyPressObserver = Mockito.mock(KeyPressObserver.class);
        Mockito.when(gameOverController.getGameOverModel()).thenReturn(Mockito.mock(GameOverModel.class));
    }

    @Test
    void handle() {
        gameOverState.setSleepFrames(0);
        gameOverState.setGameOverController(gameOverController);
        gameOverState.setGameOverViewer(gameOverViewer);
        gameOverState.setKeyPressObserver(keyPressObserver);

        Mockito.when(keyPressObserver.getKeyPressed()).thenReturn(false, true);
        Mockito.when(gameOverController.isClosed()).thenReturn(false, false, true);

        gameOverState.run();

        Mockito.verify(gameOverViewer, Mockito.times(3)).draw(Mockito.any(GameOverModel.class));
        Mockito.verify(gameOverController, Mockito.times(3)).isClosed();
        Mockito.verify(gameOverController, Mockito.times(4)).handleKeyPress(gui);
        Mockito.verify(keyPressObserver, Mockito.times(4)).getKeyPressed();
        Mockito.verify(keyPressObserver, Mockito.times(2)).resetKeyPress();
    }

    @Test
    void setupGameOver() {
        GameOverState gameOverStateConst = new GameOverState(game, gui,10);
        Assertions.assertNotNull(gameOverStateConst.getStateView());
        Assertions.assertNotNull(gameOverStateConst.getStateController());

        Assertions.assertTrue(gameOverStateConst.getStateController() instanceof GameOverController);

        GameOverController stateController = (GameOverController) gameOverStateConst.getStateController();

        Assertions.assertEquals(1, stateController.getInputNotifier().getInputObservers().size());


        List<Button> options = stateController.getGameOverModel().getOptions();
        Assertions.assertEquals(StartCommand.class, options.get(0).getButtonCommand().getClass());
        Assertions.assertEquals(ExitCommand.class, options.get(1).getButtonCommand().getClass());
    }
}