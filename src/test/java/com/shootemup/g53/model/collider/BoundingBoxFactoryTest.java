package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoundingBoxFactoryTest {
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
    void boundingBoxLineCreation() {
        LineCollider collider = new LineCollider(element1, new Position(0,0),2);
        LineCollider collider2 = new LineCollider(element2, new Position(1,1),4);

        BoundingBoxFactory factory = new BoundingBoxFactory();

        BoundingBox collider1BB = factory.createFromLine(collider);
        Assertions.assertEquals(new Position(0,0), collider1BB.getTopLeft());
        Assertions.assertEquals(2, collider1BB.getWidth());
        Assertions.assertEquals(0, collider1BB.getHeight());

        BoundingBox collider2BB = factory.createFromLine(collider2);
        Assertions.assertEquals(new Position(1,1), collider2BB.getTopLeft());
        Assertions.assertEquals(4, collider2BB.getWidth());
        Assertions.assertEquals(0, collider2BB.getHeight());
    }

    @Test
    void boundingBoxLineCompositeCreation() {
        LineCollider collider = new LineCollider(element1, new Position(0,0),2);
        LineCollider collider2 = new LineCollider(element1, new Position(1,1),4);
        LineCollider collider3 = new LineCollider(element1, new Position(-1,2),8);
        LineCollider collider4 = new LineCollider(element1, new Position(0,-1),0);
        LineCollider collider5 = new LineCollider(element1, new Position(0,-3),0);


        List<LineCollider> colliders = Arrays.asList(collider, collider2, collider3, collider4);

        LineCompositeCollider compositeCollider = new LineCompositeCollider(element1, colliders);

        BoundingBoxFactory factory = new BoundingBoxFactory();

        BoundingBox boundingBox = factory.createFromLineComposite(compositeCollider);

        Assertions.assertEquals(new Position(-1,-1), boundingBox.getTopLeft());
        Assertions.assertEquals(8, boundingBox.getWidth());
        Assertions.assertEquals(3, boundingBox.getHeight());

        List<LineCollider> colliders2 = Arrays.asList(collider, collider2, collider3, collider4, collider5);

        LineCompositeCollider compositeCollider1 = new LineCompositeCollider(element1, colliders2);

        BoundingBox boundingBox2 = factory.createFromLineComposite(compositeCollider1);

        Assertions.assertEquals(new Position(-1,-3), boundingBox2.getTopLeft());
        Assertions.assertEquals(8, boundingBox2.getWidth());
        Assertions.assertEquals(5, boundingBox2.getHeight());
    }
}