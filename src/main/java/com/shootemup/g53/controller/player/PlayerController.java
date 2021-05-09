package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.element.Spaceship;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerController {
    private Spaceship spaceship;
    public PlayerController(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public void fire(Gui gui, BulletPoolController bulletPoolController) {
        spaceship.getFiringController().increaseFrame();
        if (gui.isActionActive(Action.SPACE)) {
            spaceship.fire(bulletPoolController);
        }
    }

    public void setPosition(Position position){
        spaceship.setPosition(position);
    }

    public Position move(Gui gui) {
        int speed = spaceship.getSpeed();
        Position newPosition = spaceship.getPosition();
        if (gui.isActionActive(Action.W)) {
            newPosition = newPosition.getUp(speed);
        }
        if (gui.isActionActive(Action.A)) {
            newPosition = newPosition.getLeft(speed);
        }
        if (gui.isActionActive(Action.S)) {
            newPosition = newPosition.getDown(speed);
        }
        if (gui.isActionActive(Action.D)) {
            newPosition = newPosition.getRight(speed);
        }
        return newPosition;
    }

}