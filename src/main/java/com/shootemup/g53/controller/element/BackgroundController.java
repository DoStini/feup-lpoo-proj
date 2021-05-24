package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundController implements ElementInterface {
    protected final GameModel model;
    protected Background background;
    protected int maxDistance;
    protected Random rng;
    protected List<StarController> starControllerList;

    public BackgroundController(GameModel model, Background background, int maxDistance) {
        this(model, background, maxDistance, new Random());
    }

    public BackgroundController(GameModel model, Background background, int maxDistance, Random rng) {
        this.model = model;
        this.background = background;
        this.maxDistance = maxDistance;
        this.rng = rng;
        this.starControllerList = new ArrayList<>();
    }

    public void setStarControllerList(List<StarController> starControllerList) {
        this.starControllerList = starControllerList;
    }

    public void addStarController(StarController starController) {
        starControllerList.add(starController);
    }

    public void removeStarController(StarController starController) {
        starControllerList.remove(starController);
    }

    protected void removeOutOfBounds() {
        List<StarController> controllerCopy = new ArrayList<>(starControllerList);

        for(StarController controller : controllerCopy) {
            Star star = controller.getStar();

            if(star.getPosition().getY() >= model.getHeight()) {
                background.removeStar(star);
                removeStarController(controller);
            }
        }
    }

    @Override
    public void handle() {
        removeOutOfBounds();

        for(StarController controller : starControllerList) {
            controller.handle();
        }

        while(background.getStars().size() < background.getMinStars()) {
            Star star = new Star(new Position(rng.nextInt(model.getWidth()), 0), rng.nextInt(maxDistance));
            star.setSpeed(Math.max(1, maxDistance/(1+star.getDistance())));
            background.addStar(star);
            StarController controller = new StarController(star, new FallDownMovement());
            addStarController(controller);
        }
    }
}
