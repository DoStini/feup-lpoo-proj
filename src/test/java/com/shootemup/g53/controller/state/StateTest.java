package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gameBuilder.GameBuilder;

import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class StateTest {
    private GameController gameController;
    private PlayerController playerController;
    private Viewer<GameModel> gameViewer;
    private GameModel gameModel;
    private GameBuilder gameBuilder;
    private Gui gui;
    private Game game;
    private Spaceship player;
    long frame = 0;

    @BeforeEach
    void setup() {
        playerController = Mockito.mock(PlayerController.class);
        gameController = Mockito.mock(GameController.class);
        gameModel = Mockito.mock(GameModel.class);
        player = Mockito.mock(Spaceship.class);
        Mockito.when(player.getPosition()).thenReturn(new Position(0,0));
        Mockito.when(gameModel.getPlayer()).thenReturn(player);
        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);
        Mockito.when(gameController.isGameFinished()).thenReturn(true);
        Mockito.when(gameController.getElementController(player)).thenReturn(playerController);
        gameViewer = Mockito.mock(GameViewer.class);
        gameBuilder = Mockito.mock(GameBuilder.class);
        Mockito.when(gameBuilder.buildGame(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.any())).thenReturn(gameController);
        gui = Mockito.mock(Gui.class);

        game = Mockito.mock(Game.class);
    }

    @Test
    void testStateRun(){
        PlayState playState = new PlayState(game,gui,gameBuilder);
        playState.run();

        Mockito.verify(gameBuilder, Mockito.times(1)).buildGame(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.any());
        Mockito.verify(gameController,Mockito.times(1)).handle(frame);
        Mockito.verify(gameController,Mockito.times(1)).handleKeyPress(gui);

        

    }

    @Test
    void gameIsOverStateTest(){


    }
}
