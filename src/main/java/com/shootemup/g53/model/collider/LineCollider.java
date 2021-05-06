package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class LineCollider extends BodyCollider {
    Position topLeft;
    int width;

    public LineCollider(Element element, Position topLeft, int width) {
        super(element);

        this.topLeft = topLeft;
        this.width = width;
    }

    @Override
    protected BoundingBox createBoundingBox() {
        return null;
    }


    @Override
    public boolean collides(BodyCollider other) {
        return other.collidesInner(this);
    }

    @Override
    protected boolean collidesInner(LineCollider other) {
        return false;
    }
}
