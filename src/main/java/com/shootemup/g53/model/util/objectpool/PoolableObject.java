package com.shootemup.g53.model.util.objectpool;

public interface PoolableObject {
    boolean isActive();
    void activate();
    void deactivate();
    PoolableObject clone();
}
