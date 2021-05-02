package com.shootemup.g53.controller;

import com.shootemup.g53.model.util.Position;

public interface MovementController {
    Position nextPosition(Position position);
}
