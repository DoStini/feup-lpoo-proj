package com.shootemup.g53.controller.collision;

import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Element;

public abstract class CollisionHandler<T extends Element> {
    private T element;
    private Class<?> wantedClass;

    CollisionHandler(T element, Class<?> wantedClass, CollisionController controller) {
        controller.addHandler(this);
        this.element = element;
        this.wantedClass = wantedClass;
    }

    public T getElement() {
        return element;
    }

    public Class<?> getWantedClass() {
        return wantedClass;
    }

    public abstract void handleCollision(BodyCollider other);
}
