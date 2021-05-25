package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;

public class CompositeMovement extends FrameMovement {
    protected List<MovementStrategy> controllers;

    public CompositeMovement() {
        this(new ArrayList<>());
    }

    public CompositeMovement(List<MovementStrategy> controllers) {
        this.controllers = controllers;
    }

    public void addMovement(MovementStrategy strategy) {
        controllers.add(strategy);
    }

    @Override
    Position moveFrame(Position position, int speed) {
        Position newPosition = new Position(position.getX(), position.getY());

        for(MovementStrategy strategy : controllers) {
            newPosition = strategy.move(newPosition, speed);
        }

        return newPosition;
    }

    @Override
    public void handleFailedMovement() {
        for(MovementStrategy strategy : controllers) {
            strategy.handleFailedMovement();
        }
    }
}
