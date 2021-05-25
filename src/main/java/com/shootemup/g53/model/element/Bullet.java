package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Bullet extends MovableElement implements PoolableObject {

    private int size;
    private boolean active = false;

    public Bullet(Position position, String color, double speed, int size) {
        super(position, color, speed);
        this.size = size;
        this.active = true;
    }

    public int getSize() {
        return size;
    }

    @Override
    public PoolableObject clone() {
        return new Bullet(new Position(position.getX(), position.getY()), getColor(),getSpeed(), size);
    }

    public void init(int x, int y, String color, int size,double speed) {
        position.setX(x);
        position.setY(y);
        setColor(color);
        this.size = size;
        setSpeed(speed);

    }
}
