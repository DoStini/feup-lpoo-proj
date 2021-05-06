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
        return null;
    }

    @Override
    protected boolean innerVisit(BodyCollider other) {
        return false;
    }

    @Override
    protected boolean collidesLine(LineCollider other) {
        return false;
    }

    protected HashMap<Integer, LineCollider> getColliderHashMap() {
        return colliderHashMap;
    }
}
