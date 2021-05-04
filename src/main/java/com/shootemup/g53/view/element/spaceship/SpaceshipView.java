package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.view.element.ElementView;

public abstract class SpaceshipView implements ElementView<Spaceship> {
    protected int lineWidth;
    protected int size;
    protected String color;

    public SpaceshipView(int lineWidth) {
        this.lineWidth = lineWidth - 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
