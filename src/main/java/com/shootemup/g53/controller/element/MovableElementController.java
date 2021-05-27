package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class MovableElementController {
    private MovableElement movableElement;
    private MovementStrategy movementStrategy;

    public MovableElementController(MovableElement movableElement, MovementStrategy movementStrategy) {
        this.movableElement = movableElement;
        this.movementStrategy = movementStrategy;
    }

    public void setPosition(Position position){
        movableElement.setPosition(position);
    }

    public Position move(){
        return movementStrategy.move(movableElement.getPosition(), movableElement.getSpeed());
    }


    public void setMovableElement(MovableElement movableElement) {
        this.movableElement = movableElement;
    }
}
