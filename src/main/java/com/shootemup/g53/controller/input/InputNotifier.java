package com.shootemup.g53.controller.input;

import com.shootemup.g53.controller.observer.EventSubject;

import java.util.ArrayList;
import java.util.List;

public class InputNotifier implements EventSubject<InputObserver> {
    protected List<InputObserver> inputObservers = new ArrayList<>();
    @Override
    public void addObserver(InputObserver observer) {
        inputObservers.add(observer);
    }

    @Override
    public void removeObserver(InputObserver observer) {
        inputObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(InputObserver inputObserver: inputObservers){
            inputObserver.notifyAction();
        }
    }

    public List<InputObserver> getInputObservers() {
        return inputObservers;
    }
}
