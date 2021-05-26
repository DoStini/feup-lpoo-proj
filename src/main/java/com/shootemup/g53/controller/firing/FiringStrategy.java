package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public abstract class FiringStrategy {
    protected double bulletSpeed;
    protected int fireRate;
    protected long lastFire;
 
    
    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public FiringStrategy() {
        lastFire = 0;

    }

    public int getFireRate() {
        return fireRate;
    }

    public long getLastFire() {
        return lastFire;
    }


    public void fire(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color, ColliderCategory category, long frame) {
        if (frame > lastFire + fireRate) {
            createBullets(spaceship, position, bulletPoolController, color, category);

            lastFire = frame;
        }
    }

    abstract void createBullets(Spaceship spaceship, Position position, BulletPoolController bulletPoolController, String color, ColliderCategory category);

}
