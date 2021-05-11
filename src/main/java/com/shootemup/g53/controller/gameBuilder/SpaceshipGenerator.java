package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.element.Spaceship;

import java.util.Random;

public class SpaceshipGenerator extends MovableElementGenerator {


    private final int maxFireRate;
    private final int maxSize;

    public SpaceshipGenerator(int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int maxSize, int maxFireRate) {
        this(new Random(), xMinPos, xMaxPos, minSpeed, maxSpeed, maxSize, maxFireRate);
    }

    SpaceshipGenerator(Random rand, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int maxSize, int maxFireRate) {
        super(rand, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxFireRate = maxFireRate;
        this.maxSize = maxSize;
    }

    protected void setFireRate(Spaceship element) {
        element.setFireRate(rand.nextInt(maxFireRate-1)+1);
    }

    protected void setFireController(Spaceship spaceship) {
        spaceship.setFiringController(new StraightBulletStrategy(new FallDownMovement(), 2*spaceship.getSpeed()));
    }

    protected void setSize(Spaceship spaceship) {
        spaceship.setHeight(rand.nextInt(maxSize-2)+2);
    }

    @Override
    Element generateElement() {
        Spaceship spaceship = new Spaceship();
        setPosition(spaceship);
        setColor(spaceship);
        setSpeed(spaceship);
        setFireRate(spaceship);
        setMovement(spaceship);
        setSize(spaceship);
        setFireController(spaceship);
        return spaceship;
    }
}
