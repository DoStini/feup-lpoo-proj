package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;

public class DiagonalBounceMovement extends IncrementalMovement {
    protected final Position initalPosition;
    protected final int xLeftLimit;
    protected final int xRightLimit;
    protected Direction direction;


    @Override
    Position moveFrame(Position position, int speed) {
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
    public MovementStrategy cloneStrategy() {
        return new DiagonalBounceMovement(xLeftLimit, xRightLimit, direction, new Position(initalPosition.getX(), initalPosition.getY()));
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
            newPosition = position.getRight(speed);
            this.direction = Direction.DOWN_RIGHT;
        }

        return newPosition;
    }

    private Position moveRight(Position position, int speed) {
        Position newPosition = position.getRight(speed);

        int x = newPosition.getX();

        if(x > initalPosition.getX() + xRightLimit) {
            newPosition = position.getLeft(speed);
              this.direction = Direction.DOWN_LEFT;
        }

        return newPosition;
    }


}
