package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;

import java.util.Random;

public class SpaceshipGenerator extends MovableElementGenerator {


    private final int maxFireRate;
    private final int maxSize;
    private final int minSize;

    public SpaceshipGenerator(GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int minSize, int maxSize, int maxFireRate) {
        this(new Random(), gameController, xMinPos, xMaxPos, minSpeed, maxSpeed, minSize, maxSize, maxFireRate);
    }

    SpaceshipGenerator(Random rand, GameController gameController,  int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int minSize, int maxSize, int maxFireRate) {
        super(rand, gameController, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxFireRate = maxFireRate;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    protected FiringStrategy setFiringStrategy(Spaceship spaceship) {
        return new StraightBulletStrategy(new FallDownMovement(),
                        2*spaceship.getSpeed(), rand.nextInt(maxFireRate-1)+1);
    }

    protected void setController(Spaceship spaceship) {
        MovementStrategy strategy = new FallDownMovement();
        FiringStrategy firingStrategy = setFiringStrategy(spaceship);
        gameController.addToControllerMap(spaceship, new SpaceshipController(spaceship, firingStrategy, strategy,
                gameController.getBulletPoolController()));
    }

    protected void setSize(Spaceship spaceship) {
        spaceship.setHeight(rand.nextInt(maxSize-minSize)+minSize);
    }

    @Override
    public void generateElement() {
        Spaceship spaceship = new Spaceship();
        setPosition(spaceship);
        setColor(spaceship);
        setSpeed(spaceship);
        setController(spaceship);
        setSize(spaceship);
        gameModel.addEnemy(spaceship);
    }
}
