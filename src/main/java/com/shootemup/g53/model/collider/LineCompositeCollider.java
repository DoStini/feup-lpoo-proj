package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LineCompositeCollider extends BodyCollider {
    private final HashMap<Integer, LineCollider> colliderHashMap;

    public LineCompositeCollider(Element element, List<LineCollider> lineColliders) {
        super(element);

        colliderHashMap = new HashMap<>();

        for (LineCollider collider : lineColliders) {
            colliderHashMap.put(collider.topLeft.getY(), collider);
        }
    }

    public LineCompositeCollider(Element element, HashMap<Integer, LineCollider> lineColliders) {
        super(element);

        colliderHashMap = lineColliders;
    }

    @Override
    protected BoundingBox createBoundingBox() {
        return new BoundingBoxFactory().createFromLineComposite(this);
    }

    @Override
    protected boolean innerVisit(BodyCollider other) {
        return other.collidesLineComposite(this);
    }

    @Override
    protected boolean collidesLine(LineCollider other) {
        int realOtherY = other.topLeft.getY() + other.element.getPosition().getY();

        LineCollider sameHeightCollider = colliderHashMap.get(realOtherY-element.getPosition().getY());

        return sameHeightCollider != null && sameHeightCollider.collidesLine(other);
    }

    @Override
    protected boolean collidesLineComposite(LineCompositeCollider other) {
        int thisBoundingBoxY = getBoundingBox().getTopLeft().getY() + this.element.getPosition().getY();
        int otherBoundingBoxY = other.getBoundingBox().getTopLeft().getY() + other.element.getPosition().getY();

        int topY, botY;

        topY = Math.max(thisBoundingBoxY, otherBoundingBoxY);
        botY = Math.min(
                thisBoundingBoxY + this.boundingBox.getHeight(), otherBoundingBoxY + other.boundingBox.getHeight()
        );

        LineCollider thisHeightCollider, otherHeightCollider;

        for(int y = topY; y <= botY; y++) {
            thisHeightCollider = colliderHashMap.get(y-element.getPosition().getY());
            if(thisHeightCollider == null) continue;
            otherHeightCollider = other.getColliderHashMap().get(y-other.element.getPosition().getY());
            if(otherHeightCollider == null) continue;

            if(thisHeightCollider.collidesLine(otherHeightCollider)) return true;
        }

        return false;
    }

    protected HashMap<Integer, LineCollider> getColliderHashMap() {
        return colliderHashMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineCompositeCollider)) return false;
        if (!super.equals(o)) return false;
        LineCompositeCollider that = (LineCompositeCollider) o;
        return colliderHashMap.equals(that.colliderHashMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colliderHashMap);
    }
}
