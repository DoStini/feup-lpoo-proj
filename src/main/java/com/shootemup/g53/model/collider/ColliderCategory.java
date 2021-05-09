package com.shootemup.g53.model.collider;

public enum ColliderCategory {
    PLAYER((short)0x0001),
    ENEMY((short)0x0002),
    BULLET((short)0x0004)
    ;

    private final short bits;

    ColliderCategory(short bits) {
        this.bits = bits;
    }
}
