package com.shootemup.g53.model.element;

import com.shootemup.g53.controller.command.ButtonCommand;
import com.shootemup.g53.model.Model;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.PoolableObject;

public class Button extends Element {
    private String text;
    private int width;
    private int height;
    private ButtonCommand buttonCommand;

    public Button(String text, Position upperLeft, int width, int height, String hexColor) {
        super(upperLeft,hexColor);
        this.text = text;
        this.width = width;
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public ButtonCommand getButtonCommand() {
        return buttonCommand;
    }

    public void setButtonCommand(ButtonCommand buttonCommand) {
        this.buttonCommand = buttonCommand;
    }


    @Override
    public PoolableObject clone() {
        return new Button(text,getPosition(),width,height,getColor());
    }

}
