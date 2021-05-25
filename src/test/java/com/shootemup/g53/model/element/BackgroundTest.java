package com.shootemup.g53.model.element;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BackgroundTest {
    Star star;
    Star star2;
    List<Star> stars;

    @BeforeEach
    void setUp() {
        star = Mockito.mock(Star.class);
        star2 = Mockito.mock(Star.class);

        stars = new ArrayList<>();
        stars.add(star);
        stars.add(star2);
    }

    @Test
    void constructors() {
        Background background = new Background(stars, 5, 15);

        Assertions.assertEquals(stars, background.getStars());
        Assertions.assertEquals(5, background.getMinStars());

        Background background1 = new Background(10, 20);

        Assertions.assertEquals(new ArrayList<>(), background1.getStars());
        Assertions.assertEquals(10, background1.getMinStars());
    }

    @Test
    void addRemoveStars() {
        Background background = new Background(stars, 0, 3);

        Star star1 = Mockito.mock(Star.class);
        Star star2 = Mockito.mock(Star.class);

        Assertions.assertEquals(2, background.getStars().size());
        Assertions.assertFalse(background.getStars().contains(star1));

        background.addStar(star1);

        Assertions.assertEquals(3, background.getStars().size());
        Assertions.assertTrue(background.getStars().contains(star1));

        background.addStar(star2);

        Assertions.assertEquals(3, background.getStars().size());
        Assertions.assertFalse(background.getStars().contains(star2));

        background.removeStar(star1);

        Assertions.assertEquals(2, background.getStars().size());
        Assertions.assertFalse(background.getStars().contains(star1));

        background.removeStar(star2);

        Assertions.assertEquals(2, background.getStars().size());
    }
}