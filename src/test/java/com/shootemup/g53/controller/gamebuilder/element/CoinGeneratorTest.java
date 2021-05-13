package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class CoinGeneratorTest {
    private Random random;
    private Coin coin;
    private CoinGenerator coinGenerator;
    private GameController gameController;
    private GameModel gameModel;

    private int xMinPos = 0,
            xMaxPos = 10,
            minSpeed = 2,
            maxSpeed = 10,
            maxRadius = 4,
            minVal = 2,
            maxVal = 3;

    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gameController = Mockito.mock(GameController.class);
        coin = Mockito.mock(Coin.class);
        gameModel = Mockito.mock(GameModel.class);

        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);

        coinGenerator = new CoinGenerator(random, gameController,
                xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius, minVal, maxVal);
    }



    @Test
    void setRadius() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        coinGenerator.setRadius(coin);

        Mockito.verify(random, Mockito.times(1)).nextInt(maxRadius-1);
        Mockito.verify(coin, Mockito.times(1)).setRadius(randomVal + 1);
    }


    @Test
    void generateElement() {
        int randomVal = 2;
        coinGenerator.generateElement();

        Mockito.verify(gameModel, Mockito.times(1)).addEnemy(Mockito.any());
    }
}