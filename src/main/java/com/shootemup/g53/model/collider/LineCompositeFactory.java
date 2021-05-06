package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

import java.util.HashMap;

public class LineCompositeFactory {
    LineCompositeCollider createFromSquare(Element element, Position topLeft, int width, int height) {
        HashMap<Integer, LineCollider> colliderHashMap = new HashMap<>();

        for(int y = topLeft.getY(); y <= topLeft.getY() + height; y++) {
            colliderHashMap.put(y, new LineCollider(element, new Position(topLeft.getX(), topLeft.getY()+y), width));
        }

        return new LineCompositeCollider(element, colliderHashMap);
    }
}
