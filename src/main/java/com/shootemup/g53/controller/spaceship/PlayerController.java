package com.shootemup.g53.controller.spaceship;

import com.googlecode.lanterna.input.KeyStroke;
import com.shootemup.g53.model.element.Spaceship;

import java.util.ArrayList;
import java.util.List;

public class PlayerController implements SpaceshipController {

    List<KeyStroke> activeKeys;

    public PlayerController() {
        this.activeKeys = new ArrayList<>();
    }

    @Override
    public void fire(Spaceship spaceship) {

    }

    @Override
    public void move(Spaceship spaceship) {

    }

    @Override
    public void usePowerup(Spaceship spaceship) {

    }
}
