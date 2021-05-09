package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ElementTest {
    List<Element> elementMocks;

    @BeforeEach
    public void setUp(){
        elementMocks = new ArrayList<>();
        Position positionMock = mock(Position.class);
        when(positionMock.getX()).thenReturn(1);
        when(positionMock.getY()).thenReturn(2);


        Element elementMock = new Element(positionMock, "#FFFFFF") {
        };

        elementMocks.add(elementMock);

        Position positionMock2 = mock(Position.class);
        when(positionMock.getX()).thenReturn(2);
        when(positionMock.getY()).thenReturn(3);

        Element elementMock2 = new Element(positionMock2, "#FFFFFF") {
        };

        elementMocks.add(elementMock2);

        Position positionMock3 = mock(Position.class);
        when(positionMock.getX()).thenReturn(1);
        when(positionMock.getY()).thenReturn(2);


        Element elementMock3 = new Element(positionMock3, "#FFFFFF") {
        };

        elementMocks.add(elementMock3);

        Element elementMock4 = new Element(positionMock, "#FFFFFF") {
        };

        elementMocks.add(elementMock4);


    }
    @Test
    public void getPosition(){
        Position positionMock = mock(Position.class);
        when(positionMock.getX()).thenReturn(2);
        when(positionMock.getY()).thenReturn(3);

        elementMocks.get(0).setPosition(positionMock);

        assertEquals(2, elementMocks.get(0).getPosition().getX());
    }


}
