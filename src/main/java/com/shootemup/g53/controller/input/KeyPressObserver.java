package com.shootemup.g53.controller.input;

public class KeyPressObserver implements InputObserver
{
    private boolean keyPressed = false;
    @Override
    public void notifyAction() {
        keyPressed = true;
    }

    public boolean getKeyPressed(){
        return keyPressed;
    }
    public void resetKeyPress(){
        keyPressed = false;
    }
}
