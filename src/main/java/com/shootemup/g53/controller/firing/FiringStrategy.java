package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.util.Position;

public abstract class FiringStrategy {
    protected int bulletSpeed;
    protected int fireRate;
    protected int lastFire;
    protected int frame;
    public void increaseFrame(){
        frame++;
    }
    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public FiringStrategy() {
        lastFire = 0;
        frame = 0;
    }

    public int getFireRate() {
        return fireRate;
    }

    public int getLastFire() {
        return lastFire;
    }

    public int getFrame() {
        return frame;
    }

    public void fire(Position position, BulletPoolController bulletPoolController, String color) {
        increaseFrame();
        if (frame > lastFire + fireRate) {
            createBullets(position, bulletPoolController, color);

            lastFire = frame;
        }
    }

    abstract void createBullets(Position position, BulletPoolController bulletPoolController, String color);

}
