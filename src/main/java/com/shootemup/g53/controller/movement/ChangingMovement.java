package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChangingMovement extends IncrementalMovement {
    protected int changeRate;
    private MovementStrategy currentController;
    protected List<MovementStrategy> controllers;
    private int lastChange = 0;
    private int frame = 0;
    private Random randomGen;

    public ChangingMovement(int changeRate, List<MovementStrategy> controllers) {
        this.changeRate = changeRate;
        this.controllers = controllers;
        this.randomGen = new Random();
        setNewController();
    }

    public ChangingMovement(int changeRate, List<MovementStrategy> controllers, Random randomGen, int frame) {
        this.changeRate = changeRate;
        this.controllers = controllers;
        this.randomGen = randomGen;
        this.frame = frame;
        setNewController();
    }

    private void setNewController() {
        currentController = controllers.get(randomGen.nextInt(controllers.size()));
        lastChange = frame;
    }

    @Override
    Position moveFrame(Position position, int speed) {
        frame++;
        if (frame > lastChange + changeRate)
            setNewController();
        return currentController.move(position, speed);
    }

    @Override
    public void handleFailedMovement() {
        currentController.handleFailedMovement();
    }

    @Override
    public MovementStrategy cloneStrategy() {
        List<MovementStrategy> strategies = new ArrayList<>();

        for(MovementStrategy strategy : controllers) {
            strategies.add(strategy.cloneStrategy());
        }

        return new ChangingMovement(changeRate, strategies);
    }
}
