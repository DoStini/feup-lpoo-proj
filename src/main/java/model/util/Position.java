package model.util;

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

    public void setX(int x) {
        this.x = x;
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
}
