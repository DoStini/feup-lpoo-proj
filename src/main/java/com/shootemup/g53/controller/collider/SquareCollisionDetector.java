package com.shootemup.g53.controller.collider;

import com.shootemup.g53.model.collider.Collider;
import com.shootemup.g53.model.collider.SquareCollider;

public class SquareCollisionDetector implements CollisionDetector {
    @Override
    public boolean collide(Collider firstC, Collider secondC) {
        SquareCollider first, second;

        if(firstC.getClass() == SquareCollider.class) first = (SquareCollider) firstC;
        else return false;
        if(secondC.getClass() == SquareCollider.class) second = (SquareCollider) secondC;
        else return false;

        return !(
                first.getTopLeft().getX()+first.getWidth() < second.getTopLeft().getX() ||
                first.getTopLeft().getX() > second.getTopLeft().getX()+second.getWidth() ||
                first.getTopLeft().getY()+first.getHeight() < second.getTopLeft().getY() ||
                first.getTopLeft().getY() > second.getTopLeft().getY()+second.getHeight()
        );
    }
}
