package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.element.Spaceship;

import java.util.Random;

public abstract class MovableElementGenerator extends ElementGenerator {

    private final int minSpeed;
    private final int maxSpeed;

    public MovableElementGenerator(int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        this(new Random(), xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    MovableElementGenerator(Random rand, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        super(rand, xMinPos, xMaxPos);
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    protected void setSpeed(MovableElement element) {
        element.setSpeed(rand.nextInt(maxSpeed-minSpeed)+minSpeed);
    }

    protected void setMovement(MovableElement element) {
        element.setMovementController(new FallDownMovement());
    }
}
