package com.shootemup.g53.controller.element;

import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public class BackgroundController implements ElementInterface {
    protected final GameModel model;
    protected Background background;
    protected int maxDistance;
    protected Random rng;

    public BackgroundController(GameModel model, Background background, int maxDistance) {
        this(model, background, maxDistance, new Random());
    }

    public BackgroundController(GameModel model, Background background, int maxDistance, Random rng) {
        this.model = model;
        this.background = background;
        this.maxDistance = maxDistance;
        this.rng = rng;
    }

    @Override
    public void handle() {
        if(background.getStars().size() < background.getMinStars()) {
            background.addStar(new Star(new Position(rng.nextInt(model.getWidth()), 0), rng.nextInt(maxDistance)));
        }
    }
}
