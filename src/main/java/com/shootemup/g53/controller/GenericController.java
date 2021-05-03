package com.shootemup.g53.controller;

abstract public class GenericController {

    public void processEventList(List<EventType> events) {
        for (EventType event : events) handleKeyPress(event);
    }
    public abstract void handleKeyPress(EventType event);
}