package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

// idea from https://stackoverflow.com/questions/22899363/advice-on-class-structure-in-a-collision-detection-system
// based on this https://refactoring.guru/design-patterns/visitor design pattern
public abstract class BodyCollider {
    protected Element element;
    protected BoundingBox boundingBox;

    protected BodyCollider(Element element) {
        this.element = element;
        this.boundingBox = null;
    }

    public BoundingBox getBoundingBox() {
        if(this.boundingBox == null) this.boundingBox = createBoundingBox();
        return boundingBox;
    }

    abstract protected BoundingBox createBoundingBox();

    public boolean collides(BodyCollider other) {
        if(!this.getBoundingBox().collides(
                other.getBoundingBox(), this.element.getPosition(), other.element.getPosition())
        ) return false;
        else return innerVisit(other);
    }

    public Element getElement() {
        return element;
    }

    abstract protected boolean innerVisit(BodyCollider other);
    abstract protected boolean collidesLine(LineCollider other);
    abstract protected boolean collidesLineComposite(LineCompositeCollider other);
}
