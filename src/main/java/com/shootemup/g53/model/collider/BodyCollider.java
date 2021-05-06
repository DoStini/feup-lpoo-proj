package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

// idea from https://stackoverflow.com/questions/22899363/advice-on-class-structure-in-a-collision-detection-system
// based on this https://refactoring.guru/design-patterns/visitor design pattern
public abstract class BodyCollider {
    Element element;
    BoundingBox boundingBox;

    protected BodyCollider(Element element) {
        this.element = element;
        this.boundingBox = null;
    }

    BoundingBox getBoundingBox() {
        if(this.boundingBox == null) this.boundingBox = createBoundingBox();
        return this.boundingBox;
    }

    abstract protected BoundingBox createBoundingBox();

    abstract public boolean collides(BodyCollider other);
    abstract protected boolean collidesInner(LineCollider other);
}
