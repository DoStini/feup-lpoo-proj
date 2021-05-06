package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BodyColliderTest {
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
    void bodyColliderDispatch() {
        BodyCollider collider = Mockito.spy(new LineCollider(element1, new Position(0,0),1));
        BodyCollider collider2 = Mockito.spy(new LineCollider(element1, new Position(0,0),1));

        Mockito.verify(collider, Mockito.times(0)).createBoundingBox();
        Mockito.verify(collider2, Mockito.times(0)).createBoundingBox();

        collider.collides(collider2);

        Mockito.verify(collider, Mockito.times(1)).createBoundingBox();
        Mockito.verify(collider, Mockito.times(1)).getRealBoundingBox();
        Mockito.verify(collider, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(collider, Mockito.times(0)).collidesLine(Mockito.any());

        Mockito.verify(collider2, Mockito.times(1)).createBoundingBox();
        Mockito.verify(collider2, Mockito.times(1)).getRealBoundingBox();
        Mockito.verify(collider2, Mockito.times(0)).innerVisit(Mockito.any());
        Mockito.verify(collider2, Mockito.times(1)).collidesLine(Mockito.any());

        collider2.collides(collider);

        Mockito.verify(collider, Mockito.times(1)).createBoundingBox();
        Mockito.verify(collider, Mockito.times(2)).getRealBoundingBox();
        Mockito.verify(collider, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(collider, Mockito.times(1)).collidesLine(Mockito.any());

        Mockito.verify(collider2, Mockito.times(1)).createBoundingBox();
        Mockito.verify(collider2, Mockito.times(2)).getRealBoundingBox();
        Mockito.verify(collider2, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(collider2, Mockito.times(1)).collidesLine(Mockito.any());
    }

    @Test
    void boundingBoxCreation() {
        BodyCollider collider = Mockito.spy(new LineCollider(element1, new Position(0,0),2));
        BodyCollider collider2 = Mockito.spy(new LineCollider(element2, new Position(1,1),4));

        BoundingBox collider1BB = collider.getRealBoundingBox();
        Assertions.assertEquals(element1.getPosition(), collider1BB.getTopLeft());
        Assertions.assertEquals(2, collider1BB.getWidth());
        Assertions.assertEquals(0, collider1BB.getHeight());

        BoundingBox collider2BB = collider2.getRealBoundingBox();
        Assertions.assertEquals(element2.getPosition().add(new Position(1,1)), collider2BB.getTopLeft());
        Assertions.assertEquals(4, collider2BB.getWidth());
        Assertions.assertEquals(0, collider2BB.getHeight());
    }

    @Test
    void integratedTest() {
        BodyCollider collider = Mockito.spy(new LineCollider(element1, new Position(0,0),2));
        BodyCollider collider2 = Mockito.spy(new LineCollider(element2, new Position(1,1),4));

        Assertions.assertFalse(collider.collides(collider2));
        Mockito.verify(collider, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(collider, Mockito.times(0)).innerVisit(Mockito.any()); // bounding dont collide

        BodyCollider collider3 = Mockito.spy(new LineCollider(element2, new Position(-1,0),2));

        Assertions.assertTrue(collider3.collides(collider));
        Mockito.verify(collider3, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(collider3, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(collider, Mockito.times(1)).collidesLine(Mockito.any());

        Assertions.assertTrue(collider.collides(collider3));
        Mockito.verify(collider, Mockito.times(2)).collides(Mockito.any());
        Mockito.verify(collider, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(collider3, Mockito.times(1)).collidesLine(Mockito.any());

        BodyCollider collider4 = Mockito.spy(new LineCollider(element3, new Position(-1,-2),2));

        Assertions.assertTrue(collider.collides(collider4));
        Assertions.assertTrue(collider4.collides(collider));
    }
}