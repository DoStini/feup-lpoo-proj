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
    protected double maxSpeed;
    protected List<StarController> starControllerList;

    public BackgroundController(GameModel model, Background background, int maxDistance, double maxSpeed) {
        this(model, background, maxDistance, maxSpeed, new Random());
    }

    public BackgroundController(GameModel model, Background background, int maxDistance, double maxSpeed, Random rng) {
        this.model = model;
        this.background = background;
        this.maxDistance = maxDistance;
        this.rng = rng;
        this.maxSpeed = maxSpeed;
        this.starControllerList = new ArrayList<>();

        while(background.getStars().size() < background.getMinStars()) {
            createStar(new Position(rng.nextInt(model.getWidth()), rng.nextInt(model.getHeight())), rng.nextInt(maxDistance));
        }
    }

    public void setStarControllerList(List<StarController> starControllerList) {
        this.starControllerList = starControllerList;
    }

    public void createStar(Position position, int distance) {
        Star star = new Star(position, distance);
        star.setSpeed(Math.min((double)maxDistance/(1+distance), this.maxSpeed));
        background.addStar(star);
        StarController controller = new StarController(star, new FallDownMovement());
        addStarController(controller);
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
    public void handle(long frame) {
        removeOutOfBounds();

        for(StarController controller : starControllerList) {
            controller.handle(frame);
        }

        while(background.getStars().size() < background.getMinStars()) {
            createStar(new Position(rng.nextInt(model.getWidth()), 0), rng.nextInt(maxDistance));
        }
    }

    public Background getBackground() {
        return background;
    }

    public GameModel getModel() {
        return model;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
