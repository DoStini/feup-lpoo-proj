package com.shootemup.g53.controller.spaceship;

import com.googlecode.lanterna.input.KeyStroke;
import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

import java.util.List;

public class PlayerController extends SpaceshipController {

    @Override
    public void handle(Spaceship spaceship, Action act) {
        increaseFrame();
        if (fire(spaceship, act)) return;
        if (usePowerup(spaceship, act)) return;
        move(spaceship, act);
    }

    @Override
    public boolean fire(Spaceship spaceship, Action act) {
        if (act == Action.FIRE) {
            int fireRate = 20;
            if (current > lastFire + fireRate) {
                System.out.println("Fire!");
                lastFire = current;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Spaceship spaceship, Action act) {
        Position p = spaceship.getPosition();
        int speed = 1; // Override when elements exist
        Position newPos;
        if (act == Action.W) {
            return executeMove(spaceship, p.getUp(speed));
        }
        else if (act == Action.A) {
            return executeMove(spaceship, p.getLeft(speed));
        }
        else if (act == Action.S) {
            return executeMove(spaceship, p.getDown(speed));
        }
        else if (act == Action.D) {
            return  executeMove(spaceship, p.getRight(speed));
        }
        return false;
    }

    private boolean executeMove(Spaceship spaceship, Position position) {
        // Later verify here if can move to the desired location
        spaceship.setPosition(position);
        return true;
    }

    public boolean usePowerup(Spaceship spaceship, Action act) {
        return false;
    }
}
