package com.shootemup.g53.model.collider;

public class SquareCollisionDetector implements CollisionDetector<SquareCollider, SquareCollider> {
    @Override
    public boolean collide(SquareCollider first, SquareCollider second) {
        return !(
                first.getTopLeft().getX()+first.getWidth() < second.getTopLeft().getX() ||
                first.getTopLeft().getX() > second.getTopLeft().getX()+second.getWidth() ||
                first.getTopLeft().getY()+first.getHeight() < second.getTopLeft().getY() ||
                first.getTopLeft().getY() > second.getTopLeft().getY()+second.getHeight()
        );
    }
}
