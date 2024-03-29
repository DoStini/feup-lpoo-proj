package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class MoveUpwardsMovement  extends IncrementalMovement {
    @Override
    Position moveFrame(Position position, int speed) {
        return position.getUp(speed);
    }

    @Override
    public MovementStrategy cloneStrategy() {
        return new MoveUpwardsMovement();
    }
}
