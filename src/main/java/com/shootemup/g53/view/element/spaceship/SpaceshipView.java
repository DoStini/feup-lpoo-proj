package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.view.element.ElementView;

public abstract class SpaceshipView implements ElementView<Spaceship> {
    protected int lineWidth;
    protected int size;
    protected String color;

    public SpaceshipView(String color, int size, int lineWidth) {
        this.size = size;
        this.color = color;
        this.lineWidth = lineWidth - 1;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
