package com.shootemup.g53.controller.collider;

import com.shootemup.g53.model.collider.CircleCollider;
import com.shootemup.g53.model.collider.Collider;

// https://lazyfoo.net/tutorials/SDL/29_circular_collision_detection/index.php
public class CircleCollisionDetector implements CollisionDetector{
    @Override
    public boolean collide(Collider firstC, Collider secondC) {
        CircleCollider first, second;

        if(firstC.getClass() == CircleCollider.class) first = (CircleCollider) firstC;
        else return false;
        if(secondC.getClass() == CircleCollider.class) second = (CircleCollider) secondC;
        else return false;

        double totalRadius = first.getRadius() + second.getRadius();
        double totalRadiusSquared = totalRadius * totalRadius;

        return first.getCenter().getSquaredDistance(second.getCenter()) < totalRadiusSquared;
    }
}
