package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

public class LineCollider extends BodyCollider {
    LineCollider(Element element) {
        super(element);
    }

    @Override
    protected BoundingBox createBoundingBox() {
        return null;
    }


    @Override
    public boolean collides(Collider other) {
        return other.collides(this);
    }

    @Override
    public boolean collides(LineCollider other) {
        return false;
    }
}
