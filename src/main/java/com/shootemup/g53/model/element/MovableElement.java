package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.movement.MovementController;
import com.shootemup.g53.model.util.Position;

public abstract class MovableElement extends Element {

    private int speed;
    protected MovementController movementController;
    public MovableElement(Position position, String color, int speed) {
        super(position, color);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public MovementController getMovementController() {
        return movementController;
    }

    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }
    public Position move(){
        return movementController.move(getPosition(),getSpeed());
    }

    public void handleFailedMovement(){
        movementController.handleFailedMovement();
    }
}
