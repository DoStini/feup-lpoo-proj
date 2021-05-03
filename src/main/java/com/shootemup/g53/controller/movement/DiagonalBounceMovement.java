package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;

public class DiagonalBounceMovement implements MovementController{
    private final Element element;
    private final int speed;
    private final int xLeftLimit;
    private final int xRightLimit;
    private Direction direction;

    enum Direction {
        DOWN_RIGHT, DOWN_LEFT
    }

    public DiagonalBounceMovement(Element element, int speed, int xLeftLimit, int xRightLimit, Direction direction) {
        this.element = element;
        this.speed = speed;
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.direction = direction;
    }

    private void moveLeft() {
        element.setPosition(element.getPosition().getLeft(speed));

        int x = element.getPosition().getX();

        if(x < xLeftLimit) {
            int diff = xLeftLimit - x;
            element.setPosition(element.getPosition().getRight(diff + diff));

            this.direction = Direction.DOWN_RIGHT;
        }
    }

    private void moveRight() {
        element.setPosition(element.getPosition().getRight(speed));

        int x = element.getPosition().getX();

        if(x > xRightLimit) {
            int diff = x-xRightLimit;
            element.setPosition(element.getPosition().getLeft(diff + diff));

            this.direction = Direction.DOWN_LEFT;
        }
    }

    @Override
    public Element move() {
        element.setPosition(element.getPosition().getDown(speed));

        switch (this.direction) {
            case DOWN_LEFT:
                this.moveLeft();
                break;
            case DOWN_RIGHT:
                this.moveRight();
                break;
        }

        return element;
    }
}
