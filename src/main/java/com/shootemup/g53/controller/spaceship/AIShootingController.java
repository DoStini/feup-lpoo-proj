package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class AIShootingController extends SpaceshipController {

    private MovementController controller;

    public AIShootingController(Spaceship spaceship, MovementController controller) {
        super(spaceship);
        this.controller = controller;
    }

    @Override
    public void fire(Gui gui) {
        if (frame > lastFire + spaceship.getFireRate()) {
            System.out.println("Enemy Fire");
            lastFire = frame;
        }
    }

    @Override
    public Position move(Gui gui) {
        return controller.move();
    }

}
