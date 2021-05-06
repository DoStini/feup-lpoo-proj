package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.controller.game.BulletController;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
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
    public abstract Bullet fire(Gui gui);
    public abstract Position move(Gui gui);
}
