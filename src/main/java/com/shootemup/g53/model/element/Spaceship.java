package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Spaceship extends MovableElement {
    private int height;
    private int health;
    public Spaceship(Position position, int height,int health, String color, int speed) {
        super(position, color, speed);
        this.height = height;
        this.health = health;

    }

    public Spaceship() {
        this(new Position(0,0), 0, 0, "", 0);
    }

    @Override
    public PoolableObject clone() {
        return new Spaceship(getPosition(), height,health,getColor(),getSpeed());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

        if(this.health < 0) this.health = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship)) return false;
        Spaceship other = (Spaceship) o;
        return super.equals(o)
                && other.height == this.height
                && other.getSpeed() == this.getSpeed();
    }

}
