package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;

public abstract class SpaceshipController {

    protected int lastFire = 0;
    protected int lastMove = 0;
    protected int current = 0;
    protected Spaceship spaceship;

    public SpaceshipController(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    void increaseFrame() {
        current++;
    }
    abstract public void handle(Action act);
    abstract boolean fire(Action act);
    abstract boolean move(Action act);
}
