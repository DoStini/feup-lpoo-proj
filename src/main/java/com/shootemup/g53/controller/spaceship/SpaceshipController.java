package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public class SpaceshipController {
    protected Spaceship spaceship;
    public SpaceshipController(Spaceship spaceship) {
        this.spaceship = spaceship;

    }

    public Position move(){
        return spaceship.move();
    }

    public Bullet fire(){
        return spaceship.fire();
    };
    public void handleFailedMovement(){
        spaceship.handleFailedMovement();
    };
}
