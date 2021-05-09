package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;

public class AIFiringController{
    private Spaceship spaceship;
    public AIFiringController(Spaceship spaceship) {
        this.spaceship = spaceship;

    }

    public void fire(BulletPoolController bulletPoolController){
        spaceship.fire(bulletPoolController);
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }
}
