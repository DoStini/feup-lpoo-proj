package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;

public abstract class BlockCollider implements Collider<BlockCollider> {
    Element element;
    BoundingBox boundingBox;

    BlockCollider(Element element) {
        this.element = element;
        boundingBox = createBoundingBox();
    }

    BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    abstract protected BoundingBox createBoundingBox();
}
