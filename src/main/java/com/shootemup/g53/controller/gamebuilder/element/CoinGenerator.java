package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public class CoinGenerator extends MovableElementGenerator {
    private final int maxRadius;

    public CoinGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory,
                         int xMinPos, int xMaxPos, double minSpeed, double maxSpeed,
                         int maxRadius) {
        this(new Random(), gameController, movementStrategyFactory,
                xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius);
    }

    CoinGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                  int xMinPos, int xMaxPos, double minSpeed, double maxSpeed,
                  int maxRadius) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxRadius = maxRadius;
    }

    protected void setRadius(Coin coin) {
        coin.setRadius(rand.nextInt(maxRadius-1)+1);
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
        setMovement(coin);
        setCollider(coin);
        gameModel.addCoin(coin);
    }
}
