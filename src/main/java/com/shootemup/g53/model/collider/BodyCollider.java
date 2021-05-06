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

    public BoundingBox getBoundingBox() {
        if(this.boundingBox == null) this.boundingBox = createBoundingBox();
        return this.boundingBox;
    }

    abstract protected BoundingBox createBoundingBox();

    public boolean collides(BodyCollider other) {
        if(!other.getBoundingBox().collides(this.getBoundingBox())) return false;
        else return innerVisit(other);
    }

    abstract protected boolean innerVisit(BodyCollider other);
    abstract protected boolean collidesLine(LineCollider other);
}
