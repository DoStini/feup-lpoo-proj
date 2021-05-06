package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

import java.util.List;
import java.util.Random;

public class AIChangingController extends SpaceshipController {

    private int changeRate;
    private MovementController currentController;
    private List<MovementController> controllers;
    private int lastChange = 0;
    private Random randomGen;

    public AIChangingController(Spaceship spaceship, List<MovementController> controllers, int changeRate) {
        this(spaceship, controllers, changeRate, new Random());
    }

    public AIChangingController(Spaceship spaceship, List<MovementController> controllers, int changeRate, Random randomGen) {
        super(spaceship);
        this.controllers = controllers;
        this.changeRate = changeRate;
        this.randomGen = randomGen;
        setNewController();
    }

    private void setNewController() {
        currentController = controllers.get(randomGen.nextInt(controllers.size()));
        lastChange = frame;
    }

    @Override
    public Position handle (Gui gui) {
        increaseFrame();
        if (frame > lastChange + changeRate)
            setNewController();
        fire(gui);
        return move(gui);
    }

    @Override
    public void fire(Gui gui) {
        if (frame > lastFire + spaceship.getFireRate()) {
            System.out.println("Enemy Fire");
            lastFire = frame;
        }
    }

    @Override
    public Position move(Gui gui) {
        return currentController.move();
    }
}
