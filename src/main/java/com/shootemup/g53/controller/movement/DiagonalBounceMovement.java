package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class DiagonalBounceMovement implements MovementController{
    private final Element element;
    private final int increment;
    private final int xLeftLimit;
    private final int xRightLimit;
    private Direction direction;

    enum Direction {
        DOWN_RIGHT, DOWN_LEFT
    }

    public DiagonalBounceMovement(Element element, int increment, int xLeftLimit, int xRightLimit, Direction direction) {
        this.element = element;
        this.increment = increment;
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.direction = direction;
    }

    private Position moveLeft() {
        Position newPosition = element.getPosition().getLeft(increment);

        int x = newPosition.getX();

        if(x < xLeftLimit) {
            int diff = xLeftLimit - x;
            newPosition = newPosition.getRight(diff + diff);

            this.direction = Direction.DOWN_RIGHT;
        }

        return newPosition;
    }

    private Position moveRight() {
        Position newPosition = element.getPosition().getRight(increment);

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

        return position.getDown(increment);
    }
}
