package com.shootemup.g53.controller.observer;

public interface EventSubject<M> {
    void addObserver(M observer);
    void removeObserver(M observer);
    void notifyObservers();
}
