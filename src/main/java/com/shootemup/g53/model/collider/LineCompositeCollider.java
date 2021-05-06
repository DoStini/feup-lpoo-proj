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
        return false;
    }

    protected HashMap<Integer, LineCollider> getColliderHashMap() {
        return colliderHashMap;
    }
}
