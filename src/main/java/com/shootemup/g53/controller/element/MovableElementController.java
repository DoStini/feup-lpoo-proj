package com.shootemup.g53.controller.element;

import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class MovableElementController {
    private MovableElement movableElement;

    public MovableElementController(MovableElement movableElement) {
        this.movableElement = movableElement;
    }

    public void setPosition(Position position){
        movableElement.setPosition(position);
    }

    public Position move(){
        return movableElement.move();
    }

    public void handleFailedMovement(){
        movableElement.handleFailedMovement();
    }

    public void setMovableElement(MovableElement movableElement) {
        this.movableElement = movableElement;
    }
}
