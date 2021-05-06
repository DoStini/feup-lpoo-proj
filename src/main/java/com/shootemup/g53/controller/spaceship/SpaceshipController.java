package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public abstract class SpaceshipController {

    protected int lastFire = 0;
    protected int frame = 0;

    protected Spaceship spaceship;

    public SpaceshipController(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public Position handle(Gui gui) {
        increaseFrame();
        fire(gui);
        return move(gui);
    }

    public void increaseFrame() {
        frame++;
    }

    public abstract void fire(Gui gui);
    public abstract Position move(Gui gui);
}
