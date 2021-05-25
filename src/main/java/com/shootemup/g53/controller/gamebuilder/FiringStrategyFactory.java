package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.firing.*;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Spaceship;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FiringStrategyFactory {
    public enum Strategy {
        NORMAL,
        MULTIPLE,
        SPREAD_DOWN,
        SPREAD_UP,
        ALL_SPREAD
    }

    private final Random random;
    private final List<Strategy> strategies;

    private int bulletSpeedDiff = 4;
    private int maxFireRate = 10;
    private int minFireRate = 5;
    private int maxBullets = 4;

    public FiringStrategyFactory(List<Strategy> strategies) {
        this(new Random(), strategies);
    }

    public FiringStrategyFactory(Random random, List<Strategy> strategies) {
        this(random, strategies, 0);
    }

    public FiringStrategyFactory(Random random, List<Strategy> strategies, int currentRec) {
        this.random = random;
        this.strategies = strategies;
    }

    public FiringStrategy generate(Spaceship element) {
        Strategy strategy = strategies.get(random.nextInt(strategies.size()));
        switch (strategy){
            case NORMAL:
                return generateNormal(element);
            case MULTIPLE:
                return generateMultiple(element);
            case SPREAD_DOWN:
                return generateDownSpread(element);
            case SPREAD_UP:
                return generateUpSpread(element);
            case ALL_SPREAD:
                return generateAllSpread(element);
        }
        return null;
    }

    private FiringStrategy generateUpSpread(Spaceship element) {
        return new SpreadBulletUpStrategy(genSpeed(element), genFireRate());
    }

    private FiringStrategy generateDownSpread(Spaceship element) {
        return new SpreadBulletDownStrategy(genSpeed(element), genFireRate());
    }

    private FiringStrategy generateAllSpread(Spaceship element) {
        return new SpreadAllStrategy(genSpeed(element), genFireRate());
    }

    private FiringStrategy generateMultiple(Spaceship element) {
        return new MultipleMovingBulletStrategy(new FallDownMovement(),
                                                random.nextInt(maxBullets), -1,
                                                genSpeed(element),
                                                genFireRate());
    }

    private FiringStrategy generateNormal(Spaceship element) {
        MovementStrategyFactory movementStrategyFactory =
                new MovementStrategyFactory(Arrays.asList(MovementStrategyFactory.Strategy.DOWN,
                                                            MovementStrategyFactory.Strategy.DIAGONAL_DOWN));
        return new MovingBulletStrategy(movementStrategyFactory.generate(element),
                genSpeed(element),
                genFireRate());
    }

    private int genFireRate() {
        return random.nextInt(maxFireRate - minFireRate) + minFireRate;
    }

    private int genSpeed(Spaceship element) {
        return random.nextInt(bulletSpeedDiff) + element.getSpeed() + 2;
    }

}
