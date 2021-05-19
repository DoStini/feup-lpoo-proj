package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.element.MovableElementController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.game.GameModel;

import java.util.Random;

public abstract class MovableElementGenerator extends ElementGenerator {

    private final int minSpeed;
    private final int maxSpeed;

    public MovableElementGenerator(GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        this(new Random(), gameController, xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    MovableElementGenerator(Random rand, GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        super(rand, gameController, xMinPos, xMaxPos);
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    protected void setSpeed(MovableElement element) {
        element.setSpeed(rand.nextInt(maxSpeed-minSpeed)+minSpeed);
    }
    
}
