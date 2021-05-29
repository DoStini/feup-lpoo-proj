package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MovementStrategyFactoryTest {

    List<MovementStrategyFactory.Strategy> strategies;
    Element element;

    @BeforeEach
    void setUp() {
        element = Mockito.mock(Element.class);
    }

    @Test
    void generateDown() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.DOWN);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(strategies);

        Assertions.assertEquals(FallDownMovement.class, movementStrategyFactory.generate(element).getClass());
    }

    @Test
    void generateUp() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.UP);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(strategies);

        Assertions.assertEquals(MoveUpwardsMovement.class, movementStrategyFactory.generate(element).getClass());
    }

    @Test
    void generateCircularDefault() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.CIRCULAR);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(0.5);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);

        MovementStrategy movementStrategy = movementStrategyFactory.generate(element);

        Assertions.assertEquals(CircularMovement.class, movementStrategy.getClass());
        Mockito.verify(random, Mockito.times(2)).nextDouble();

        CircularMovement circularMovement = (CircularMovement) movementStrategy;

        Assertions.assertEquals(4, circularMovement.getRadius());
        Assertions.assertEquals(Math.toRadians(40), circularMovement.getAngularSpeed());
    }

    @Test
    void generateCircularBuilder() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.CIRCULAR);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(0.8);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);
        movementStrategyFactory.setMaxCircularSpeed(10);
        movementStrategyFactory.setMinCircularSpeed(5);
        movementStrategyFactory.setMaxRadius(3);
        movementStrategyFactory.setMinRadius(1);

        MovementStrategy movementStrategy = movementStrategyFactory.generate(element);

        Assertions.assertEquals(CircularMovement.class, movementStrategy.getClass());
        Mockito.verify(random, Mockito.times(2)).nextDouble();

        CircularMovement circularMovement = (CircularMovement) movementStrategy;

        Assertions.assertEquals(2.6, circularMovement.getRadius());
        Assertions.assertEquals(Math.toRadians(9), circularMovement.getAngularSpeed());
    }

    @Test
    void generateDiagonalDown() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.DIAGONAL_DOWN);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(2);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);

        Assertions.assertEquals(DiagonalDownRightMovement.class, movementStrategyFactory.generate(element).getClass());

        Mockito.when(random.nextInt()).thenReturn(1);
        Assertions.assertEquals(DiagonalDownLeftMovement.class, movementStrategyFactory.generate(element).getClass());

    }

    @Test
    void generateBounceDefaults() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.DIAGONAL_BOUNCE);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(2);
        Position position = new Position(5, 5);
        Mockito.when(element.getPosition()).thenReturn(position);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);

        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0, 4);

        MovementStrategy movementStrategy =  movementStrategyFactory.generate(element);

        Assertions.assertEquals(DiagonalBounceMovement.class, movementStrategy.getClass());
        DiagonalBounceMovement bounceMovement = (DiagonalBounceMovement) movementStrategy;

        Assertions.assertEquals(position, bounceMovement.getInitalPosition());
        Assertions.assertEquals(6, bounceMovement.getxLeftLimit());
        Assertions.assertEquals(6, bounceMovement.getxLeftLimit());
        Assertions.assertEquals(6, bounceMovement.getxRightLimit());
        Assertions.assertEquals(Direction.DOWN_RIGHT, bounceMovement.getDirection());
        Mockito.verify(random, Mockito.times(1)).nextInt(6);
    }

    @Test
    void generateBounceBuilder() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.DIAGONAL_BOUNCE);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(1);
        Position position = new Position(5, 5);
        Mockito.when(element.getPosition()).thenReturn(position);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);
        movementStrategyFactory.setMinBouncingLimit(4);
        movementStrategyFactory.setMaxBouncingLimit(10);


        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0, 5);

        MovementStrategy movementStrategy =  movementStrategyFactory.generate(element);

        Assertions.assertEquals(DiagonalBounceMovement.class, movementStrategy.getClass());
        DiagonalBounceMovement bounceMovement = (DiagonalBounceMovement) movementStrategy;

        Assertions.assertEquals(position, bounceMovement.getInitalPosition());
        Assertions.assertEquals(9, bounceMovement.getxLeftLimit());
        Assertions.assertEquals(9, bounceMovement.getxRightLimit());
        Assertions.assertEquals(Direction.DOWN_LEFT, bounceMovement.getDirection());
    }

    @Test
    void generateInvalidCompositeChanging() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.COMPOSITE, MovementStrategyFactory.Strategy.CHANGING);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(0);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);
        Assertions.assertNull(movementStrategyFactory.generate(element));
    }

    @Test
    void generateInvalid() {
        strategies = new ArrayList<>();
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(0);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);
        Assertions.assertNull(movementStrategyFactory.generate(element));
    }

    @Test
    void composite() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.COMPOSITE,
                MovementStrategyFactory.Strategy.CHANGING,
                MovementStrategyFactory.Strategy.DOWN,
                MovementStrategyFactory.Strategy.CIRCULAR);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0, 0, 0, 1);


        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);
        MovementStrategy movementStrategy = movementStrategyFactory.generate(element);
        Assertions.assertEquals(CompositeMovement.class, movementStrategy.getClass());

        CompositeMovement composite = (CompositeMovement) movementStrategy;

        Assertions.assertEquals(2, composite.getControllers().size());
        Assertions.assertEquals(FallDownMovement.class, composite.getControllers().get(0).getClass());
        Assertions.assertEquals(CircularMovement.class, composite.getControllers().get(1).getClass());
        Mockito.verify(random, Mockito.times(3)).nextInt(2);
    }

    @Test
    void changing() {
        strategies = Arrays.asList(MovementStrategyFactory.Strategy.COMPOSITE,
                MovementStrategyFactory.Strategy.CHANGING,
                MovementStrategyFactory.Strategy.DOWN,
                MovementStrategyFactory.Strategy.CIRCULAR);
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1, 0, 0, 1, 80);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(random, strategies);
        MovementStrategy movementStrategy = movementStrategyFactory.generate(element);
        Assertions.assertEquals(ChangingMovement.class, movementStrategy.getClass());

        ChangingMovement changing = (ChangingMovement) movementStrategy;

        Assertions.assertEquals(2, changing.getControllers().size());
        Assertions.assertEquals(110, changing.getChangingRate());
        Assertions.assertEquals(FallDownMovement.class, changing.getControllers().get(0).getClass());
        Assertions.assertEquals(CircularMovement.class, changing.getControllers().get(1).getClass());
        Mockito.verify(random, Mockito.times(1)).nextInt(90);
    }

    @Test
    void ensureFallDown() {
        MovementStrategy movementStrategy = Mockito.mock(FallDownMovement.class);
        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(strategies);
        Assertions.assertEquals(movementStrategy, movementStrategyFactory.ensureFallDown(movementStrategy));
    }

    @Test
    void ensureFallDownNonFallDown() {
        MovementStrategy movementStrategy = Mockito.mock(CircularMovement.class);
        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(strategies);

        MovementStrategy result =  movementStrategyFactory.ensureFallDown(movementStrategy);

        Assertions.assertEquals(CompositeMovement.class, result.getClass());

        CompositeMovement composite = (CompositeMovement) result;

        Assertions.assertEquals(2, composite.getControllers().size());
        Assertions.assertEquals(Mockito.mock(CircularMovement.class).getClass(), composite.getControllers().get(0).getClass());
        Assertions.assertEquals(FallDownMovement.class, composite.getControllers().get(1).getClass());
    }

    @Test
    void ensureFallDownChanging() {

        List<MovementStrategy> movementStrategies = new ArrayList<>();
        movementStrategies.add(Mockito.mock(CircularMovement.class));

        MovementStrategy movementStrategy =
                new ChangingMovement(0, movementStrategies);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(strategies);

        MovementStrategy result =  movementStrategyFactory.ensureFallDown(movementStrategy);

        Assertions.assertEquals(movementStrategy, result);

        ChangingMovement changing = (ChangingMovement) result;

        Assertions.assertEquals(2, changing.getControllers().size());
        Assertions.assertEquals(Mockito.mock(CircularMovement.class).getClass(), changing.getControllers().get(0).getClass());
        Assertions.assertEquals(FallDownMovement.class, changing.getControllers().get(1).getClass());

        result =  movementStrategyFactory.ensureFallDown(result);
        changing = (ChangingMovement) result;

        Assertions.assertEquals(2, changing.getControllers().size());
        Assertions.assertEquals(Mockito.mock(CircularMovement.class).getClass(), changing.getControllers().get(0).getClass());
        Assertions.assertEquals(FallDownMovement.class, changing.getControllers().get(1).getClass());

    }

    @Test
    void ensureFallDownComposite() {

        List<MovementStrategy> movementStrategies = new ArrayList<>();
        movementStrategies.add(Mockito.mock(CircularMovement.class));

        MovementStrategy movementStrategy =
                new CompositeMovement(movementStrategies);

        MovementStrategyFactory movementStrategyFactory = new MovementStrategyFactory(strategies);

        MovementStrategy result =  movementStrategyFactory.ensureFallDown(movementStrategy);

        Assertions.assertEquals(movementStrategy, result);

        CompositeMovement composite = (CompositeMovement) result;

        Assertions.assertEquals(2, composite.getControllers().size());
        Assertions.assertEquals(Mockito.mock(CircularMovement.class).getClass(), composite.getControllers().get(0).getClass());
        Assertions.assertEquals(FallDownMovement.class, composite.getControllers().get(1).getClass());

        result =  movementStrategyFactory.ensureFallDown(result);
        composite = (CompositeMovement) result;

        Assertions.assertEquals(2, composite.getControllers().size());
        Assertions.assertEquals(Mockito.mock(CircularMovement.class).getClass(), composite.getControllers().get(0).getClass());
        Assertions.assertEquals(FallDownMovement.class, composite.getControllers().get(1).getClass());

    }

}