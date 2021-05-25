package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Player extends Spaceship{
    private int coins;
    private int essence;

    public Player(Position position, int height, int health, String color, int speed) {
        super(position, height, health, color, speed);
        this.coins = 0;
        this.essence = 0;
    }

    public int getEssence() {
        return essence;
    }

    public int getCoins() {
        return coins;
    }

    public void addEssence(int essence) {
        this.essence += essence;
    }

    public void addCoin(int coin) {
        this.coins += coin;
    }

    public boolean removeEssence(int essence) {
        if(this.essence < essence) return false;

        this.essence -= essence;
        return true;
    }

    @Override
    public PoolableObject clone() {
        return new Player(new Position(position.getX(), position.getY()), this.getHeight(),this.getHealth(),getColor(),getSpeed());
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }
}
