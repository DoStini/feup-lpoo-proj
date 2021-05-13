package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.game.GameModel;

import java.util.Random;

public class CoinGenerator extends MovableElementGenerator {


    private final int minVal;
    private final int maxVal;
    private final int maxRadius;

    public CoinGenerator(GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int maxRadius, int minVal, int maxVal) {
        this(new Random(), gameController, xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius, minVal, maxVal);
    }

    CoinGenerator(Random rand, GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                  int maxRadius, int minVal, int maxVal) {
        super(rand, gameController, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.maxRadius = maxRadius;
    }

    protected void setRadius(Coin coin) {
        coin.setRadius(rand.nextInt(maxRadius-1)+1);
    }

    protected void setValue(Coin coin) {
        // coin.setValue(rand.nextInt(maxVal - minVal)+minVal);
    }

    @Override
    public void generateElement() {
        Coin coin = new Coin();
        setPosition(coin);
        setRadius(coin);
        setValue(coin);
        setMovement(coin);
        gameModel.addCoin(coin);
    }
}
