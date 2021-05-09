package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Bullet extends MovableElement implements PoolableObject {

    private int size;
    private boolean active = false;

    public Bullet(Position position, String color,int speed, int size, MovementStrategy movementStrategy) {
        super(position, color, speed, movementStrategy);
        this.size = size;
        this.active = true;
    }

    public int getSize() {
        return size;
    }


    @Override
    public boolean isActive() { return active; }

    @Override
    public void activate() { active = true; }

    @Override
    public void deactivate() { active = false; }

    @Override
    public PoolableObject clone() {
        return new Bullet(getPosition(), getColor(),getSpeed(), size, movementStrategy);
    }

    public void init(int x, int y, String color, int size,int speed, MovementStrategy movementStrategy) {
        position.setX(x);
        position.setY(y);
        setColor(color);
        this.size = size;
        setSpeed(speed);
        setMovementController(movementStrategy);
    }
}
