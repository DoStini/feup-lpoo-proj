package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.ui.Gui;

public class StarView implements ElementView<Star> {
    double attenuation;

    public StarView(double attenuation) {
        this.attenuation = Math.max(attenuation, 0.001);
    }

    @Override
    public void draw(Gui gui, Star element) {
        double factor = Math.min(1, (double)1/(attenuation*element.getDistance()));
        int value = (int)(255 * factor);
        gui.drawColor(value, value, value, element.getPosition());
    }
}
