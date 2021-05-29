package com.shootemup.g53.controller.observer;

import java.util.ArrayList;
import java.util.List;

public class ScoreController implements EventSubject<PlayerScoreObserver>{
    private List<PlayerScoreObserver> levelCompletedObservers = new ArrayList<>();
    @Override
    public void addObserver(PlayerScoreObserver observer) {
        levelCompletedObservers.add(observer);
    }

    @Override
    public void removeObserver(PlayerScoreObserver observer) {
        levelCompletedObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(PlayerScoreObserver playerScoreObserver: levelCompletedObservers)
            playerScoreObserver.updateScore();
    }

    public List<PlayerScoreObserver> getLevelCompletedObservers() {
        return levelCompletedObservers;
    }
}
