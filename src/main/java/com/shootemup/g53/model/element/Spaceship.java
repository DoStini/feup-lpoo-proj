package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.util.Position;

public class Spaceship extends MovableElement {
    private int height;
    private FiringStrategy firingStrategy;

    public Spaceship(Position position, int height, String color, int speed) {
        super(position, color, speed);
        this.height = height;
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
}
