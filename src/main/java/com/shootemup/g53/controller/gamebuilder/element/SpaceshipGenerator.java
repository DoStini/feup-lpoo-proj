package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.FiringStrategyFactory;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.CompositeMovement;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

import java.util.Arrays;
import java.util.Random;

public class SpaceshipGenerator extends MovableElementGenerator {


    private final int maxFireRate;
    private final int maxSize;
    private final int minSize;
    private final FiringStrategyFactory firingStrategyFactory;
    private final int maxDamage;
    private int minHealth = 5;
    private int maxHealth = 10;

    public void setMinHealth(int minHealth) {
        this.minHealth = minHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public SpaceshipGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory,
                              FiringStrategyFactory firingStrategyFactory,
                              int xMinPos, int xMaxPos, double minSpeed, double maxSpeed,
                              int minSize, int maxSize, int maxFireRate, int maxDamage) {
        this(new Random(), gameController, movementStrategyFactory, firingStrategyFactory, xMinPos, xMaxPos,
                minSpeed, maxSpeed, minSize, maxSize, maxFireRate, maxDamage);
    }

    SpaceshipGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                       FiringStrategyFactory firingStrategyFactory,
                       int xMinPos, int xMaxPos, double minSpeed, double maxSpeed, int minSize, int maxSize, int maxFireRate, int maxDamage) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxFireRate = maxFireRate;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.maxDamage = maxDamage;
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
        collider.setCategoryMask((short) (ColliderCategory.PLAYER.getBits() | ColliderCategory.PLAYER_BULLET.getBits()
                | ColliderCategory.SHIELD.getBits()));
        gameModel.addCollider(collider);
    }

    protected void setController(Spaceship spaceship) {
        MovementStrategy strategy = generateMovementStrategy(spaceship);

        FiringStrategy firingStrategy = setFiringStrategy(spaceship);
        SpaceshipController spaceshipController = new SpaceshipController(spaceship, firingStrategy, strategy,
                gameController.getBulletPoolController());
        gameController.addToControllerMap(spaceship, spaceshipController);
        gameController.addToCollisionMap(spaceship, spaceshipController);
    }

    private void setDamage(Spaceship spaceship) {
        spaceship.setBulletDamage(rand.nextInt(maxDamage - 1) + 1);
    }

    protected void setSize(Spaceship spaceship) {
        spaceship.setHeight(rand.nextInt(maxSize-minSize)+minSize);
    }

    private void setHealth(Spaceship spaceship) {
        spaceship.setHealth(rand.nextInt(maxHealth-minHealth)+minHealth);
    }

    @Override
    public void generateElement() {
        Spaceship spaceship = new Spaceship();
        setPosition(spaceship);
        setColor(spaceship);
        setSpeed(spaceship);
        setSize(spaceship);
        setDamage(spaceship);
        setHealth(spaceship);
        setController(spaceship);
        setCollider(spaceship);
        gameModel.addEnemy(spaceship);
    }
}
