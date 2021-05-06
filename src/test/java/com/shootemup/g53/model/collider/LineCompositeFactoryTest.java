package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

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
        Collection<Integer> heights = compositeCollider.getColliderHashMap().keySet();
        List<LineCollider> colliderList = new ArrayList<>(colliders);
        List<Integer> heightsList = new ArrayList<>(heights);

        colliderList.sort(Comparator.comparingInt(o -> o.topLeft.getY()));
        Collections.sort(heightsList);

        for(int y = 0; y <= 5; y++) {
            Assertions.assertEquals(heightsList.get(y), colliderList.get(y).topLeft.getY());
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
        Collection<Integer> heights = compositeCollider.getColliderHashMap().keySet();
        List<LineCollider> colliderList = new ArrayList<>(colliders);
        List<Integer> heightsList = new ArrayList<>(heights);

        colliderList.sort(Comparator.comparingInt(o -> o.topLeft.getY()));
        Collections.sort(heightsList);

        for(int y = 0; y <= 5; y++) {
            Assertions.assertEquals(heightsList.get(y), colliderList.get(y).topLeft.getY());
            Assertions.assertEquals(y+3, colliderList.get(y).topLeft.getY());
            Assertions.assertEquals(1, colliderList.get(y).topLeft.getX());
            Assertions.assertEquals(0, colliderList.get(y).width);
        }
    }

    @Test
    void fromIsoscelesTriangle() {
        LineCompositeFactory factory = new LineCompositeFactory();

        LineCompositeCollider compositeCollider = factory.createFromIsoscelesTriangle(
                element2, new Position(2, 1), 5
        );

        BoundingBox bb = compositeCollider.getRealBoundingBox();
        Assertions.assertEquals(new Position(-3,3), bb.getTopLeft());
        Assertions.assertEquals(5*2, bb.getWidth());
        Assertions.assertEquals(5, bb.getHeight());

        Collection<LineCollider> colliders = compositeCollider.getColliderHashMap().values();
        Collection<Integer> heights = compositeCollider.getColliderHashMap().keySet();
        List<LineCollider> colliderList = new ArrayList<>(colliders);
        List<Integer> heightsList = new ArrayList<>(heights);

        colliderList.sort(Comparator.comparingInt(o -> o.topLeft.getY()));
        Collections.sort(heightsList);

        for(int y = 0; y <= 5; y++) {
            Assertions.assertEquals(y+1, heightsList.get(y));
            Assertions.assertEquals(y+1, colliderList.get(y).topLeft.getY());
            Assertions.assertEquals(heightsList.get(y), colliderList.get(y).topLeft.getY());
            Assertions.assertEquals(2-y, colliderList.get(y).topLeft.getX());
            Assertions.assertEquals(y*2, colliderList.get(y).width);
        }
    }

    @Test
    void fromCircle() {
        LineCompositeFactory factory = new LineCompositeFactory();

        LineCompositeCollider compositeCollider = factory.createFromCircle(
                element2, new Position(2, 1), 5
        );

        BoundingBox bb = compositeCollider.getRealBoundingBox();
        Assertions.assertEquals(new Position(2-5,1-5+2), bb.getTopLeft());
        Assertions.assertEquals(5*2, bb.getWidth());
        Assertions.assertEquals(5*2, bb.getHeight());

        Collection<LineCollider> colliders = compositeCollider.getColliderHashMap().values();
        Collection<Integer> heights = compositeCollider.getColliderHashMap().keySet();
        List<LineCollider> colliderList = new ArrayList<>(colliders);
        List<Integer> heightsList = new ArrayList<>(heights);

        colliderList.sort(Comparator.comparingInt(o -> o.topLeft.getY()));
        Collections.sort(heightsList);

        Assertions.assertEquals(1, heightsList.get(5));
        Assertions.assertEquals(1, colliderList.get(5).topLeft.getY());
        Assertions.assertEquals(heightsList.get(5), colliderList.get(5).topLeft.getY());
        Assertions.assertEquals(2-5, colliderList.get(5).topLeft.getX());
        Assertions.assertEquals(5*2, colliderList.get(5).width);

        for(int y = 1; y <= 5; y++) {
            int currentRadius = 5 - y;

            Assertions.assertEquals(1+y, heightsList.get(y+5));
            Assertions.assertEquals(1+y, colliderList.get(y+5).topLeft.getY());
            Assertions.assertEquals(heightsList.get(y+5), colliderList.get(y+5).topLeft.getY());
            Assertions.assertEquals(2-currentRadius, colliderList.get(y+5).topLeft.getX());
            Assertions.assertEquals(currentRadius*2, colliderList.get(y+5).width);

            Assertions.assertEquals(1-y, heightsList.get(5-y));
            Assertions.assertEquals(1-y, colliderList.get(5-y).topLeft.getY());
            Assertions.assertEquals(heightsList.get(5-y), colliderList.get(5-y).topLeft.getY());
            Assertions.assertEquals(2-currentRadius, colliderList.get(5-y).topLeft.getX());
            Assertions.assertEquals(currentRadius*2, colliderList.get(5-y).width);
        }

        LineCompositeCollider compositeCollider2 = factory.createFromCircle(
                element2, new Position(2, 1), 0
        );

        Collection<LineCollider> colliders2 = compositeCollider2.getColliderHashMap().values();
        Collection<Integer> heights2 = compositeCollider2.getColliderHashMap().keySet();
        List<LineCollider> colliderList2 = new ArrayList<>(colliders2);
        List<Integer> heightsList2 = new ArrayList<>(heights2);

        Assertions.assertEquals(0, colliderList2.size());
        Assertions.assertEquals(0, heightsList2.size());
    }
}
