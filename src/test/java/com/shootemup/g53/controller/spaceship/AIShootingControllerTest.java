package com.shootemup.g53.controller.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AIShootingControllerTest {

    Spaceship spaceship;
    Position position;

    @BeforeEach
    void setup() {
        position = new Position(0, 0);
        spaceship = Mockito.spy( new Spaceship(position));
        spaceship.setPosition(position);
        // Mockito.when(spaceship.getSpeed()).thenReturn(1); Example
    }

    @Test
    void handle() {

    }
}