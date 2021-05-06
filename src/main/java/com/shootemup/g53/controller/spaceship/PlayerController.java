package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController extends SpaceshipController {
    public PlayerController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void fire(Gui gui) {
        if (gui.isActionActive(Action.SPACE) && frame > lastFire + spaceship.getFireRate()) {
            System.out.println("Fire");
            lastFire = frame;
        }
    }

    @Override
    public Position move(Gui gui) {
        int speed = spaceship.getSpeed(); // Override when elements exist
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
