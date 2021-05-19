package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompositeMovement implements MovementStrategy {
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
    public Position move(Position position, int speed) {
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
