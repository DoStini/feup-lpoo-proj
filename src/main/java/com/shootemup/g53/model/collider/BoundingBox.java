package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.util.Position;

public class BoundingBox {
    private final Position topLeft;
    private final int width;
    private final int height;

    public BoundingBox(Position topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean collides(BoundingBox other) {
        return  this.getTopLeft().getX() + this.getWidth() >= other.getTopLeft().getX() &&
                this.getTopLeft().getX() <= other.getTopLeft().getX() + other.getWidth() &&
                this.getTopLeft().getY() + this.getHeight() >= other.getTopLeft().getY() &&
                this.getTopLeft().getY() <= other.getTopLeft().getY() + other.getHeight();
    }

    public boolean collides(BoundingBox other, Position thisOffset, Position otherOffset) {
        int thisLeftX = this.topLeft.getX() + thisOffset.getX(), thisLeftY = this.topLeft.getY() + thisOffset.getY();
        int otherLeftX = other.topLeft.getX() + otherOffset.getX(), otherLeftY = other.topLeft.getY() + otherOffset.getY();

        return  thisLeftX + this.width >= otherLeftX &&
                thisLeftX <= otherLeftX + other.width &&
                thisLeftY + this.height >= otherLeftY &&
                thisLeftY <= otherLeftY + other.height;
    }
}
