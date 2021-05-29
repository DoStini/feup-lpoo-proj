package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.firing.*;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Spaceship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FiringStrategyFactoryTest {

    private Spaceship spaceship;
    private Random random;

    private FiringStrategyFactory firingStrategyFactory;
    private List<FiringStrategyFactory.Strategy> strategies;

    @BeforeEach
    void setUp() {
        spaceship = Mockito.mock(Spaceship.class);
        random = Mockito.mock(Random.class);
    }

    @Test
    void generateNormal() {
        strategies = Arrays.asList(FiringStrategyFactory.Strategy.NORMAL);
        firingStrategyFactory = new FiringStrategyFactory(random, strategies);

        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(1.0);
        Mockito.when(random.nextInt(5)).thenReturn(3);
        Mockito.when(spaceship.getSpeed()).thenReturn(2.0);

        FiringStrategy firingStrategy = firingStrategyFactory.generate(spaceship);
        Assertions.assertEquals(MovingBulletStrategy.class, firingStrategy.getClass());

        MovingBulletStrategy strategy = (MovingBulletStrategy) firingStrategy;
        Assertions.assertEquals(8.0, strategy.getBulletSpeed());
        Assertions.assertEquals(8, strategy.getFireRate());
        Assertions.assertEquals(FallDownMovement.class, strategy.getMovement().getClass());
    }

    @Test
    void generateMultiple() {
        strategies = Arrays.asList(FiringStrategyFactory.Strategy.MULTIPLE);
        firingStrategyFactory = new FiringStrategyFactory(random, strategies);

        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(0.5);
        Mockito.when(random.nextInt(5)).thenReturn(4);
        Mockito.when(spaceship.getSpeed()).thenReturn(3.5);

        FiringStrategy firingStrategy = firingStrategyFactory.generate(spaceship);
        Assertions.assertEquals(MultipleMovingBulletStrategy.class, firingStrategy.getClass());

        Assertions.assertEquals(7.5, firingStrategy.getBulletSpeed());
        Assertions.assertEquals(9, firingStrategy.getFireRate());
    }

    @Test
    void generateDownSpread() {
        strategies = Arrays.asList(FiringStrategyFactory.Strategy.SPREAD_DOWN);
        firingStrategyFactory = new FiringStrategyFactory(random, strategies);

        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(0.8);
        Mockito.when(random.nextInt(5)).thenReturn(4);
        Mockito.when(spaceship.getSpeed()).thenReturn(3.5);

        FiringStrategy firingStrategy = firingStrategyFactory.generate(spaceship);
        Assertions.assertEquals(SpreadBulletDownStrategy.class, firingStrategy.getClass());

        Assertions.assertEquals(8.7, firingStrategy.getBulletSpeed());
        Assertions.assertEquals(9, firingStrategy.getFireRate());
    }

    @Test
    void generateUpSpread() {
        strategies = Arrays.asList(FiringStrategyFactory.Strategy.SPREAD_UP);
        firingStrategyFactory = new FiringStrategyFactory(random, strategies);

        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(0.5);
        Mockito.when(random.nextInt(5)).thenReturn(4);
        Mockito.when(spaceship.getSpeed()).thenReturn(3.5);

        FiringStrategy firingStrategy = firingStrategyFactory.generate(spaceship);
        Assertions.assertEquals(SpreadBulletUpStrategy.class, firingStrategy.getClass());

        Assertions.assertEquals(7.5, firingStrategy.getBulletSpeed());
        Assertions.assertEquals(9, firingStrategy.getFireRate());
    }

    @Test
    void generateAllSpread() {
        strategies = Arrays.asList(FiringStrategyFactory.Strategy.ALL_SPREAD);
        firingStrategyFactory = new FiringStrategyFactory(random, strategies);

        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(random.nextDouble()).thenReturn(1.0);
        Mockito.when(random.nextInt(5)).thenReturn(4);
        Mockito.when(spaceship.getSpeed()).thenReturn(3.5);

        FiringStrategy firingStrategy = firingStrategyFactory.generate(spaceship);
        Assertions.assertEquals(SpreadAllStrategy.class, firingStrategy.getClass());

        Assertions.assertEquals(9.5, firingStrategy.getBulletSpeed());
        Assertions.assertEquals(9, firingStrategy.getFireRate());
    }

    @Test
    void generate() {
        strategies = Arrays.asList(FiringStrategyFactory.Strategy.ALL_SPREAD,
                FiringStrategyFactory.Strategy.SPREAD_DOWN,
                FiringStrategyFactory.Strategy.NORMAL);

        firingStrategyFactory = new FiringStrategyFactory(random, strategies);

        Mockito.when(random.nextInt()).thenReturn(1);
        Assertions.assertEquals(SpreadBulletDownStrategy.class,
                firingStrategyFactory.generate(spaceship).getClass());

        Mockito.when(random.nextInt()).thenReturn(2);
        Assertions.assertEquals(MovingBulletStrategy.class,
                firingStrategyFactory.generate(spaceship).getClass());

        Mockito.when(random.nextInt()).thenReturn(0);
        Assertions.assertEquals(SpreadAllStrategy.class,
                firingStrategyFactory.generate(spaceship).getClass());


    }

}