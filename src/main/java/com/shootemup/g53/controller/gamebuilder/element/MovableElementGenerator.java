package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.MovableElement;

import java.util.Random;

public abstract class MovableElementGenerator extends ElementGenerator {

    private final double minSpeed;
    private final double maxSpeed;

    public MovableElementGenerator(GameController gameController,
                                   MovementStrategyFactory movementStrategyFactory,
                                   int xMinPos, int xMaxPos, double minSpeed, double maxSpeed) {
        this(new Random(), gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    MovableElementGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                            int xMinPos, int xMaxPos, double minSpeed, double maxSpeed) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos);
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    MovementStrategy generateMovementStrategy(MovableElement element) {
        return movementStrategyFactory.generate(element);
    }

    protected void setSpeed(MovableElement element) {
        element.setSpeed(rand.nextDouble()*(maxSpeed-minSpeed)+minSpeed);
    }
    
}
