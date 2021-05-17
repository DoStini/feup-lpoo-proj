package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;

public class CoinController extends MovableElementController implements CollisionController,ElementInterface  {
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
    public void handleBullet(Bullet bullet) {

    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {
        coin.deactivate();
    }

    @Override
    public void handleAsteroid(Asteroid asteroid) {

    }

    @Override
    public void handleCoin(Coin coin) {

    }

    @Override
    public void handle() {
        Position newPosition = move();
        coin.setPosition(newPosition);
    }
}
