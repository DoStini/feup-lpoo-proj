package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerActiveController extends SpaceshipActiveController {
    public PlayerActiveController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void handle(Gui gui) {
        fire(gui);
        move(gui);
    }

    @Override
    boolean fire(Gui gui) {
        if (gui.isActionActive(Action.FIRE))
            System.out.println("Fire");
        return false;
    }

    @Override
    boolean move(Gui gui) {
        Position p = spaceship.getPosition();
        int speed = 1; // Override when elements exist
        Position newPos;
        if (gui.isActionActive(Action.W)) {
            return executeMove(spaceship, p.getUp(speed));
        }
        else if (gui.isActionActive(Action.A)) {
            return executeMove(spaceship, p.getLeft(speed));
        }
        else if (gui.isActionActive(Action.S)) {
            return executeMove(spaceship, p.getDown(speed));
        }
        else if (gui.isActionActive(Action.D)) {
            return  executeMove(spaceship, p.getRight(speed));
        }
        return false;
    }

    private boolean executeMove(Spaceship spaceship, Position position) {
        // Later verify here if can move to the desired location
        spaceship.setPosition(position);
        return true;
    }

}
