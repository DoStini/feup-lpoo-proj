package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;

public class CoinController extends MovableElementController implements CollisionHandlerController,ElementInterface  {
    private Coin coin;
    public CoinController(Coin coin, MovementStrategy movementStrategy) {
        super(coin, movementStrategy);
        this.coin = coin;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleCoin(this.coin);
    }

    @Override
    public void handlePlayer(Player player) {
        coin.deactivate();
    }

    @Override
    public void handle(long frame) {
        Position newPosition = move();
        coin.setPosition(newPosition);
    }
}
