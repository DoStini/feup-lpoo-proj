package com.shootemup.g53.model.collider;

public interface Collider<T extends Collider<T>> {
    boolean collides(T other);
}
