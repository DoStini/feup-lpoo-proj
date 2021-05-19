package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;

public abstract class FiringStrategy {
    protected int bulletSpeed;
    protected int fireRate;
    protected int lastFire = 0;
    protected int frame = 0;
    public void increaseFrame(){
        frame++;
    }
    public int getBulletSpeed() {
        return bulletSpeed;
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

    public void fire(Spaceship spaceship, BulletPoolController bulletPoolController, String color) {
        increaseFrame();
        if (frame > lastFire + fireRate) {
            createBullets(spaceship, bulletPoolController, color);

            lastFire = frame;
        }
    }

    abstract void createBullets(Spaceship spaceship, BulletPoolController bulletPoolController, String color);

}
