package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class LineCollider extends BodyCollider {
    protected final Position topLeft;
    protected final int width;

    public LineCollider(Element element, Position topLeft, int width) {
        super(element);

        this.topLeft = topLeft;
        this.width = width;
    }

    @Override
    protected BoundingBox createBoundingBox() {
        return new BoundingBoxFactory().createFromLine(this);
    }

    @Override
    protected boolean innerVisit(BodyCollider other) {
        return other.collidesLine(this);
    }

    @Override
    protected boolean collidesLine(LineCollider other) {
        int realLeftX = this.topLeft.getX() + this.element.getPosition().getX();
        int otherRealLeftX = other.topLeft.getX() + other.element.getPosition().getX();

        return realLeftX <= otherRealLeftX + other.width &&
                realLeftX + this.width >= otherRealLeftX;
    }

    @Override
    protected boolean collidesLineComposite(LineCompositeCollider other) {
        return other.collidesLine(this);
    }
}
