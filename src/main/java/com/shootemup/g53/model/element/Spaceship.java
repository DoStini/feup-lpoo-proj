package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.util.Position;

public class Spaceship extends MovableElement {
    private int height;
    private int fireRate;
    private int lastFire = 0;
    private int frame = 0;
    private FiringStrategy firingStrategy;

    public Spaceship(Position position, int height, String color, int speed, int fireRate) {
        super(position, color, speed);
        this.height = height;
        this.fireRate = fireRate;
    }

    public void increaseFrame(){
        frame++;
    }

    public int getFireRate() {
        return fireRate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public int getLastFire() {
        return lastFire;
    }

    public void setLastFire(int lastFire) {
        this.lastFire = lastFire;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
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
}
