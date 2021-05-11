package com.shootemup.g53.controller.collision;

import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Element;

public abstract class CollisionHandler<T extends Element> {
    private T element;

    CollisionHandler(T element, CollisionController controller) {
        controller.addHandler(this);
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public abstract void handleCollision(BodyCollider other);
}
