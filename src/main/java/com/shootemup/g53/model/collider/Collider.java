package com.shootemup.g53.model.collider;

public interface Collider {
    boolean collide(Collider other);
    short getMask();
    short getCategory();
}
