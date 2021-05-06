package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

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
    void integratedLineComposite() {
        LineCollider collider = Mockito.spy(new LineCollider(element1, new Position(0,0),2));
        LineCollider collider2 = Mockito.spy(new LineCollider(element1, new Position(1,1),4));
        LineCollider collider3 = Mockito.spy(new LineCollider(element1, new Position(-1,2),8));
        LineCollider collider4 = Mockito.spy(new LineCollider(element1, new Position(0,-1),0));
        BodyCollider collider5 = Mockito.spy(new LineCollider(element1, new Position(0,-3),0));
        BodyCollider collider6 = Mockito.spy(new LineCollider(element2, new Position(-2, 1), 0));

        List<LineCollider> colliders = Arrays.asList(collider, collider2, collider3, collider4);

        BodyCollider compositeCollider = Mockito.spy(new LineCompositeCollider(element1, colliders));

        Assertions.assertFalse(compositeCollider.collides(collider5));
        Mockito.verify(compositeCollider, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(0)).innerVisit(Mockito.any()); // bounding dont collide

        Assertions.assertFalse(collider5.collides(compositeCollider));
        Mockito.verify(collider5, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(collider5, Mockito.times(0)).innerVisit(Mockito.any()); // bounding dont collide

        Assertions.assertTrue(compositeCollider.collides(collider));
        Mockito.verify(compositeCollider, Mockito.times(2)).collides(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(collider, Mockito.times(1)).collidesLineComposite(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(1)).collidesLine(Mockito.any());

        Assertions.assertTrue(collider.collides(compositeCollider));
        Mockito.verify(collider, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(collider, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(2)).collidesLine(Mockito.any());

        Assertions.assertFalse(compositeCollider.collides(collider6));
        Mockito.verify(compositeCollider, Mockito.times(3)).collides(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(2)).innerVisit(Mockito.any());
        Mockito.verify(collider6, Mockito.times(1)).collidesLineComposite(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(3)).collidesLine(Mockito.any());

        Assertions.assertFalse(collider6.collides(compositeCollider));
        Mockito.verify(collider6, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(collider6, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(4)).collidesLine(Mockito.any());
    }

    @Test
    void compositeToLineCollision() {
        LineCollider collider = new LineCollider(element1, new Position(0,0),2);
        LineCollider collider2 = new LineCollider(element1, new Position(1,1),4);
        LineCollider collider3 = new LineCollider(element1, new Position(-1,2),8);
        LineCollider collider4 = new LineCollider(element1, new Position(0,-1),0);
        LineCollider collider5 = new LineCollider(element1, new Position(0,-3),0);
        LineCollider collider6 = new LineCollider(element2, new Position(-1, 0), 0);
        LineCollider collider7 = new LineCollider(element2, new Position(-4, 0), 1);
        LineCollider collider8 = new LineCollider(element2, new Position(-4, 0), 2);
        LineCollider collider9 = new LineCollider(element3, new Position(-2, -2), 0);


        List<LineCollider> colliders = Arrays.asList(collider, collider2, collider3, collider4);

        LineCompositeCollider compositeCollider = new LineCompositeCollider(element1, colliders);

        Assertions.assertFalse(compositeCollider.collidesLine(collider5));
        Assertions.assertFalse(collider5.collidesLineComposite(compositeCollider));

        Assertions.assertTrue(compositeCollider.collidesLine(collider));
        Assertions.assertTrue(collider.collidesLineComposite(compositeCollider));

        Assertions.assertTrue(compositeCollider.collidesLine(collider6));
        Assertions.assertTrue(collider6.collidesLineComposite(compositeCollider));

        Assertions.assertFalse(compositeCollider.collidesLine(collider7));
        Assertions.assertFalse(collider7.collidesLineComposite(compositeCollider));

        Assertions.assertTrue(compositeCollider.collidesLine(collider8));
        Assertions.assertTrue(collider8.collidesLineComposite(compositeCollider));

        Assertions.assertTrue(compositeCollider.collidesLine(collider9));
        Assertions.assertTrue(collider9.collidesLineComposite(compositeCollider));

        LineCompositeCollider compositeCollider2 = new LineCompositeCollider(element3, colliders);

        Assertions.assertTrue(collider3.collidesLineComposite(compositeCollider2));
        Assertions.assertTrue(compositeCollider2.collidesLine(collider3));
    }
}
