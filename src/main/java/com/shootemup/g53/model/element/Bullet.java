package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Bullet extends MovableElement implements PoolableObject {

    private int size;
    private int damage;
    private boolean active = false;


    public Bullet(Position position, String color,double speed, int size, int damage) {
        super(position, color, speed);
        this.size = size;
        this.active = true;
        this.damage = damage;
    }

    public int getSize() {
        return size;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public PoolableObject clone() {
        return new Bullet(new Position(position.getX(), position.getY()), getColor(),getSpeed(), size, this.damage);
    }

    public void init(int x, int y, String color, int size, double speed, int damage) {

        position.setX(x);
        position.setY(y);
        setColor(color);
        this.size = size;
        setSpeed(speed);
        this.damage = damage;
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
