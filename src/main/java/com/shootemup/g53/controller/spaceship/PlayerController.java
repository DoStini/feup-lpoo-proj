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
        Position newPosition = spaceship.getPosition();
        if (gui.isActionActive(Action.W)) {
            newPosition = newPosition.getUp(speed);
        }
        if (gui.isActionActive(Action.A)) {
            newPosition = newPosition.getLeft(speed);
        }
        if (gui.isActionActive(Action.S)) {
            newPosition = newPosition.getDown(speed);
        }
        if (gui.isActionActive(Action.D)) {
            newPosition = newPosition.getRight(speed);
        }
        return newPosition;

    }

}