package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class LineCompositeTest {
    private Element element1, element2;

    @BeforeEach
    void setUp() {
        this.element1 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        this.element1.setPosition(new Position(5,3));
        this.element2 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        this.element2.setPosition(new Position(2, 2));
    }

    @Test
    void lineCompositeIntegrated() {
        LineCollider collider1 = new LineCollider(element1, new Position(0, 0), 0);
        LineCollider collider2 = new LineCollider(element1, new Position(-1, -1), 2);
        LineCollider collider3 = new LineCollider(element1, new Position(-2, -2), 4);
        LineCollider collider4 = new LineCollider(element1, new Position(-3, -3), 6);

        List<LineCollider> colliders = Arrays.asList(collider1, collider2, collider3, collider4);

        BodyCollider compositeCollider = Mockito.spy(new LineCompositeCollider(element1, colliders));

        LineCollider collider5 = new LineCollider(element2, new Position(0,0),0);
        LineCollider collider6 = new LineCollider(element2, new Position(-1,1),2);
        LineCollider collider7 = new LineCollider(element2, new Position(-2,2),4);

        List<LineCollider> colliders2 = Arrays.asList(collider5, collider6, collider7);

        BodyCollider compositeCollider2 = Mockito.spy(new LineCompositeCollider(element2, colliders2));

        Assertions.assertFalse(compositeCollider.collides(compositeCollider2));
        Mockito.verify(compositeCollider, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(compositeCollider2, Mockito.times(1)).collidesLineComposite(Mockito.any());

        Assertions.assertFalse(compositeCollider2.collides(compositeCollider));
        Mockito.verify(compositeCollider2, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(compositeCollider2, Mockito.times(1)).innerVisit(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(1)).collidesLineComposite(Mockito.any());

        Assertions.assertTrue(compositeCollider.collides(compositeCollider));
        Mockito.verify(compositeCollider, Mockito.times(2)).collides(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(2)).innerVisit(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(2)).collidesLineComposite(Mockito.any());

        Assertions.assertTrue(compositeCollider2.collides(compositeCollider2));
        Mockito.verify(compositeCollider2, Mockito.times(2)).collides(Mockito.any());
        Mockito.verify(compositeCollider2, Mockito.times(2)).innerVisit(Mockito.any());
        Mockito.verify(compositeCollider2, Mockito.times(2)).collidesLineComposite(Mockito.any());

        LineCollider collider8 = new LineCollider(element2, new Position(-2,0),1);
        LineCollider collider9 = new LineCollider(element2, new Position(-1,1),0);
        LineCollider collider10 = new LineCollider(element2, new Position(-1,2),0);

        List<LineCollider> colliders3 = Arrays.asList(collider8, collider9, collider10);

        BodyCollider compositeCollider3 = Mockito.spy(new LineCompositeCollider(element2, colliders3));

        Assertions.assertFalse(compositeCollider3.collides(compositeCollider));
        Mockito.verify(compositeCollider3, Mockito.times(1)).collides(Mockito.any());
        Mockito.verify(compositeCollider3, Mockito.times(0)).innerVisit(Mockito.any()); // bounding dont collide

        Assertions.assertFalse(compositeCollider.collides(compositeCollider3));
        Mockito.verify(compositeCollider, Mockito.times(3)).collides(Mockito.any());
        Mockito.verify(compositeCollider, Mockito.times(2)).innerVisit(Mockito.any()); // bounding dont collide
    }


    @Test
    void lineCompositeCollision() {
        LineCollider collider1 = new LineCollider(element1, new Position(0, 0), 0);
        LineCollider collider2 = new LineCollider(element1, new Position(-1, -1), 2);
        LineCollider collider3 = new LineCollider(element1, new Position(-2, -2), 4);
        LineCollider collider4 = new LineCollider(element1, new Position(-3, -3), 6);

        List<LineCollider> colliders = Arrays.asList(collider1, collider2, collider3, collider4);

        LineCompositeCollider compositeCollider = new LineCompositeCollider(element1, colliders);

        LineCollider collider5 = new LineCollider(element2, new Position(0,0),0);
        LineCollider collider6 = new LineCollider(element2, new Position(-1,1),2);
        LineCollider collider7 = new LineCollider(element2, new Position(-2,2),4);

        List<LineCollider> colliders2 = Arrays.asList(collider5, collider6, collider7);

        LineCompositeCollider compositeCollider2 = new LineCompositeCollider(element2, colliders2);

        Assertions.assertFalse(compositeCollider.collidesLineComposite(compositeCollider2));
        Assertions.assertFalse(compositeCollider2.collidesLineComposite(compositeCollider));

        Assertions.assertTrue(compositeCollider.collidesLineComposite(compositeCollider));
        Assertions.assertTrue(compositeCollider2.collidesLineComposite(compositeCollider2));

        element2.setPosition(new Position(2, 1));

        Assertions.assertFalse(compositeCollider.collidesLineComposite(compositeCollider2));
        Assertions.assertFalse(compositeCollider2.collidesLineComposite(compositeCollider));

        element2.setPosition(new Position(2, 0));

        Assertions.assertTrue(compositeCollider.collidesLineComposite(compositeCollider2));
        Assertions.assertTrue(compositeCollider2.collidesLineComposite(compositeCollider));

        LineCollider collider8 = new LineCollider(element2, new Position(-1,1),4);
        List<LineCollider> colliders3 = Arrays.asList(collider1, collider3, collider4);
        List<LineCollider> colliders4 = Arrays.asList(collider5, collider8, collider7);

        LineCompositeCollider compositeCollider3 = new LineCompositeCollider(element1, colliders3);
        LineCompositeCollider compositeCollider4 = new LineCompositeCollider(element2, colliders4);

        element2.setPosition(new Position(2, 2));

        Assertions.assertTrue(compositeCollider3.collidesLineComposite(compositeCollider4));
        Assertions.assertTrue(compositeCollider4.collidesLineComposite(compositeCollider3));

        LineCollider collider9 = new LineCollider(element2, new Position(-1,1),3);
        List<LineCollider> colliders5 = Arrays.asList(collider5, collider9, collider7);

        LineCompositeCollider compositeCollider5 = new LineCompositeCollider(element2, colliders5);

        Assertions.assertFalse(compositeCollider3.collidesLineComposite(compositeCollider5));
        Assertions.assertFalse(compositeCollider5.collidesLineComposite(compositeCollider3));
    }
}
