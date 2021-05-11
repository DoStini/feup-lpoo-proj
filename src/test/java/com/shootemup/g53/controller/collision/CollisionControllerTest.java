package com.shootemup.g53.controller.collision;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionControllerTest {
    List<BodyCollider> colliders;
    Element element1;
    Spaceship spaceship;
    Asteroid asteroid;
    Bullet bullet;
    Element element2;

    @BeforeEach
    void setUp() {
        colliders = new ArrayList<>();

        element1 = Mockito.mock(Element.class);
        spaceship = Mockito.mock(Spaceship.class);
        asteroid = Mockito.mock(Asteroid.class);
        bullet = Mockito.mock(Bullet.class);
        element2 = Mockito.mock(Element.class);

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

        Mockito.when(collider1.getElement()).thenReturn(element1);
        Mockito.when(collider2.getElement()).thenReturn(spaceship);
        Mockito.when(collider3.getElement()).thenReturn(asteroid);
        Mockito.when(collider4.getElement()).thenReturn(bullet);
        Mockito.when(collider5.getElement()).thenReturn(element2);
    }

    @Test
    void checkCollision() {
        CollisionController controller = new CollisionController(colliders);

        Assertions.assertTrue(controller.checkCollisions());
    }

    @Test
    void handlerCall() {
        CollisionController controller = new CollisionController(colliders);
        CollisionHandler<Element> handler = (CollisionHandler<Element>) Mockito.mock(CollisionHandler.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(handler.getElement()).thenReturn(element1);
        Mockito.doNothing().when(handler).handleCollision(Mockito.any());

        CollisionHandler<Element> handler2 = (CollisionHandler<Element>) Mockito.mock(CollisionHandler.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(handler2.getElement()).thenReturn(element2);
        Mockito.doNothing().when(handler2).handleCollision(Mockito.any());

        CollisionHandler<Element> handler3 = (CollisionHandler<Element>) Mockito.mock(CollisionHandler.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(handler3.getElement()).thenReturn(asteroid);
        Mockito.doNothing().when(handler3).handleCollision(Mockito.any());

        controller.addHandler(handler);
        controller.addHandler(handler2);
        controller.addHandler(handler3);

        controller.checkCollisions();

        Mockito.verify(handler, Mockito.times(1)).handleCollision(Mockito.any());
        Mockito.verify(handler2, Mockito.times(0)).handleCollision(Mockito.any());
        Mockito.verify(handler3, Mockito.times(1)).handleCollision(Mockito.any());
    }
}