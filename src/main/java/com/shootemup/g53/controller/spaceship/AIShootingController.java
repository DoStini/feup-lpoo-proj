package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class AIShootingController extends SpaceshipController {

    public AIShootingController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Bullet fire(Gui gui) {
        return null;
    }

    @Override
    public Position move(Gui gui) {
        int speed = 1;
        return spaceship.getPosition().getDown(speed);
    }

}
