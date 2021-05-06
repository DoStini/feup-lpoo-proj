package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

import java.util.HashMap;
import java.util.List;

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
        BoundingBox thisBoundingBox = getRealBoundingBox();
        BoundingBox otherBoundingBox = other.getRealBoundingBox();

        int topY, botY;

        topY = Math.max(thisBoundingBox.getTopLeft().getY(), otherBoundingBox.getTopLeft().getY());
        botY = Math.min(thisBoundingBox.getTopLeft().getY()+thisBoundingBox.getHeight(),
                otherBoundingBox.getTopLeft().getY() + otherBoundingBox.getHeight());

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
}
