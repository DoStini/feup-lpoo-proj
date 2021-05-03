package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;

public class AIKamikazeController extends SpaceshipController {
    @Override
    void increaseFrame() {
        super.increaseFrame();
    }

    @Override
    public void handle(Spaceship spaceship, Action act) {
        move(spaceship, Action.NONE);
    }

    @Override
    boolean fire(Spaceship spaceship, Action act) {
        return false;
    }

    @Override
    boolean move(Spaceship spaceship, Action act) {
        return false;
    }
}
