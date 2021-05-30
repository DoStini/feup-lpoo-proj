package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Spaceship extends MovableElement {
    private int height;
    protected int health;
    private int bulletDamage;
    private int maxHealth;

    public Spaceship(Position position, int height,int health, String color, double speed, int bulletDamage) {
        super(position, color, speed);
        this.height = height;
        this.health = health;
        this.maxHealth = health;
        this.bulletDamage = bulletDamage;
    }

    public Spaceship() {
        this(new Position(0,0), 0, 0, "",0, 0);
    }

    @Override
    public PoolableObject clone() {
        return new Spaceship(getPosition(), height,health,getColor(),getSpeed(), getBulletDamage());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(maxHealth, health));
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship)) return false;
        Spaceship other = (Spaceship) o;
        return super.equals(o)
                && other.height == this.height
                && other.getSpeed() == this.getSpeed()
                && other.getBulletDamage() == this.getBulletDamage()
                && other.getMaxHealth() == this.getMaxHealth()
                && other.getHealth() == this.getMaxHealth();
    }

}
