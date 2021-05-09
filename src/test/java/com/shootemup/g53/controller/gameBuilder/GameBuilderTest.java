package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameBuilderTest {

    private Random random;
    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

    }

    @Test
    void checkIfBuildingEnemies() {
        GameBuilder gameBuilder = new GameBuilder(random);
        GameModel gameModel = gameBuilder.buildGame(5,0,50,50);

        Mockito.verify(random, Mockito.times(10)).nextInt(Mockito.anyInt());
        assertEquals(gameModel.getEnemySpaceships().size(),5);
    }

    @Test
    void checkIfBuildingCoins() {
        GameBuilder gameBuilder = new GameBuilder(random);
        GameModel gameModel = gameBuilder.buildGame(0,5,50,50);

        Mockito.verify(random, Mockito.times(10)).nextInt(Mockito.anyInt());
        assertEquals(gameModel.getCoins().size(),5);
    }

    @Test
    void checkIfBuildingArenaWithCorrectSize() {
        GameBuilder gameBuilder = new GameBuilder();
        GameModel gameModel = gameBuilder.buildGame(0,0,50,50);

        Mockito.verify(random, Mockito.times(0)).nextInt(Mockito.anyInt());
        assertEquals(gameModel.getWidth(),50);
        assertEquals(gameModel.getHeight(),50);
    }
}
