package com.shootemup.g53.model.collider;

// idea from https://stackoverflow.com/questions/22899363/advice-on-class-structure-in-a-collision-detection-system
// based on this https://refactoring.guru/design-patterns/visitor design pattern
public interface Collider {
    boolean collides(Collider other);
    boolean collides(LineCollider other);
}
