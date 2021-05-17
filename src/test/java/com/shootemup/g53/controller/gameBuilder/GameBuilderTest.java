package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameBuilderTest {

    private Random random;
    private Gui gui;
    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gui = Mockito.mock(Gui.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

    }

    @Test
    void checkIfBuildingEnemies() {
        GameBuilder gameBuilder = new GameBuilder(random);
        GameController gameController = gameBuilder.buildGame(5,0,50,50, gui);

        Mockito.verify(random, Mockito.times(5 * 3)).nextInt(Mockito.anyInt());
        assertEquals(gameController.getGameModel().getEnemySpaceships().size(),5);
    }

    @Test
    void checkIfBuildingCoins() {
        GameBuilder gameBuilder = new GameBuilder(random);
        GameController gameController = gameBuilder.buildGame(0,5,50,50,gui);
        GameModel gameModel = gameController.getGameModel();
        Mockito.verify(random, Mockito.times(10)).nextInt(Mockito.anyInt());
        assertEquals(gameModel.getCoins().size(),5);
    }

    @Test
    void checkIfBuildingArenaWithCorrectSize() {
        GameBuilder gameBuilder = new GameBuilder();
        GameController gameController = gameBuilder.buildGame(0,0,50,50,gui);
        GameModel gameModel = gameController.getGameModel();

        Mockito.verify(random, Mockito.times(0)).nextInt(Mockito.anyInt());
        assertEquals(gameModel.getWidth(),50);
        assertEquals(gameModel.getHeight(),50);
    }
}
