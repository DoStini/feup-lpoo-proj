package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController extends SpaceshipController {
    public PlayerController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Bullet fire(Gui gui) {
        if (gui.isActionActive(Action.FIRE))
            System.out.println("Fire");
        return null;
    }

    @Override
    public Position move(Gui gui) {
        int speed = 1; // Override when elements exist
        if (gui.isActionActive(Action.W)) {
            return spaceship.getPosition().getUp(speed);
        }
        if (gui.isActionActive(Action.A)) {
            return spaceship.getPosition().getLeft(speed);
        }
        if (gui.isActionActive(Action.S)) {
            return spaceship.getPosition().getDown(speed);
        }
        if (gui.isActionActive(Action.D)) {
            return spaceship.getPosition().getRight(speed);
        }
        return null;

    }

}