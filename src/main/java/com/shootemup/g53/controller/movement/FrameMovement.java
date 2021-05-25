package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public abstract class FrameMovement implements MovementStrategy {
    protected int fireRate;
    protected int lastMove;
    protected int moveFrame;
    public void increaseFrame(){
        moveFrame++;
    }

    public int getFrame() {
        return moveFrame;
    }

    FrameMovement() {
        lastMove = 0;
        moveFrame = 0;
    }

    @Override
    public Position move(Position position, double speed) {
        increaseFrame();

        return moveFrame(position, (int)speed);
    }

    abstract Position moveFrame(Position position, int speed);
}
