package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.ui.Gui;

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
    abstract public void handle(Gui gui);
    abstract void fire(Gui gui);
    abstract void move(Gui gui);
}
