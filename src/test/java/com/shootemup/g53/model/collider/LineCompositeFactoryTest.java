package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

class LineCompositeFactoryTest {
    Element element1, element2;

    @BeforeEach
    void setUp() {
        element1 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        element1.setPosition(new Position(0,0));
        element2 = Mockito.mock(Element.class, Mockito.CALLS_REAL_METHODS);
        element2.setPosition(new Position(0,2));
    }

    @Test
    void fromSquare() {
        LineCompositeFactory factory = new LineCompositeFactory();

        LineCompositeCollider compositeCollider = factory.createFromSquare(
                element2, new Position(1, 3), 10, 5
        );

        BoundingBox bb = compositeCollider.getRealBoundingBox();
        Assertions.assertEquals(new Position(1,5), bb.getTopLeft());
        Assertions.assertEquals(10, bb.getWidth());
        Assertions.assertEquals(5, bb.getHeight());

        Collection<LineCollider> colliders = compositeCollider.getColliderHashMap().values();
        List<LineCollider> colliderList = new ArrayList<>(colliders);

        colliderList.sort(Comparator.comparingInt(o -> o.topLeft.getY()));

        for(int y = 0; y <= 5; y++) {
            Assertions.assertEquals(y+3, colliderList.get(y).topLeft.getY());
            Assertions.assertEquals(1, colliderList.get(y).topLeft.getX());
            Assertions.assertEquals(10, colliderList.get(y).width);
        }
    }

    @Test
    void fromLine() {
        LineCompositeFactory factory = new LineCompositeFactory();

        LineCompositeCollider compositeCollider = factory.createFromVerticalLine(
                element2, new Position(1, 3), 5
        );

        BoundingBox bb = compositeCollider.getRealBoundingBox();
        Assertions.assertEquals(new Position(1,5), bb.getTopLeft());
        Assertions.assertEquals(0, bb.getWidth());
        Assertions.assertEquals(5, bb.getHeight());

        Collection<LineCollider> colliders = compositeCollider.getColliderHashMap().values();
        List<LineCollider> colliderList = new ArrayList<>(colliders);

        colliderList.sort(Comparator.comparingInt(o -> o.topLeft.getY()));

        for(int y = 0; y <= 5; y++) {
            Assertions.assertEquals(y+3, colliderList.get(y).topLeft.getY());
            Assertions.assertEquals(1, colliderList.get(y).topLeft.getX());
            Assertions.assertEquals(0, colliderList.get(y).width);
        }
    }
}
