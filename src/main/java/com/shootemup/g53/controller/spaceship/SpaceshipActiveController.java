package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.ui.Gui;

public abstract class SpaceshipActiveController {

    protected int lastFire = 0;
    protected int lastMove = 0;
    protected int current = 0;
    protected Spaceship spaceship;

    public SpaceshipActiveController(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    void increaseFrame() {
        current++;
    }
    abstract public void handle(Gui gui);
    abstract boolean fire(Gui gui);
    abstract boolean move(Gui gui);
}
