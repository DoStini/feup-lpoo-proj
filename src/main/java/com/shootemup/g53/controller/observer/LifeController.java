package com.shootemup.g53.controller.observer;

import java.util.ArrayList;
import java.util.List;

public class LifeController implements EventSubject<PlayerLifeObserver>{
    private List<PlayerLifeObserver> playerLifeObservers = new ArrayList<>();
    @Override
    public void addObserver(PlayerLifeObserver observer) {
        playerLifeObservers.add(observer);
    }

    @Override
    public void removeObserver(PlayerLifeObserver observer) {
        playerLifeObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(PlayerLifeObserver playerLifeObserver : playerLifeObservers)
            playerLifeObserver.updateLife();
    }
}
