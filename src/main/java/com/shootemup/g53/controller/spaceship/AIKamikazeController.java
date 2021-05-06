package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class AIKamikazeController extends SpaceshipController {

    private MovementController controller;

    public AIKamikazeController(Spaceship spaceship) {
        this(spaceship, new FallDownMovement(spaceship));
    }

    public AIKamikazeController(Spaceship spaceship, MovementController controller) {
        super(spaceship);
        this.controller = controller;
    }

    @Override
    public void fire(Gui gui) {
    }

    @Override
    public Position move(Gui gui) {
        return controller.move();
    }

}
