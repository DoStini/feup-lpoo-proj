package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StarTest {
    @Test
    void constructor() {
        Star star = new Star(new Position(1, 2), 10);

        Assertions.assertEquals(new Position(1, 2), star.getPosition());
        Assertions.assertEquals(10, star.getDistance());
        Assertions.assertEquals(5/(10+1), star.getSpeed());
    }

    @Test
    void cloneStar() {
        Star star = new Star(new Position(2,3), 15);
        Star clone = (Star)star.clone();

        Assertions.assertEquals(star.getPosition(), clone.getPosition());
        Assertions.assertEquals(star.getDistance(), clone.getDistance());
        Assertions.assertEquals(5/(15+1), star.getSpeed());
    }
}