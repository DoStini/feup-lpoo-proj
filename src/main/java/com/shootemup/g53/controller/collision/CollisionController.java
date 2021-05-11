package com.shootemup.g53.controller.collision;

import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollisionController {
    private List<BodyCollider> colliders;
    private List<CollisionHandler<? extends Element>> handlers;

    public CollisionController(List<BodyCollider> colliders) {
        this.colliders = colliders;
        this.handlers = new ArrayList<>();
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
                    notify(firstCollider, secondCollider);
                    collision = true;
                }
            }
        }

        return collision;
    }

    public void addHandler(CollisionHandler<? extends Element> handler) {
        handlers.add(handler);
    }

    private void notify(BodyCollider first, BodyCollider second) {
        for(CollisionHandler<? extends Element> handler : handlers) {
            if(first.getElement() == handler.getElement()) {
                handler.handleCollision(second);
            } else if(second.getElement() == handler.getElement()) {
                handler.handleCollision(first);
            }
        }
    }
}
