package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.GameBuilder;
import com.shootemup.g53.controller.infobar.InfoBarController;
import com.shootemup.g53.controller.observer.LifeController;
import com.shootemup.g53.controller.observer.ScoreController;
import com.shootemup.g53.controller.observer.WaveCompletionController;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.infobar.InfoBarModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;
import com.shootemup.g53.view.infobar.InfoBarViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayStateTest {

    private GameController gameController;
    private PlayerController playerController;
    private BulletPoolController bulletPoolController;
    private Viewer<GameModel> gameViewer;
    private LifeController lifeController;
    private WaveCompletionController waveCompletionController;
    private InfoBarViewer infoBarViewer;
    private InfoBarModel infoBarModel;
    private ScoreController scoreController;
    private GameModel gameModel;
    private GameBuilder gameBuilder;
    private Gui gui;
    private Game game;
    private Player player;
    private InfoBarController infoBarController;
    private PlayState playState;

    @BeforeEach
    void setup() {
        gameModel = Mockito.spy(new GameModel(50, 50));
        gui = Mockito.mock(Gui.class);
        game = Mockito.mock(Game.class);
        bulletPoolController = Mockito.mock(BulletPoolController.class);
        gameController = Mockito.spy(new GameController(gameModel, bulletPoolController));
        gameBuilder = Mockito.spy(new GameBuilder(gui, this.gameController, 10));
        gameViewer = Mockito.mock(GameViewer.class);
        infoBarViewer = Mockito.mock(InfoBarViewer.class);
        infoBarController = Mockito.mock(InfoBarController.class);
        waveCompletionController = Mockito.mock(WaveCompletionController.class);

        playState = new PlayState(game, gameModel, gui);
        playState.setGameController(gameController);
        playState.setGameBuilder(gameBuilder);
        playState.setGameViewer(gameViewer);
        playState.setInfoBarController(infoBarController);
        playState.setInfoBarViewer(infoBarViewer);

    }

    @Test
    void runFinished() {
        playState.setTimerThread(Mockito.mock(Thread.class));

        Mockito.doReturn(false, false, false, true)
                .when(gameModel).isGameFinished();

        playState.run();

        Mockito.verify(gameBuilder, Mockito.times(4)).handle(Mockito.anyLong());

        Mockito.verify(gameController, Mockito.times(4)).handle(Mockito.anyLong());

        Mockito.verify(gameController, Mockito.times(4)).handleKeyPress(gui);
        Mockito.verify(infoBarController, Mockito.times(4)).handle(Mockito.anyInt());
        Mockito.verify(gameController, Mockito.times(4)).isGameFinished();
        Mockito.verify(gameViewer, Mockito.times(3)).draw(gameModel);
        Mockito.verify(infoBarViewer, Mockito.times(3)).draw(infoBarModel);

        Mockito.verify(game, Mockito.times(1))
                .changeState(Mockito.any(GameOverState.class));

        Mockito.verify(gui, Mockito.times(4)).clear();
        Mockito.verify(gui, Mockito.times(3)).refresh();
    }

    @Test
    void runPaused() {
        Thread thread = Mockito.mock(Thread.class);
        playState.setTimerThread(thread);

        Mockito.when(gameModel.isGameFinished()).thenReturn(false);

        Mockito.doReturn(false, false, false, true)
                .when(gameModel).isPaused();

        playState.run();


        Mockito.verify(gameBuilder, Mockito.times(4)).handle(Mockito.anyLong());
        Mockito.verify(infoBarController, Mockito.times(4)).handle(Mockito.anyInt());
        Mockito.verify(gameController, Mockito.times(4)).handle(Mockito.anyLong());
        Mockito.verify(gameController, Mockito.times(4)).handleKeyPress(gui);
        Mockito.verify(gameController, Mockito.times(4)).isGameFinished();
        Mockito.verify(gameViewer, Mockito.times(3)).draw(gameModel);
        Mockito.verify(infoBarViewer, Mockito.times(3)).draw(infoBarModel);

        Mockito.verify(game, Mockito.times(1))
                .changeState(Mockito.any(PauseState.class));
        Mockito.verify(thread, Mockito.times(1))
                .interrupt();
        Mockito.verify(thread, Mockito.times(1))
                .start();
        Mockito.verify(gameController, Mockito.times(1))
                .unpause();

        Mockito.verify(gui, Mockito.times(4)).clear();
        Mockito.verify(gui, Mockito.times(3)).refresh();
    }

    @Test
    void timerThreadFrame() {
        Mockito.doReturn(false, false, false, true)
                .when(gameModel).isGameFinished();

        Thread thread = playState.getTimerThread();

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) { }

        Assertions.assertEquals(3, playState.getFrame());
        Assertions.assertEquals(0, playState.getSeconds());
    }

    @Test
    void timerThreadSeconds() {
        Mockito.doReturn(false, false, true)
                .when(gameModel).isGameFinished();
        playState.setFrameSleep(1000);

        Thread thread = playState.getTimerThread();

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) { }

        Assertions.assertEquals(2, playState.getFrame());
        Assertions.assertEquals(2, playState.getSeconds());
    }

    @Test
    void setupGame() {
        PlayState playState = new PlayState(game, gameModel, gui);

        Assertions.assertEquals(10, playState.getGameBuilder().getBaseSkip());
        Assertions.assertNotNull(playState.getGameViewer());
        Assertions.assertEquals(10, playState.getInfoBarViewer().getInfoBarWidth());
        Assertions.assertEquals(1,
                playState.getGameBuilder().getWaveFactory().getWaveCompletionController().getWaveCompletionObservers().size());

        InfoBarController infoBarController = playState.getInfoBarController();
        Assertions.assertNotNull(infoBarController);
        Assertions.assertEquals(1, infoBarController.getInfoBarModel().getScore());
        Assertions.assertEquals(20, infoBarController.getInfoBarModel().getCurrentLives());
        Assertions.assertEquals(20, infoBarController.getInfoBarModel().getMaxLives());
    }
}