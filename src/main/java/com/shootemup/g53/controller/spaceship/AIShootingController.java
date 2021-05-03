package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public class AIShootingController extends SpaceshipController {
    @Override
    public void handle(Spaceship spaceship, Action act) {
        move(spaceship, Action.NONE);
    }

    @Override
    public boolean fire(Spaceship spaceship, Action act) {
        return false;
    }

    @Override
    public boolean move(Spaceship spaceship, Action act) {
        int speed = 1;
        executeMove(spaceship, spaceship.getPosition().getDown(speed));
        return true;
    }

    private boolean executeMove(Spaceship spaceship, Position position) {
        // Later verify here if can move to the desired location
        spaceship.setPosition(position);
        return true;
    }
}
