package com.shootemup.g53.controller.collision;

import com.shootemup.g53.controller.element.CollisionHandlerController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CollisionControllerTest {
    List<BodyCollider> colliders;
    Element element1;
    Element spaceship;
    Asteroid asteroid;
    Bullet bullet;
    Element element2;
    GameModel model;
    GameController controller;
    CollisionHandlerController element1Controller;
    CollisionHandlerController element2Controller;
    CollisionHandlerController asteroidController;

    @BeforeEach
    void setUp() {
        colliders = new ArrayList<>();

        element1 = Mockito.mock(Element.class);
        spaceship = Mockito.mock(Spaceship.class);
        asteroid = Mockito.mock(Asteroid.class);
        bullet = Mockito.mock(Bullet.class);
        element2 = Mockito.mock(Element.class);
        model = Mockito.mock(GameModel.class, Mockito.CALLS_REAL_METHODS);
        controller = Mockito.mock(GameController.class);
        element1Controller = Mockito.mock(CollisionHandlerController.class);
        element2Controller = Mockito.mock(CollisionHandlerController.class);
        asteroidController = Mockito.mock(CollisionHandlerController.class);

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

        Mockito.when(model.getColliders()).thenReturn(colliders);
        model.setColliders(colliders);
        Mockito.when(controller.getGameModel()).thenReturn(model);
    }

    @Test
    void checkCollision() {
        CollisionController collisionController = new CollisionController(controller);
        Mockito.when(controller.getCollisionHandler(Mockito.any())).thenReturn(Mockito.mock(CollisionHandlerController.class));

        Assertions.assertTrue(collisionController.checkCollisions());

        colliders.remove(0);
        colliders.remove(0);
        colliders.remove(0);
        colliders.remove(0);

        Assertions.assertFalse(collisionController.checkCollisions());
    }

    @Test
    void removeCollider() {
        BodyCollider removed = colliders.get(1);

        model.removeCollider(spaceship);

        Assertions.assertEquals(4, model.getColliders().size());
        Assertions.assertFalse(model.getColliders().contains(removed));

        model.addCollider(removed);

        Assertions.assertEquals(5, model.getColliders().size());
        Assertions.assertTrue(model.getColliders().contains(removed));
    }

    @Test
    void handlerCall() {
        CollisionController collisionController = new CollisionController(controller);

        Mockito.when(controller.getCollisionHandler(element1)).thenReturn(element1Controller);
        Mockito.when(controller.getCollisionHandler(element2)).thenReturn(element1Controller);
        Mockito.when(controller.getCollisionHandler(asteroid)).thenReturn(asteroidController);

        collisionController.checkCollisions();
        Mockito.verify(element1Controller, Mockito.times(1)).handleCollision(Mockito.any());
        Mockito.verify(element2Controller, Mockito.times(0)).handleCollision(Mockito.any());
        Mockito.verify(asteroidController, Mockito.times(1)).handleCollision(Mockito.any());
    }
}