package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.view.element.ElementView;

public abstract class SpaceshipView implements ElementView<Spaceship> {
    protected int lineWidth;
    protected String color;

    public SpaceshipView(int lineWidth) {
        this.lineWidth = lineWidth;
    }
}
