package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;

public class AIKamikazeController extends SpaceshipController {
    public AIKamikazeController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    void increaseFrame() {
        super.increaseFrame();
    }

    @Override
    public void handle(Action act) {
        move(Action.NONE);
    }

    @Override
    boolean fire(Action act) {
        return false;
    }

    @Override
    boolean move(Action act) {
        return false;
    }
}
