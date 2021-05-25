package com.shootemup.g53.model.element;

import java.util.ArrayList;
import java.util.List;

public class Background {
    private List<Star> stars;
    private int maxStars;
    private int minStars;

    public Background(List<Star> stars, int minStars, int maxStars) {
        this.stars = stars;
        this.maxStars = maxStars;
        this.minStars = minStars;
    }

    public Background(int minStars, int maxStars) {
        this(new ArrayList<>(), minStars, maxStars);
    }

    public void addStar(Star star) {
        if(stars.size() == maxStars) return;

        this.stars.add(star);
    }

    public void removeStar(Star star) {
        this.stars.remove(star);
    }

    public int getMinStars() {
        return this.minStars;
    }

    public List<Star> getStars() {
        return stars;
    }
}
