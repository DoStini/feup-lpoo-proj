package com.shootemup.g53;

import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(50,50);
        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
