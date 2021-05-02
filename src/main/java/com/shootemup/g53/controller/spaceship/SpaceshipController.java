package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public interface SpaceshipController {
    void fire (Spaceship spaceship);
    void move(Spaceship spaceship);
    void usePowerup(Spaceship spaceship);
}
