package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class AIShootingController extends SpaceshipController {

    public AIShootingController(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public void handle(Gui gui) {
        move(gui);
        fire(gui);

    }

    @Override
    void fire(Gui gui) {
    }

    @Override
    void move(Gui gui) {
        int speed = 1;
        executeMove(spaceship.getPosition().getDown(speed));
    }

    private void executeMove(Position position) {
        // Later verify here if can move to the desired location
        spaceship.setPosition(position);
    }
}
