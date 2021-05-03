package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController extends SpaceshipController {
    public PlayerController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void handle(Gui gui) {
        fire(gui);
        move(gui);
    }

    @Override
    void fire(Gui gui) {
        if (gui.isActionActive(Action.FIRE))
            System.out.println("Fire");
    }

    @Override
     void move(Gui gui) {
        int speed = 1; // Override when elements exist
        if (gui.isActionActive(Action.W)) {
            executeMove(spaceship, spaceship.getPosition().getUp(speed));
        }
        if (gui.isActionActive(Action.A)) {
            executeMove(spaceship, spaceship.getPosition().getLeft(speed));
        }
        if (gui.isActionActive(Action.S)) {
            executeMove(spaceship, spaceship.getPosition().getDown(speed));
        }
        if (gui.isActionActive(Action.D)) {
            executeMove(spaceship, spaceship.getPosition().getRight(speed));
        }
    }

    private void executeMove(Spaceship spaceship, Position position) {
        // Later verify here if can move to the desired location
        spaceship.setPosition(position);
    }
}
