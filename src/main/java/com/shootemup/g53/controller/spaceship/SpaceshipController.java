package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public interface SpaceshipController {
    void handle(Spaceship spaceship, Action act);
    boolean fire (Spaceship spaceship, Action act);
    boolean move (Spaceship spaceship, Action act);
    boolean usePowerup (Spaceship spaceship, Action act);
}
