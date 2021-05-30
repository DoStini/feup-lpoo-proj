package com.shootemup.g53.controller;

import com.shootemup.g53.ui.Gui;

import java.util.List;

public interface GenericController {
    default void handleKeyPress(Gui gui) {};
    default void handle(long frame) {};
}