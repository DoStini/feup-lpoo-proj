package com.shootemup.g53.model.infobar;

import com.shootemup.g53.controller.observer.PlayerLifeObserver;
import com.shootemup.g53.controller.observer.PlayerScoreObserver;
import com.shootemup.g53.controller.observer.WaveCompletionObserver;
import com.shootemup.g53.model.Model;

public class InfoBarModel extends Model implements PlayerLifeObserver, PlayerScoreObserver, WaveCompletionObserver {
    private int currentWave = 1;
    private int currentLives;
    private int maxLives;
    private int score;
    private int time;

    public InfoBarModel(int currentLives, int score, int time, int maxLives) {
        this.currentLives = currentLives;
        this.score = score;
        this.time = time;
        this.maxLives = maxLives;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    public int getCurrentLives() {
        return currentLives;
    }

    public void setCurrentLives(int currentLives) {
        this.currentLives = currentLives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void updateLife(int amount) {
        this.currentLives = this.currentLives - amount;
    }

    @Override
    public void updateScore() {
        this.score++;
    }

    @Override
    public void updateWaveCompleted() {
        this.currentWave++;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }
}
