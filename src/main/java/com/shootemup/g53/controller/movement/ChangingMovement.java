package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

import java.util.List;
import java.util.Random;

public class ChangingMovement implements MovementController{
    private int changeRate;
    private MovementController currentController;
    private List<MovementController> controllers;
    private int lastChange = 0;
    private int frame = 0;
    private Random randomGen;

    public ChangingMovement(int changeRate, List<MovementController> controllers) {
        this.changeRate = changeRate;
        this.controllers = controllers;
        this.randomGen = new Random();
        setNewController();
    }

    public ChangingMovement(int changeRate, List<MovementController> controllers, Random randomGen, int frame) {
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
    public Position move(Position position, int speed) {
        frame++;
        if (frame > lastChange + changeRate)
            setNewController();
        return currentController.move(position, speed);
    }

    @Override
    public void handleFailedMovement() {
        currentController.handleFailedMovement();
    }
}
