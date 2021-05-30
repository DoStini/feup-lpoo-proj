package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;

public class CompositeMovement extends IncrementalMovement {
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
    public MovementStrategy cloneStrategy() {
        List<MovementStrategy> strategies = new ArrayList<>();

        for(MovementStrategy strategy : controllers) {
            strategies.add(strategy.cloneStrategy());
        }

        return new CompositeMovement(strategies);
    }

    public boolean contains(MovementStrategy strategy) {
        return controllers.stream().anyMatch(st -> st.getClass() == strategy.getClass());
    }

    public List<MovementStrategy> getControllers() {
        return controllers;
    }
}
