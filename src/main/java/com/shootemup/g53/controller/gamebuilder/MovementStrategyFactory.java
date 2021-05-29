package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MovementStrategyFactory {

    public enum Strategy {
        UP,
        DOWN,
        CIRCULAR,
        DIAGONAL_BOUNCE,
        DIAGONAL_DOWN,
        CHANGING,
        COMPOSITE
    }

    private final Random random;
    private List<Strategy> strategies;

    private double maxRadius = 5;
    private double minRadius = 3;
    private double minCircularSpeed = 30;
    private double maxCircularSpeed = 50;
    private int minBouncingLimit = 2;
    private int maxBouncingLimit = 8;
    private int maxChangingRate = 120;
    private int minChangingRate = 30;
    private int minComposite = 2;
    private int maxComposite = 4;

    public MovementStrategyFactory(List<Strategy> strategies) {
        this(new Random(), strategies);
    }

    public MovementStrategyFactory(Random random, List<Strategy> strategies) {
        this.random = random;
        this.strategies = strategies;
    }

    public MovementStrategy generate(Element element) {
        if (strategies.isEmpty())
            return null;
        int r = random.nextInt(strategies.size());
        Strategy strategy = strategies.get(r);
        switch (strategy){
            case DOWN:
                return generateDown(element);
            case UP:
                return generateUp(element);
            case CIRCULAR:
                return generateCircular(element);
            case DIAGONAL_DOWN:
                return generateDiagonalDown(element);
            case DIAGONAL_BOUNCE:
                return generateDiagonalBounce(element);
            case CHANGING:
                return generateChanging(element);
            case COMPOSITE:
                return generateComposite(element);
        }
        return null;
    }

    public MovementStrategy ensureFallDown(MovementStrategy movementStrategy) {
        if (movementStrategy instanceof FallDownMovement)
            return movementStrategy;
        else if (movementStrategy instanceof CompositeMovement) {
            CompositeMovement strategy = (CompositeMovement) movementStrategy;
            if (!strategy.contains(new FallDownMovement()))
                strategy.addMovement(new FallDownMovement());
            return strategy;
        }
        else if (movementStrategy instanceof ChangingMovement) {
            ChangingMovement strategy = (ChangingMovement) movementStrategy;
            if (!strategy.contains(new FallDownMovement()))
                strategy.addMovement(new FallDownMovement());
            return strategy;
        }

        List<MovementStrategy> strategies = new ArrayList<>();
        strategies.add(movementStrategy);
        strategies.add(new FallDownMovement());

        return new CompositeMovement(strategies);
    }

    private List<MovementStrategy> compositeMovementStrategies(Element element) {
        List<Strategy> swap = strategies;
        strategies = new ArrayList<>(strategies).stream()
                .filter(el -> el != Strategy.COMPOSITE && el != Strategy.CHANGING).collect(Collectors.toList());

        List<MovementStrategy> strategyList = new ArrayList<>();

        int numComposites = random.nextInt(maxComposite-minComposite)+minComposite;

        for (int i = 0; i < numComposites; i++) {
            MovementStrategy movementStrategy = generate(element);
            if (movementStrategy != null)
                strategyList.add(movementStrategy);
        }

        strategies = swap;

        return strategyList;
    }

    private MovementStrategy generateComposite(Element element) {
        List<MovementStrategy> strategyList = compositeMovementStrategies(element);
        if (strategyList.isEmpty())
            return null;

        return new CompositeMovement(strategyList);
    }

    private MovementStrategy generateChanging(Element element) {
        List<MovementStrategy> strategyList = compositeMovementStrategies(element);
        if (strategyList.isEmpty())
            return null;

        return new ChangingMovement(random.nextInt(maxChangingRate - minChangingRate)+minChangingRate,
                strategyList);
    }

    private MovementStrategy generateDiagonalBounce(Element element) {
        int bounce = random.nextInt(maxBouncingLimit - minBouncingLimit)+minBouncingLimit;
        if (random.nextInt() % 2 == 0)
            return new DiagonalBounceMovement(
                    bounce, bounce, Direction.DOWN_RIGHT, element.getPosition());
        else
            return new DiagonalBounceMovement(
                    bounce, bounce, Direction.DOWN_LEFT, element.getPosition());
    }

    private MovementStrategy generateDiagonalDown(Element element) {
        if (random.nextInt() % 2 == 0)
            return new DiagonalDownRightMovement();
        else
            return new DiagonalDownLeftMovement();
    }

    private MovementStrategy generateUp(Element element) {
        return new MoveUpwardsMovement();
    }

    private MovementStrategy generateCircular(Element element) {
        return new CircularMovement(random.nextDouble()*(maxRadius - minRadius) + minRadius, 0,
                                random.nextDouble()*(maxCircularSpeed - minCircularSpeed) + minCircularSpeed);
    }

    private MovementStrategy generateDown(Element element) {
        return new FallDownMovement();
    }

    public void setMaxRadius(int maxRadius) {
        this.maxRadius = maxRadius;
    }

    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
    }

    public void setMinCircularSpeed(int minCircularSpeed) {
        this.minCircularSpeed = minCircularSpeed;
    }

    public void setMaxCircularSpeed(int maxCircularSpeed) {
        this.maxCircularSpeed = maxCircularSpeed;
    }

    public void setMinBouncingLimit(int minBouncingLimit) {
        this.minBouncingLimit = minBouncingLimit;
    }

    public void setMaxBouncingLimit(int maxBouncingLimit) {
        this.maxBouncingLimit = maxBouncingLimit;
    }

    public void setMaxChangingRate(int maxChangingRate) {
        this.maxChangingRate = maxChangingRate;
    }

    public void setMinChangingRate(int minChangingRate) {
        this.minChangingRate = minChangingRate;
    }
}
