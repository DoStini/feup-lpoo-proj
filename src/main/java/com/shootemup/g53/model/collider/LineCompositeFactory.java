package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

import java.util.HashMap;

public class LineCompositeFactory {
    LineCompositeCollider createFromSquare(Element element, Position topLeft, int width, int height) {
        HashMap<Integer, LineCollider> colliderHashMap = new HashMap<>();

        for(int y = topLeft.getY(); y <= topLeft.getY() + height; y++) {
            colliderHashMap.put(y, new LineCollider(element, new Position(topLeft.getX(), y), width));
        }

        return new LineCompositeCollider(element, colliderHashMap);
    }

    LineCompositeCollider createFromVerticalLine(Element element, Position topLeft, int height) {
        HashMap<Integer, LineCollider> colliderHashMap = new HashMap<>();

        for(int y = topLeft.getY(); y <= topLeft.getY() + height; y++) {
            colliderHashMap.put(y, new LineCollider(element, new Position(topLeft.getX(), y), 0));
        }

        return new LineCompositeCollider(element, colliderHashMap);
    }

    LineCompositeCollider createFromIsoscelesTriangle(Element element, Position top, int height) {
        HashMap<Integer, LineCollider> colliderHashMap = new HashMap<>();

        for(int y = 0; y <= height; y++) {
            colliderHashMap.put(
                    y+top.getY(), new LineCollider(element, new Position(top.getX()-y, y+ top.getY()), 2*(y))
            );
        }

        return new LineCompositeCollider(element, colliderHashMap);
    }

    LineCompositeCollider createFromCircle(Element element, Position center, int radius) {
        HashMap<Integer, LineCollider> colliderHashMap = new HashMap<>();

        if(radius == 0) return  new LineCompositeCollider(element, colliderHashMap);

        colliderHashMap.put(
                center.getY(), new LineCollider(element, center.getLeft(radius), 2*radius)
        );

        for(int r = 1; r < radius+1; r++) {
            int currentRadius = radius - r;

            colliderHashMap.put(
                    center.getY()+r, new LineCollider(element, center.getLeft(currentRadius).getUp(r), 2*(currentRadius))
            );
            colliderHashMap.put(
                    center.getY()-r, new LineCollider(element, center.getLeft(currentRadius).getDown(r), 2*(currentRadius))
            );
        }

        return new LineCompositeCollider(element, colliderHashMap);
    }
}
