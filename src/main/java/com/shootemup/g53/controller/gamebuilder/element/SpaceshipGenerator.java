package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.FiringStrategyFactory;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.CompositeMovement;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

import java.util.Arrays;
import java.util.Random;

public class SpaceshipGenerator extends MovableElementGenerator {


    private final int maxFireRate;
    private final int maxSize;
    private final int minSize;
    private final FiringStrategyFactory firingStrategyFactory;

    public SpaceshipGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory,
                              FiringStrategyFactory firingStrategyFactory,
                              int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                              int minSize, int maxSize, int maxFireRate) {
        this(new Random(), gameController, movementStrategyFactory, firingStrategyFactory, xMinPos, xMaxPos,
                minSpeed, maxSpeed, minSize, maxSize, maxFireRate);
    }

    SpaceshipGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                       FiringStrategyFactory firingStrategyFactory,
                       int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int minSize, int maxSize, int maxFireRate) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxFireRate = maxFireRate;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.firingStrategyFactory = firingStrategyFactory;
    }

    protected FiringStrategy setFiringStrategy(Spaceship spaceship) {
        return firingStrategyFactory.generate(spaceship);
    }

    protected void setCollider(Spaceship spaceship) {
        BodyCollider collider =
                new LineCompositeFactory().createFromInvertedIsoscelesTriangle(spaceship, new Position(0, 0),
                        spaceship.getHeight());
        collider.setCategory(ColliderCategory.ENEMY);
        collider.setCategoryMask((short) (ColliderCategory.PLAYER.getBits() | ColliderCategory.PLAYER_BULLET.getBits()));
        gameModel.addCollider(collider);
    }

    protected void setController(Spaceship spaceship) {
        MovementStrategy randomStrategy = generateMovementStrategy(spaceship), strategy;

        if (!(randomStrategy instanceof FallDownMovement))
            strategy = new CompositeMovement(Arrays.asList(randomStrategy, new FallDownMovement()));
        else
            strategy = randomStrategy;


        FiringStrategy firingStrategy = setFiringStrategy(spaceship);
        SpaceshipController spaceshipController = new SpaceshipController(spaceship, firingStrategy, strategy,
                gameController.getBulletPoolController());
        gameController.addToControllerMap(spaceship, spaceshipController);
        gameController.addToCollisionMap(spaceship, spaceshipController);
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
        setSize(spaceship);
        setController(spaceship);
        setCollider(spaceship);
        gameModel.addEnemy(spaceship);
    }
}
