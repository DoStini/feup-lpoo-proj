package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class DiagonalBounceMovement implements MovementController{
    private final int speed;
    private final int xLeftLimit;
    private final int xRightLimit;
    private Direction direction;

    enum Direction {
        DOWN_RIGHT, DOWN_LEFT
    }

    public DiagonalBounceMovement(int speed, int xLeftLimit, int xRightLimit, Direction direction) {
        this.speed = speed;
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.direction = direction;
    }

    private void moveLeft(Element element) {
        element.setPosition(element.getPosition().getLeft(speed));

        int x = element.getPosition().getX();

        if(x < xLeftLimit) {
            int diff = xLeftLimit - x;
            element.setPosition(element.getPosition().getRight(diff + diff));

            this.direction = Direction.DOWN_RIGHT;
        }
    }

    private void moveRight(Element element) {
        element.setPosition(element.getPosition().getRight(speed));

        int x = element.getPosition().getX();

        if(x > xRightLimit) {
            int diff = x-xRightLimit;
            element.setPosition(element.getPosition().getLeft(diff + diff));

            this.direction = Direction.DOWN_LEFT;
        }
    }

    @Override
    public Element move(Element element) {
        element.setPosition(element.getPosition().getDown(speed));

        switch (this.direction) {
            case DOWN_LEFT:
                this.moveLeft(element);
                break;
            case DOWN_RIGHT:
                this.moveRight(element);
                break;
        }

        return element;
    }
}
