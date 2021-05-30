package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GameDirectorTest {
    GameModelBuilder gameModelBuilder;
    GameControllerBuilder gameControllerBuilder;
    GameModel gameModel;
    Player player;
    Background background;

    @BeforeEach
    void setUp() {
        gameModelBuilder = Mockito.mock(GameModelBuilder.class);
        gameControllerBuilder = Mockito.mock(GameControllerBuilder.class);
        gameModel = Mockito.mock(GameModel.class);
        player = Mockito.mock(Player.class);
        background = Mockito.mock(Background.class);

        Mockito.when(gameModelBuilder.getGameModel()).thenReturn(gameModel);
        Mockito.when(gameModel.getPlayer()).thenReturn(player);
        Mockito.when(gameModel.getBackground()).thenReturn(background);
    }

    @Test
    void constructor() {
        GameDirector gameDirector = new GameDirector(gameModelBuilder, gameControllerBuilder);

        Assertions.assertSame(gameModelBuilder, gameDirector.modelBuilder);
        Assertions.assertSame(gameControllerBuilder, gameDirector.controllerBuilder);
    }

    @Test
    void make() {
        InOrder inOrder = Mockito.inOrder(gameModelBuilder, gameControllerBuilder);

        GameDirector gameDirector = new GameDirector(gameModelBuilder, gameControllerBuilder);
        gameDirector.make(10, 25);

        inOrder.verify(gameModelBuilder, Mockito.times(1)).setHeight(25);
        inOrder.verify(gameModelBuilder, Mockito.times(1)).setWidth(10);
        inOrder.verify(gameModelBuilder, Mockito.times(1)).setupPlayer();
        inOrder.verify(gameModelBuilder, Mockito.times(1)).setupBackground();

        inOrder.verify(gameControllerBuilder, Mockito.times(1)).setupModel(gameModel);
        inOrder.verify(gameControllerBuilder, Mockito.times(1)).setupPlayer(player);
        inOrder.verify(gameControllerBuilder, Mockito.times(1)).setupBackground(background);

        inOrder.verifyNoMoreInteractions();
    }
}