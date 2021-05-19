package com.shootemup.g53.controller.collision;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.game.GameModel;

import java.util.List;

public class CollisionController {
    protected GameController controller;

    public CollisionController(GameController controller) {
        this.controller = controller;
    }

    public boolean checkCollisions() {
        boolean collision = false;
        BodyCollider firstCollider, secondCollider;
        List<BodyCollider> colliders = controller.getGameModel().getColliders();

        for(int i = 0; i < colliders.size(); i++) {
            firstCollider = colliders.get(i);
            for(int j = i+1; j < colliders.size(); j++) {
                secondCollider = colliders.get(j);

                if(firstCollider.collides(secondCollider)) {
                    controller.getCollisionHandler(firstCollider.getElement()).handleCollision(secondCollider);
                    controller.getCollisionHandler(secondCollider.getElement()).handleCollision(firstCollider);
                    collision = true;
                }
            }
        }

        return collision;
    }
}
