package com.shootemup.g53.controller.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyPressObserverTest {

    @Test
    void notifyAction() {
        KeyPressObserver observer = new KeyPressObserver();

        Assertions.assertFalse(observer.getKeyPressed());

        observer.notifyAction();

        Assertions.assertTrue(observer.getKeyPressed());
    }

    @Test
    void resetKeyPress() {
        KeyPressObserver observer = new KeyPressObserver();

        observer.notifyAction();

        Assertions.assertTrue(observer.getKeyPressed());

        observer.resetKeyPress();

        Assertions.assertFalse(observer.getKeyPressed());
    }
}