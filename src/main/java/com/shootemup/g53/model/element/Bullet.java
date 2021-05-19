package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Bullet extends MovableElement implements PoolableObject {

    private int size;
    private boolean active = false;

    public Bullet(Position position, String color,int speed, int size) {
        super(position, color, speed);
        this.size = size;
        this.active = true;
    }

    public int getSize() {
        return size;
    }

    @Override
    public PoolableObject clone() {
        return new Bullet(getPosition(), getColor(),getSpeed(), size);
    }

    public void init(int x, int y, String color, int size,int speed) {
        position.setX(x);
        position.setY(y);
        setColor(color);
        this.size = size;
        setSpeed(speed);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bullet)) return false;
        Bullet other = (Bullet) o;
        return super.equals(o)
                && other.getSpeed() == this.getSpeed()
                && other.getSize() == this.getSize();
    }
}
