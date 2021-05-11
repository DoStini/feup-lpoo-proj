package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.Spaceship;

import java.util.Random;

public class CoinGenerator extends MovableElementGenerator {


    private final int minVal;
    private final int maxVal;
    private final int maxRadius;

    public CoinGenerator(int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int maxRadius, int minVal, int maxVal) {
        this(new Random(), xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius, minVal, maxVal);
    }

    CoinGenerator(Random rand, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                  int maxRadius, int minVal, int maxVal) {
        super(rand, xMinPos, xMaxPos, minSpeed, maxSpeed);
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
    Element generateElement() {
        Coin coin = new Coin();
        setPosition(coin);
        setColor(coin);
        setSpeed(coin);
        setRadius(coin);
        setValue(coin);
        setMovement(coin);
        return coin;
    }
}
