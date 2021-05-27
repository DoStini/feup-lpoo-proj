package com.shootemup.g53.controller.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class InputNotifierTest {
    InputObserver observer;
    InputObserver observer2;

    @BeforeEach
    void setUp() {
        observer = Mockito.mock(InputObserver.class);
        observer2 = Mockito.mock(InputObserver.class);
    }

    @Test
    void addObserver() {
        InputNotifier notifier = new InputNotifier();

        Assertions.assertEquals(0, notifier.inputObservers.size());

        notifier.addObserver(observer);

        Assertions.assertEquals(1, notifier.inputObservers.size());
        Assertions.assertTrue(notifier.inputObservers.contains(observer));

        Assertions.assertFalse(notifier.inputObservers.contains(observer2));

        notifier.addObserver(observer2);

        Assertions.assertEquals(2, notifier.inputObservers.size());
        Assertions.assertTrue(notifier.inputObservers.contains(observer2));
    }

    @Test
    void removeObserver() {
        InputNotifier notifier = new InputNotifier();

        notifier.addObserver(observer);

        notifier.addObserver(observer2);

        notifier.removeObserver(observer);

        Assertions.assertEquals(1, notifier.inputObservers.size());
        Assertions.assertFalse(notifier.inputObservers.contains(observer));
        Assertions.assertTrue(notifier.inputObservers.contains(observer2));

        notifier.removeObserver(observer2);

        Assertions.assertEquals(0, notifier.inputObservers.size());
        Assertions.assertFalse(notifier.inputObservers.contains(observer));
        Assertions.assertFalse(notifier.inputObservers.contains(observer2));
    }

    @Test
    void notifyObservers() {
        InputNotifier notifier = new InputNotifier();

        notifier.notifyObservers();

        Mockito.verify(observer, Mockito.times(0)).notifyAction();
        Mockito.verify(observer2, Mockito.times(0)).notifyAction();

        notifier.addObserver(observer);

        notifier.notifyObservers();

        Mockito.verify(observer, Mockito.times(1)).notifyAction();
        Mockito.verify(observer2, Mockito.times(0)).notifyAction();

        notifier.addObserver(observer2);

        notifier.notifyObservers();

        Mockito.verify(observer, Mockito.times(2)).notifyAction();
        Mockito.verify(observer2, Mockito.times(1)).notifyAction();
    }
}