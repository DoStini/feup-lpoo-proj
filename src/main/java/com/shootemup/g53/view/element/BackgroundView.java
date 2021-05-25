package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.ui.Gui;

public class BackgroundView {
    protected StarView starView;

    public BackgroundView(StarView starView) {
        this.starView = starView;
    }

    public void draw(Gui gui, Background background) {
        for(Star star : background.getStars()) {
            starView.draw(gui, star);
        }
    }
}
