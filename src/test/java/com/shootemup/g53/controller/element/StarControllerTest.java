package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StarControllerTest {
    Star star;
    Star star2;
    MovementStrategy strategy;

    @BeforeEach
    void setUp() {
        star = Mockito.spy(new Star(new Position(2,2), 5));
        star2 = Mockito.mock(Star.class);
        strategy = Mockito.mock(MovementStrategy.class);

        Mockito.when(strategy.move(Mockito.any(Position.class), Mockito.anyInt())).thenReturn(new Position(5,5));
    }

    @Test
    void constructors() {
        StarController controller = new StarController(star, strategy);

        Assertions.assertEquals(star, controller.getStar());

        controller.setStar(star2);

        Assertions.assertNotEquals(star, controller.getStar());
        Assertions.assertEquals(star2, controller.getStar());
    }

    @Test
    void handle() {
        StarController controller = new StarController(star, strategy);

        controller.handle();

        Mockito.verify(strategy, Mockito.times(1)).move(Mockito.any(), Mockito.anyInt());

        Assertions.assertEquals(new Position(5,5), controller.getStar().getPosition());
    }
}