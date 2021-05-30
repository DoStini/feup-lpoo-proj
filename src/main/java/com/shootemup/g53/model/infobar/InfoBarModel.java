package com.shootemup.g53.model.infobar;

import com.shootemup.g53.controller.observer.EssenceObserver;
import com.shootemup.g53.controller.observer.PlayerLifeObserver;
import com.shootemup.g53.controller.observer.PlayerScoreObserver;
import com.shootemup.g53.controller.observer.WaveCompletionObserver;
import com.shootemup.g53.model.Model;

public class InfoBarModel extends Model implements PlayerLifeObserver, PlayerScoreObserver, WaveCompletionObserver, EssenceObserver {
    private int currentWave = 1;
    private int currentLives;
    private int maxLives;
    private int score;
    private int time;
    private int essence = 0;
    private int essenceShieldCost;
    private int essenceHealthCost;

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
        this.currentLives = amount;
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

    public int getEssence() {
        return essence;
    }

    public int getEssenceShieldCost() {
        return essenceShieldCost;
    }

    public void setEssenceShieldCost(int essenceShieldCost) {
        this.essenceShieldCost = essenceShieldCost;
    }

    public int getEssenceHealthCost() {
        return essenceHealthCost;
    }

    public void setEssenceHealthCost(int essenceHealthCost) {
        this.essenceHealthCost = essenceHealthCost;
    }

    @Override
    public void setEssence(int amount) {
        essence = amount;
    }
}
