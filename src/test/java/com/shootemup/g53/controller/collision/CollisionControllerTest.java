package com.shootemup.g53.controller.collision;

import com.shootemup.g53.model.collider.BodyCollider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionControllerTest {
    List<BodyCollider> colliders;

    @BeforeEach
    void setUp() {
        colliders = new ArrayList<>();

        BodyCollider collider1 = Mockito.mock(BodyCollider.class);
        colliders.add(collider1);

        BodyCollider collider2 = Mockito.mock(BodyCollider.class);
        Mockito.when(collider2.collides(Mockito.any())).thenReturn(false);
        colliders.add(collider2);

        BodyCollider collider3 = Mockito.mock(BodyCollider.class);
        Mockito.when(collider3.collides(collider1)).thenReturn(true);
        Mockito.when(collider1.collides(collider3)).thenReturn(true);
        colliders.add(collider3);

        BodyCollider collider4 = Mockito.mock(BodyCollider.class);
        Mockito.when(collider4.collides(Mockito.any())).thenReturn(false);
        colliders.add(collider4);

        BodyCollider collider5 = Mockito.mock(BodyCollider.class);
        Mockito.when(collider5.collides(Mockito.any())).thenReturn(false);
        colliders.add(collider5);
    }

    @Test
    void checkCollision() {
        CollisionController controller = new CollisionController(colliders);

        Assertions.assertTrue(controller.checkCollisions());
    }
}