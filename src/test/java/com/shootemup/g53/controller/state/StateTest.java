package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.game.GameController;

import com.shootemup.g53.controller.observer.LifeController;
import com.shootemup.g53.controller.observer.ScoreController;
import com.shootemup.g53.controller.observer.WaveCompletionController;
import com.shootemup.g53.controller.gamebuilder.GameBuilder;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class StateTest {
    private GameController gameController;
    private PlayerController playerController;
    private Viewer<GameModel> gameViewer;
    private LifeController lifeController;
    private WaveCompletionController waveCompletionController;
    private ScoreController scoreController;
    private GameModel gameModel;
    private GameBuilder gameBuilder;
    private Gui gui;
    private Game game;
    private Player player;
    long frame = 0;

    @BeforeEach
    void setup() {
        playerController = Mockito.mock(PlayerController.class);
        gameController = Mockito.mock(GameController.class);
        gameModel = Mockito.mock(GameModel.class);
        player = Mockito.mock(Player.class);
        lifeController = Mockito.mock(LifeController.class);
        scoreController = Mockito.mock(ScoreController.class);
        waveCompletionController = Mockito.mock(WaveCompletionController.class);

        Mockito.when(player.getPosition()).thenReturn(new Position(0,0));
        Mockito.when(gameModel.getPlayer()).thenReturn(player);
        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);
        Mockito.when(gameController.isGameFinished()).thenReturn(true);
        Mockito.when(gameController.getElementController(player)).thenReturn(playerController);
        Mockito.when(gameController.getPlayerController()).thenReturn(playerController);
        Mockito.when(gameController.getScoreController()).thenReturn(scoreController);
        Mockito.when(playerController.getScoreController()).thenReturn(scoreController);
        Mockito.when(playerController.getLifeController()).thenReturn(lifeController);
        gameViewer = Mockito.mock(GameViewer.class);
        gameBuilder = Mockito.mock(GameBuilder.class);
        gui = Mockito.mock(Gui.class);

        game = Mockito.mock(Game.class);
    }

    @Test
    void testStateRun(){
        /*
        PlayState playState = new PlayState(game, gameModel, gui);
        playState.setGameController(gameController);
        playState.setGameBuilder(gameBuilder);

        playState.run();

<<<<<<< HEAD
        Mockito.verify(gameBuilder, Mockito.times(1)).handle(1);
        Mockito.verify(gameController,Mockito.times(1)).handle();
=======
        Mockito.verify(gameBuilder, Mockito.times(1)).buildGame(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.any());
        Mockito.verify(gameController,Mockito.times(1)).handle(frame);
>>>>>>> develop
        Mockito.verify(gameController,Mockito.times(1)).handleKeyPress(gui);
    */
    }

    @Test
    void gameIsOverStateTest(){


    }
}
