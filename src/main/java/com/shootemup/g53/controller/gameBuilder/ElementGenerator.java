package com.shootemup.g53.controller.gameBuilder;

import com.googlecode.lanterna.TextColor;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public abstract class ElementGenerator {

    private final int xMinPos;
    private final int xMaxPos;
    protected final Random rand;

    ElementGenerator(int xMinPos, int xMaxPos) {
        this(new Random(), xMinPos, xMaxPos);
    }

    ElementGenerator(Random rand, int xMinPos, int xMaxPos) {
        this.rand = rand;
        this.xMinPos = xMinPos;
        this.xMaxPos = xMaxPos;
    }

    protected void setPosition(Element element) {
        element.setPosition(new Position(rand.nextInt(xMaxPos-xMinPos)+xMinPos, -1));
    }

    protected void setColor(Element element) {
        element.setColor("#ffffff");
    }


    abstract Element generateElement();
}
