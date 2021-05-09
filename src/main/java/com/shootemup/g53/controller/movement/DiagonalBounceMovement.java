package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;

public class DiagonalBounceMovement implements MovementController{
    private final Position initalPosition;
    private final int xLeftLimit;
    private final int xRightLimit;
    private Direction direction;

    @Override
    public Position move(Position position, int speed) {
        switch (this.direction) {
            case DOWN_LEFT:
                position = this.moveLeft(position, speed);
                break;
            case DOWN_RIGHT:
                position = this.moveRight(position, speed);
                break;
            default:
                position = new Position(0,0);
                break;
        }

        return position.getDown(speed);
    }

    @Override
    public void handleFailedMovement() {
        if(this.direction == Direction.DOWN_LEFT) this.direction = Direction.DOWN_RIGHT;
        else this.direction = Direction.DOWN_LEFT;
    }



    public DiagonalBounceMovement(int xLeftLimit, int xRightLimit, Direction direction, Position initalPosition) {
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.direction = direction;
        this.initalPosition = initalPosition;
    }

    private Position moveLeft(Position position, int speed) {
        Position newPosition = position.getLeft(speed);
        int x = newPosition.getX();

        if(x < initalPosition.getX() - xLeftLimit) {
            int diff = xLeftLimit - x;
            newPosition = newPosition.getRight(2*diff);

            this.direction = Direction.DOWN_RIGHT;
        }

        return newPosition;
    }

    private Position moveRight(Position position, int speed) {
        Position newPosition = position.getRight(speed);

        int x = newPosition.getX();

        if(x > initalPosition.getX() + xRightLimit) {
            int diff = x- xRightLimit;
            newPosition = newPosition.getLeft(diff + diff);

            this.direction = Direction.DOWN_LEFT;
        }

        return newPosition;
    }


}
