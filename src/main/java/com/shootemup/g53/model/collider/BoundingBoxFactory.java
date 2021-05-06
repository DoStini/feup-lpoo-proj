package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;

public class BoundingBoxFactory {
    public BoundingBox createFromLine(LineCollider line) {
        return new BoundingBox(new Position(line.topLeft.getX(), line.topLeft.getY()), line.width, 0);
    }
}
