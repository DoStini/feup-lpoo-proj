package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.game.BulletPoolController;
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
    public void fire(Gui gui, BulletPoolController bulletPoolController) {
        if (canShoot()) {
            bulletPoolController.addEnemyBullet(spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                    "#00ff00", 3);
            lastFire = frame;
        }
    }

    @Override
    public Position move(Gui gui) {
        return controller.move();
    }

}
