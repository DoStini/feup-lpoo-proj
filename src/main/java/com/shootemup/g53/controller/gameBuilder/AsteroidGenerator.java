package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Element;

import java.util.Random;

public class AsteroidGenerator extends MovableElementGenerator {

    private final int maxRadius;

    public AsteroidGenerator(int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int maxRadius) {
        this(new Random(), xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius);
    }

    AsteroidGenerator(Random rand, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                      int maxRadius) {
        super(rand, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxRadius = maxRadius;
    }

    protected void setRadius(Asteroid asteroid) {
        asteroid.setRadius(rand.nextInt(maxRadius-1)+1);
    }

    @Override
    Element generateElement() {
        Asteroid asteroid = new Asteroid();
        setColor(asteroid);
        setPosition(asteroid);
        setSpeed(asteroid);
        setRadius(asteroid);
        setMovement(asteroid);
        return asteroid;
    }
}
