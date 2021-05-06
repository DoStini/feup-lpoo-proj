package com.shootemup.g53.controller;

import com.shootemup.g53.ui.Gui;

import java.util.List;

abstract public class GenericController {

    /*public void processEventList(List<Action> events) {
        for (Action event : events) handleKeyPress(event);
    }
    */

    public abstract void handleKeyPress(Gui gui);
}