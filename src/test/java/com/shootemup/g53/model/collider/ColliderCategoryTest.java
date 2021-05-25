package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ColliderCategoryTest {
    private Element element1, element2, element3;

    @BeforeEach
    void setUp() {
        this.element1 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        this.element1.setPosition(new Position(0,0));
        this.element2 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        this.element2.setPosition(new Position(2, 0));
        this.element3 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        this.element3.setPosition(new Position(2, 2));
    }

    @Test
    void category() {
        BodyCollider collider1 = new LineCollider(element1, new Position(0,0),1);
        collider1.setCategory(ColliderCategory.PLAYER);
        collider1.setCategoryMask((short) (ColliderCategory.ENEMY_BULLET.getBits() | ColliderCategory.ENEMY.getBits()));

        BodyCollider collider2 = new LineCollider(element1, new Position(0,0),1);
        collider2.setCategory(ColliderCategory.PLAYER);
        collider2.setCategoryMask((short) (ColliderCategory.ENEMY_BULLET.getBits() | ColliderCategory.ENEMY.getBits()));

        Assertions.assertFalse(collider1.collides(collider2));
        Assertions.assertFalse(collider2.collides(collider1));


        BodyCollider collider3 = new LineCollider(element1, new Position(0,0),1);
        collider3.setCategory(ColliderCategory.ENEMY);
        collider3.setCategoryMask((short) (0));

        Assertions.assertFalse(collider3.collides(collider1));
        Assertions.assertFalse(collider1.collides(collider3));

        collider3.setCategoryMask(ColliderCategory.PLAYER.getBits());

        Assertions.assertTrue(collider3.collides(collider1));
        Assertions.assertTrue(collider1.collides(collider3));
        Assertions.assertTrue(collider3.collides(collider2));
        Assertions.assertTrue(collider2.collides(collider3));

        Assertions.assertFalse(collider1.collides(collider2));
        Assertions.assertFalse(collider2.collides(collider1));
    }
}
