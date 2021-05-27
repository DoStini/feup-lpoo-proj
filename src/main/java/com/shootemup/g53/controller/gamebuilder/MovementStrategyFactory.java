package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private final List<Strategy> strategies;
    private final int recursionLimit = 10;
    private final int currentRec;

    private int maxRadius = 5;
    private int minRadius = 3;
    private int minCircularSpeed = 30;
    private int maxCircularSpeed = 50;
    private int minBouncingLimit = 2;
    private int maxBouncingLimit = 8;
    private int maxChangingRate = 120;
    private int minChangingRate= 30;

    public MovementStrategyFactory(List<Strategy> strategies) {
        this(new Random(), strategies);
    }

    public MovementStrategyFactory(Random random, List<Strategy> strategies) {
        this(random, strategies, 0);
    }

    public MovementStrategyFactory(Random random, List<Strategy> strategies, int currentRec) {
        this.random = random;
        this.strategies = strategies;
        this.currentRec = currentRec;
    }

    public MovementStrategy generate(Element element) {
        Strategy strategy = strategies.get(random.nextInt(strategies.size()));
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

    private void addDownMovement() {
    }

    private List<MovementStrategy> compositeMovementStrategies(Element element) {
        MovementStrategyFactory factory = new MovementStrategyFactory(random, strategies, currentRec-1);
        List<MovementStrategy> strategyList = new ArrayList<>();
        if (currentRec > recursionLimit)
            return strategyList;
        strategyList.add(factory.generate(element));
        strategyList.add(factory.generate(element));
        return strategyList;
    }

    private MovementStrategy generateComposite(Element element) {
        List<MovementStrategy> strategyList = compositeMovementStrategies(element);

        return new CompositeMovement(strategyList);
    }

    private MovementStrategy generateChanging(Element element) {
        List<MovementStrategy> strategyList = compositeMovementStrategies(element);

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
        return new CircularMovement(random.nextInt(maxRadius - minRadius) + minRadius, 0,
                                random.nextInt(maxCircularSpeed - minCircularSpeed) + minCircularSpeed);
    }

    private MovementStrategy generateDown(Element element) {
        return new FallDownMovement();
    }

    protected List<Strategy> getStrategies() {
        return strategies;
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
