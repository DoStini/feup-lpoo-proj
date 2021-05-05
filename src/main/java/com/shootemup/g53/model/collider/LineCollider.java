package com.shootemup.g53.model.collider;

public class LineCollider extends BlockCollider {
    @Override
    protected BoundingBox createBoundingBox() {
        return null;
    }

    @Override
    public boolean collides(BlockCollider other) {
        return false;
    }
}
