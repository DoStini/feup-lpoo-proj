package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Player extends Spaceship{
    private final int maxHealth;
    private int coins;
    private int essence;
    private int hitHeight;


    public Player(Position position, int height, int hitHeight, int health, String color, double speed, int bulletDamage) {
        super(position, height, health, color, speed,bulletDamage);
        this.coins = 0;
        this.essence = 0;
        this.maxHealth = health;
        this.hitHeight = hitHeight;
    }

    public int getHitHeight() {
        return hitHeight;
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
        return new Player(new Position(position.getX(), position.getY()), this.getHeight(), 1, this.getHealth(),getColor(),getSpeed(),getBulletDamage());
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }


    public int getMaxHealth() {
        return maxHealth;
    }
}
