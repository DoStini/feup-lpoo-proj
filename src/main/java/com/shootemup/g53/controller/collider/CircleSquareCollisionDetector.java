package com.shootemup.g53.controller.collider;

import com.shootemup.g53.model.collider.CircleCollider;
import com.shootemup.g53.model.collider.Collider;
import com.shootemup.g53.model.collider.SquareCollider;
import com.shootemup.g53.model.util.Position;

public class CircleSquareCollisionDetector implements CollisionDetector{
    @Override
    public boolean collide(Collider first, Collider second) {
        SquareCollider square;
        CircleCollider circle;

        if(first.getClass() == SquareCollider.class && second.getClass() == CircleCollider.class) {
            square = (SquareCollider) first;
            circle = (CircleCollider) second;
        } else if(second.getClass() == SquareCollider.class && first.getClass() == CircleCollider.class) {
            square = (SquareCollider) second;
            circle = (CircleCollider) first;
        } else {
            return false;
        }

        int closestX, closestY;
        int circleX = circle.getCenter().getX(), circleY = circle.getCenter().getY();
        int squareLeftX = square.getTopLeft().getX(), squareRightX = square.getTopLeft().getX() + square.getWidth();
        int squareTopY = square.getTopLeft().getY(), squareBottomY = square.getTopLeft().getY() + square.getHeight();

        if(circleX < squareLeftX) {
            closestX = squareLeftX;
        } else closestX = Math.min(circleX, squareRightX);

        if(circleY < squareTopY) {
            closestY = squareTopY;
        } else closestY = Math.min(circleY, squareBottomY);

        Position closestPoint = new Position(closestX, closestY);

        return closestPoint.getSquaredDistance(circle.getCenter()) < circle.getRadius()*circle.getRadius();
    }
}
