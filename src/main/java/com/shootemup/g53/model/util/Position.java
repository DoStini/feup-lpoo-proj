package com.shootemup.g53.model.util;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getRight(int offset) {
        return new Position(this.x + offset, this.y);
    }

    public Position getLeft(int offset) {
        return new Position(this.x - offset, this.y);
    }

    public Position getUp(int offset) {
        return new Position(this.x, this.y - offset);
    }

    public Position getDown(int offset) {
        return new Position(this.x, this.y + offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
