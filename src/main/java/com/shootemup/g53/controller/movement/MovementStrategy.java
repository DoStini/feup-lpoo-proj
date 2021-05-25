package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public interface MovementStrategy {
    Position move(Position position, double speed);
    void handleFailedMovement();
}
