package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.EssenceController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Essence;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class EssenceGeneratorTest {
    private Random random;
    private Essence essence;
    private EssenceGenerator essenceGenerator;
    private GameController gameController;
    private MovementStrategyFactory movementStrategyFactory;
    private GameModel gameModel;

    private int xMinPos = 0,
            xMaxPos = 10,
            minSpeed = 2,
            maxSpeed = 10,
            maxRadius = 4,
            minVal = 2,
            maxVal = 3,
            maxValue = 3;

    @BeforeEach
    void setup() {
        random = Mockito.mock(Random.class);
        gameController = Mockito.mock(GameController.class);
        essence = Mockito.mock(Essence.class);
        gameModel = Mockito.mock(GameModel.class);
        movementStrategyFactory = Mockito.mock(MovementStrategyFactory.class);

        Mockito.when(gameController.getGameModel()).thenReturn(gameModel);

        essenceGenerator = new EssenceGenerator(random, gameController, movementStrategyFactory,
                xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    @Test
    void setMovementController() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(new FallDownMovement());

        essenceGenerator.setMovement(essence);
        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.eq(essence), Mockito.any(EssenceController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.eq(essence), Mockito.any(EssenceController.class));

    }

    @Test
    void setCollider() {
        essenceGenerator.setCollider(essence);
        BodyCollider collider =
                new LineCompositeFactory().createFromCircle(essence, new Position(0, 0), 1);
        collider.setCategory(ColliderCategory.PICKUP);

        Mockito.verify(gameModel, Mockito.times(1))
                .addCollider(collider);
    }

    @Test
    void setValue() {

        essenceGenerator.setMinVal(2);
        essenceGenerator.setMaxVal(5);
        Mockito.when(random.nextInt(3)).thenReturn(2);

        essenceGenerator.setValue(essence);

        Mockito.verify(random, Mockito.times(1)).nextInt(3);
        Mockito.verify(essence, Mockito.times(1)).setValue(4);
    }

    @Test
    void setSpeed() {
        double randomVal = 1.0;
        Mockito.when(random.nextDouble()).thenReturn(randomVal);

        essenceGenerator.setSpeed(essence);

        Mockito.verify(random, Mockito.times(1)).nextDouble();
        Mockito.verify(essence, Mockito.times(1)).setSpeed(randomVal*(maxSpeed-minSpeed) + minSpeed);
    }

    @Test
    void generateElement() {
        int randomVal = 2;
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(randomVal);
        Mockito.when(movementStrategyFactory.generate(Mockito.any())).thenReturn(new FallDownMovement());

        essenceGenerator.setMinVal(minVal);

        essenceGenerator.generateElement();

        Mockito.verify(gameModel, Mockito.times(1)).addEssence(
                new Essence(new Position(randomVal, 0), randomVal+minVal));

        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.any(Essence.class), Mockito.any(EssenceController.class));
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.any(Essence.class), Mockito.any(EssenceController.class));
        Mockito.verify(gameModel, Mockito.times(1)).addCollider(Mockito.any(BodyCollider.class));

    }
}