package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public abstract class SpaceshipController {

    protected int lastFire;
    protected int frame = 0;

    protected Spaceship spaceship;

    public SpaceshipController(Spaceship spaceship) {
        this.spaceship = spaceship;
        lastFire = -spaceship.getFireRate();
    }

    public Position handle(Gui gui, BulletPoolController bulletPoolController) {
        increaseFrame();
        fire(gui, bulletPoolController);
        return move(gui);
    }

    public void increaseFrame() {
        frame++;
    }

    public abstract void fire(Gui gui, BulletPoolController bulletPoolController);
    public abstract Position move(Gui gui);

    protected boolean canShoot() {
        return frame >= lastFire + spaceship.getFireRate();
    }
}
