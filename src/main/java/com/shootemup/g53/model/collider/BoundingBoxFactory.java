package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;

import java.util.HashMap;
import java.util.Map;

public class BoundingBoxFactory {
    public BoundingBox createFromLine(LineCollider line) {
        return new BoundingBox(new Position(line.topLeft.getX(), line.topLeft.getY()), line.width, 0);
    }

    public BoundingBox createFromLineComposite(LineCompositeCollider composite) {
        int leftX = Integer.MAX_VALUE, rightX=Integer.MIN_VALUE, topY=Integer.MAX_VALUE, botY=Integer.MIN_VALUE;

        HashMap<Integer, LineCollider> colliderHashMap = composite.getColliderHashMap();
        LineCollider collider;

        for(Map.Entry<Integer, LineCollider> colliderEntry : colliderHashMap.entrySet()) {
            collider = colliderEntry.getValue();

            if(collider.topLeft.getX() < leftX) leftX = collider.topLeft.getX();
            if(collider.topLeft.getY() < topY) topY = collider.topLeft.getY();
            if(collider.topLeft.getX() + collider.width > rightX) rightX = collider.topLeft.getX() + collider.width;
            if(collider.topLeft.getY() > botY) botY = collider.topLeft.getY();
        }

        int width = rightX - leftX;
        int height = botY - topY;

        return new BoundingBox(new Position(leftX, topY), width, height);
    }
}
