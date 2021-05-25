package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.ElementInterface;
import com.shootemup.g53.controller.element.MovableElementController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public class CoinGenerator extends MovableElementGenerator {
    private final int minVal;
    private final int maxVal;
    private final int maxRadius;

    public CoinGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory,
                         int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                         int maxRadius, int minVal, int maxVal) {
        this(new Random(), gameController, movementStrategyFactory,
                xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius, minVal, maxVal);
    }

    CoinGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                  int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                  int maxRadius, int minVal, int maxVal) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.maxRadius = maxRadius;
    }

    protected void setRadius(Coin coin) {
        coin.setRadius(rand.nextInt(maxRadius-1)+1);
    }

    protected void setValue(Coin coin) {
        // coin.setValue(rand.nextInt(maxVal - minVal)+minVal);
    }

    protected void setCollider(Coin coin) {
        BodyCollider coinCollider =
                new LineCompositeFactory().createFromCircle(coin, new Position(0,0), coin.getRadius());
        coinCollider.setCategory(ColliderCategory.PICKUP);
        gameModel.addCollider(coinCollider);
    }

    protected void setMovement(Coin coin) {
        CoinController coinController = new CoinController(coin, generateMovementStrategy(coin));
        gameController.addToControllerMap(coin, coinController);
        gameController.addToCollisionMap(coin, coinController);
    }

    @Override
    public void generateElement() {
        Coin coin = new Coin();
        setPosition(coin);
        setRadius(coin);
        setValue(coin);
        setMovement(coin);
        setCollider(coin);
        gameModel.addCoin(coin);
    }
}
