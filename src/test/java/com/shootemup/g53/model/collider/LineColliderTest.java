package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class LineColliderTest {
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
    void lineCollision() {
        LineCollider collider = new LineCollider(element1, new Position(0,0),2);
        LineCollider collider2 = new LineCollider(element2, new Position(1,1),4);
        LineCollider collider3 = new LineCollider(element2, new Position(-1,0),2);
        LineCollider collider4 = new LineCollider(element3, new Position(-1,-2),2);

        Assertions.assertFalse(collider.collidesLine(collider2));
        Assertions.assertFalse(collider2.collidesLine(collider));

        Assertions.assertTrue(collider3.collidesLine(collider));
        Assertions.assertTrue(collider.collidesLine(collider3));

        Assertions.assertTrue(collider.collidesLine(collider4));
        Assertions.assertTrue(collider4.collidesLine(collider));
    }
}
