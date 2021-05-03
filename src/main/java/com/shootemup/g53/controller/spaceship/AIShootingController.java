package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public class AIShootingController extends SpaceshipController {

    public AIShootingController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void handle(Action act) {
        move(Action.NONE);
    }

    @Override
    public boolean fire(Action act) {
        return false;
    }

    @Override
    public boolean move(Action act) {
        int speed = 1;
        executeMove(spaceship.getPosition().getDown(speed));
        return true;
    }

    private boolean executeMove(Position position) {
        // Later verify here if can move to the desired location
        spaceship.setPosition(position);
        return true;
    }
}
