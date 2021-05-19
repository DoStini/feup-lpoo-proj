package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

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

    abstract public void fire(Spaceship spaceship, BulletPoolController bulletPoolController);

}
