package com.shootemup.g53.controller.spaceship;

import com.googlecode.lanterna.input.KeyStroke;
import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

import java.util.List;

public class PlayerController implements SpaceshipController {

    List<KeyStroke> activeKeys;

    @Override
    public void handle(Spaceship spaceship, Action act) {
        if (fire(spaceship, act)) return;
        if (usePowerup(spaceship, act)) return;
        move(spaceship, act);
    }

    @Override
    public boolean fire(Spaceship spaceship, Action act) {
        if (act == Action.FIRE) {
            System.out.println("Fire!");
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Spaceship spaceship, Action act) {
        Position p = spaceship.getPosition();
        int speed = 2;
        Position newPos;
        if (act == Action.W) {
            spaceship.setPosition(p.getUp(speed));
            return true;
        }
        else if (act == Action.A) {
            spaceship.setPosition(p.getLeft(speed));
            return true;
        }
        else if (act == Action.S) {
            spaceship.setPosition(p.getDown(speed));
            return true;
        }
        else if (act == Action.D) {
            spaceship.setPosition(p.getRight(speed));
            return true;
        }
        return false;
    }

    @Override
    public boolean usePowerup(Spaceship spaceship, Action act) {
        return false;
    }

}
