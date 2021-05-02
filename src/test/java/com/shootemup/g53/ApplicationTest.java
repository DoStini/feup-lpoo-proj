package com.shootemup.g53;

import com.shootemup.g53.Application;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    @Test
    public void testing() {
        Application app = new Application();
        assertEquals("Hello World!", app.testing());
    }
}