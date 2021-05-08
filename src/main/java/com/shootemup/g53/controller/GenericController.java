package com.shootemup.g53.controller;

import com.shootemup.g53.ui.Gui;

import java.util.List;

abstract public class GenericController {
    public abstract void handle(Gui gui);

    public abstract void handleKeyPress(Gui gui);
}