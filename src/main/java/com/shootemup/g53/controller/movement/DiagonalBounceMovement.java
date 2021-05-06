package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class DiagonalBounceMovement implements MovementController{
    private final MovableElement element;
    private final int xLeftLimit;
    private final int xRightLimit;
    private Direction direction;

    public enum Direction {
        DOWN_RIGHT, DOWN_LEFT
    }

    public DiagonalBounceMovement(MovableElement element, int xLeftLimit, int xRightLimit, Direction direction) {
        this.element = element;
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.direction = direction;
    }

    private Position moveLeft() {
        Position newPosition = element.getPosition().getLeft(element.getSpeed());

        int x = newPosition.getX();

        if(x < xLeftLimit) {
            int diff = xLeftLimit - x;
            newPosition = newPosition.getRight(2*diff);

            this.direction = Direction.DOWN_RIGHT;
        }

        return newPosition;
    }

    private Position moveRight() {
        Position newPosition = element.getPosition().getRight(element.getSpeed());

        int x = newPosition.getX();

        if(x > xRightLimit) {
            int diff = x-xRightLimit;
            newPosition = newPosition.getLeft(diff + diff);

            this.direction = Direction.DOWN_LEFT;
        }

        return newPosition;
    }

    @Override
    public Position move() {
        Position position;
        switch (this.direction) {
            case DOWN_LEFT:
                position = this.moveLeft();
                break;
            case DOWN_RIGHT:
                position = this.moveRight();
                break;
            default:
                position = new Position(0,0);
                break;
        }

        return position.getDown(element.getSpeed());
    }
}
