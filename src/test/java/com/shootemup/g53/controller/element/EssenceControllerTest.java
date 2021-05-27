package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Essence;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EssenceControllerTest {

    @Test
    void handle() {
        Essence essence = Mockito.mock(Essence.class);
        Mockito.when(essence.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(essence.getSpeed()).thenReturn(2.0);
        MovementStrategy movementStrategy = Mockito.mock(MovementStrategy.class);

        Mockito.when(movementStrategy.move(Mockito.any(), Mockito.eq(2.0))).thenReturn(new Position(10, 8));

        EssenceController essenceController = new EssenceController(essence, movementStrategy);
        essenceController.handle(2);
        Mockito.verify(essence, Mockito.times(1)).setPosition(new Position(10, 8));
    }

}