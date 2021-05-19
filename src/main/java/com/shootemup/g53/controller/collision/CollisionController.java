package com.shootemup.g53.controller.collision;

import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.collider.BodyCollider;

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
                    CollisionHandlerController handlerFirst = controller.getCollisionHandler(firstCollider.getElement());
                    CollisionHandlerController handlerSecond = controller.getCollisionHandler(secondCollider.getElement());

                    handlerFirst.handleCollision(firstCollider, secondCollider, handlerSecond);
                    handlerSecond.handleCollision(secondCollider, firstCollider, handlerFirst);

                    collision = true;
                }
            }
        }

        return collision;
    }
}
