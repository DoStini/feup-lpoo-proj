package com.shootemup.g53.model.collider;

public enum ColliderCategory {
    GENERAL((short)0x0001),
    PLAYER((short)0x0002),
    ENEMY((short)0x0004),
    PLAYER_BULLET((short)0x0008),
    ENEMY_BULLET((short)0x0010),
    PICKUP((short)0x0020),
    SHIELD((short) 0x0040);

    private final short bits;

    ColliderCategory(short bits) {
        this.bits = bits;
    }

    public short getBits() {
        return bits;
    }
}
