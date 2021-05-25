package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public abstract class FrameMovement implements MovementStrategy {
    protected double currentDistance;

    FrameMovement() {
        currentDistance = 0;
    }

    @Override
    public Position move(Position position, double speed) {
        currentDistance += speed;
        Position newPosition;

        if(currentDistance >= 1) {
            newPosition = moveFrame(position, (int)currentDistance);

            currentDistance -= (int)currentDistance;
        } else {
            newPosition = position;
        }

        return newPosition;
    }

    abstract Position moveFrame(Position position, int speed);
}
