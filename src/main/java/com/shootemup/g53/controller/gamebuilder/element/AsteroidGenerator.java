package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.AsteroidController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public class AsteroidGenerator extends MovableElementGenerator {

    private final int maxRadius;

    public AsteroidGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory,
                             int xMinPos, int xMaxPos, double minSpeed, double maxSpeed, int maxRadius) {
        this(new Random(), gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius);
    }

    AsteroidGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                      int xMinPos, int xMaxPos, double minSpeed, double maxSpeed,
                      int maxRadius) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxRadius = maxRadius;
    }

    protected void setRadius(Asteroid asteroid) {
        asteroid.setRadius(rand.nextInt(maxRadius-1)+1);
    }


    protected void setMovement(Asteroid asteroid) {
        AsteroidController asteroidController = new AsteroidController(asteroid, generateMovementStrategy(asteroid));
        gameController.addToControllerMap(asteroid, asteroidController);
        gameController.addToCollisionMap(asteroid, asteroidController);
    }

    protected void setCollider(Asteroid asteroid) {
        BodyCollider collider = new LineCompositeFactory().createFromCircle(asteroid, new Position(0, 0),
                asteroid.getRadius());
        collider.setCategory(ColliderCategory.ENEMY);
        gameModel.addCollider(collider);
    }

    @Override
    public void generateElement() {
        Asteroid asteroid = new Asteroid();
        setPosition(asteroid);
        setRadius(asteroid);
        setMovement(asteroid);
        setCollider(asteroid);
        gameModel.addAsteroid(asteroid);
    }
}
