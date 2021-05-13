package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;

public class Spaceship extends MovableElement {
    private int height;
    private FiringStrategy firingStrategy;
    private int fireRate;

    public Spaceship() {
        this(new Position(0,0), 0, "", 0, 0, null, null);
    }

    public Spaceship(Position position, int height, String color, int speed, int fireRate, MovementStrategy movementStrategy, FiringStrategy firingStrategy) {
        super(position, color, speed, movementStrategy);
        this.height = height;
        this.firingStrategy = firingStrategy;
        this.fireRate = fireRate;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public FiringStrategy getFiringController() {
        return firingStrategy;
    }

    public void fire(BulletPoolController bulletPoolController){
        firingStrategy.fire(this, bulletPoolController );
    }

    public void setFiringController(FiringStrategy firingStrategy) {
        this.firingStrategy = firingStrategy;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship)) return false;
        Spaceship other = (Spaceship) o;
        return super.equals(o)
                && other.height == this.height
                && other.fireRate == this.fireRate
                && other.getSpeed() == this.getSpeed();
    }

}
