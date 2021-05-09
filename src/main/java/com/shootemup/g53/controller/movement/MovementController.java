package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public interface MovementController {
    Position move(Position position, int speed);
    void handleFailedMovement();
}
