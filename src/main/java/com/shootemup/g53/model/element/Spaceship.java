package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Spaceship extends MovableElement {
    private int height;
    private int health;
    private int bulletDamage;

    public Spaceship(Position position, int height,int health, String color, int speed, int bulletDamage) {
        super(position, color, speed);
        this.height = height;
        this.health = health;
        this.bulletDamage = bulletDamage;
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

    public void setHealth(int health) {
        this.health = health;

        if(this.health < 0) this.health = 0;
    }
}
