package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;

public abstract class MovableElement extends Element {

    private int speed;
    protected MovementStrategy movementStrategy;
    public MovableElement(Position position, String color, int speed, MovementStrategy movementStrategy) {
        super(position, color);
        this.speed = speed;
        this.movementStrategy = movementStrategy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public MovementStrategy getMovementController() {
        return movementStrategy;
    }

    public void setMovementController(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    public Position move(){
        return movementStrategy.move(getPosition(),getSpeed());
    }

    public void handleFailedMovement(){
        movementStrategy.handleFailedMovement();
    }
}
