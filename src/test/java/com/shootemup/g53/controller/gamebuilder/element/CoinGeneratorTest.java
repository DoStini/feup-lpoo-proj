package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class CoinGeneratorTest {
    private Random random;
    private Coin coin;
    private CoinGenerator coinGenerator;
    private GameController gameController;
    private MovementStrategyFactory movementStrategyFactory;
    private GameModel gameModel;

    private int xMinPos = 0,
            xMaxPos = 10,
            minSpeed = 2,
            maxSpeed = 10,
            maxRadius = 4,
            minVal = 2,
            maxVal = 3;

    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gameController = Mockito.mock(GameController.class);
        coin = Mockito.mock(Coin.class);
        gameModel = Mockito.mock(GameModel.class);
        movementStrategyFactory = Mockito.mock(MovementStrategyFactory.class);

        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);

        coinGenerator = new CoinGenerator(random, gameController, movementStrategyFactory,
                xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius, minVal, maxVal);
    }

    @Test
    void setMovementController() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(new FallDownMovement());

        coinGenerator.setMovement(coin);
        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.eq(coin), Mockito.any(CoinController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.eq(coin), Mockito.any(CoinController.class));

    }

    @Test
    void setCollider() {
        coinGenerator.setCollider(coin);
        BodyCollider collider =
                new LineCompositeFactory().createFromCircle(coin, new Position(0, 0), coin.getRadius());
        collider.setCategory(ColliderCategory.PICKUP);

        Mockito.verify(gameModel, Mockito.times(1))
                .addCollider(collider);
    }

    @Test
    void setSpeed() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        coinGenerator.setSpeed(coin);

        Mockito.verify(random, Mockito.times(1)).nextInt(maxSpeed-minSpeed);
        Mockito.verify(coin, Mockito.times(1)).setSpeed(randomVal + minSpeed);
    }

    @Test
    void setRadius() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);

        coinGenerator.setRadius(coin);

        Mockito.verify(random, Mockito.times(1)).nextInt(maxRadius-1);
        Mockito.verify(coin, Mockito.times(1)).setRadius(randomVal + 1);
    }


    @Test
    void generateElement() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(new FallDownMovement());

        coinGenerator.generateElement();

        Mockito.verify(gameModel, Mockito.times(1)).addCoin(
                new Coin(new Position(randomVal, 0), randomVal+1)
        );

        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.any(Coin.class), Mockito.any(CoinController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.any(Coin.class), Mockito.any(CoinController.class));
        Mockito.verify(gameModel, Mockito.times(1)).addCollider(Mockito.any(BodyCollider.class));

    }
}