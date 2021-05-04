package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;

public abstract class MovableElement extends Element {

    private int frameCount = 0;
    private int velocity = 3;
    // private MovementStrategy movementStrategy;

    public MovableElement(Position position, String color) {
        super(position, color);
    }

    public Position move(){
        if(frameCount < this.velocity){
            frameCount++;
            return null;
        }
        frameCount = 0;
        return null;
        //return movementStrategy.move(this.getPosition());
    }

   

}
