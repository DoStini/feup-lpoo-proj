package com.shootemup.g53.controller.observer;

import java.util.ArrayList;
import java.util.List;

public class WaveCompletionController implements EventSubject<WaveCompletionObserver>{
    private List<WaveCompletionObserver> waveCompletionObservers = new ArrayList<>();
    @Override
    public void addObserver(WaveCompletionObserver observer) {
        waveCompletionObservers.add(observer);
    }

    @Override
    public void removeObserver(WaveCompletionObserver observer) {
        waveCompletionObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(WaveCompletionObserver waveCompletionObserver: waveCompletionObservers)
            waveCompletionObserver.updateWaveCompleted();
    }
}
