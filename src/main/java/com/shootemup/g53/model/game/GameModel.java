package com.shootemup.g53.model.game;

import com.shootemup.g53.model.Model;
import java.util.List;

public class GameModel extends Model {
    private int width;
    private int height;
    private boolean isGameFinished = false;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }
}
