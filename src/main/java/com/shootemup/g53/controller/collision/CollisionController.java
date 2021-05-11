package com.shootemup.g53.controller.collision;

import com.shootemup.g53.model.collider.BodyCollider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollisionController {
    private List<BodyCollider> colliders;

    public CollisionController(List<BodyCollider> colliders) {
        this.colliders = colliders;
    }

    public CollisionController() {
        this(new ArrayList<>());
    }

    public void addCollider(BodyCollider collider) {
        colliders.add(collider);
    }

    public boolean checkCollisions() {
        boolean collision = false;
        BodyCollider firstCollider, secondCollider;

        for(int i = 0; i < colliders.size(); i++) {
            firstCollider = colliders.get(i);
            for(int j = i+1; j < colliders.size(); j++) {
                secondCollider = colliders.get(j);

                if(firstCollider.collides(secondCollider)) {
                    collision = true;
                }
            }
        }

        return collision;
    }
}
