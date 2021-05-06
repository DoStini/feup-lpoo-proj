package com.shootemup.g53.model.collider;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.sound.sampled.Line;

import static org.junit.jupiter.api.Assertions.*;

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
}